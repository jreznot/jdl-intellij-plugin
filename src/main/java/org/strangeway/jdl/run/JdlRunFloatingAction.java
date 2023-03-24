/*
 * Copyright © 2023 Yuriy Artamonov
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software
 * and associated documentation files (the "Software"), to deal in the Software without
 * restriction, including without limitation the rights to use, copy, modify, merge, publish,
 * distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the
 * Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies
 * or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING
 * BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM,
 * DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING
 * FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package org.strangeway.jdl.run;

import com.intellij.execution.ExecutorRegistryImpl.RunnerHelper;
import com.intellij.execution.RunManager;
import com.intellij.execution.configurations.RunConfiguration;
import com.intellij.execution.executors.DefaultRunExecutor;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.project.DumbAware;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VfsUtilCore;
import com.intellij.openapi.vfs.VirtualFile;
import org.jetbrains.annotations.NotNull;
import org.strangeway.jdl.JdlFileType;

import static com.intellij.openapi.util.io.FileUtil.toSystemDependentName;
import static com.intellij.openapi.vfs.VfsUtilCore.virtualToIoFile;

final class JdlRunFloatingAction extends AnAction implements DumbAware {
  @Override
  public void update(@NotNull AnActionEvent e) {
    var psiFile = e.getDataContext().getData(CommonDataKeys.PSI_FILE);
    e.getPresentation().setEnabledAndVisible(psiFile != null && psiFile.getFileType() == JdlFileType.INSTANCE);
  }

  @Override
  public void actionPerformed(@NotNull AnActionEvent e) {
    var project = e.getProject();
    if (project == null) return;

    var psiFile = e.getDataContext().getData(CommonDataKeys.PSI_FILE);
    if (psiFile == null) return;

    var jdlFile = psiFile.getVirtualFile();
    if (jdlFile == null) return;

    var runManager = RunManager.getInstance(project);

    var runConfiguration = findRunConfiguration(project, jdlFile);
    if (runConfiguration == null) {
      var uniqueName = runManager.suggestUniqueName(jdlFile.getName(), JdlRunConfigurationType.getInstance());
      var runnerAndConfigurationSettings = runManager.createConfiguration(uniqueName, JdlRunConfigurationType.class);
      var newConfiguration = (JdlRunConfiguration) runnerAndConfigurationSettings.getConfiguration();
      newConfiguration.getOptions().setJdlLocation(toSystemDependentName(virtualToIoFile(jdlFile).getPath()));
      runManager.addConfiguration(runnerAndConfigurationSettings);

      runConfiguration = newConfiguration;
    }

    var settings = runManager.findSettings(runConfiguration);

    RunnerHelper.run(project, runConfiguration, settings, e.getDataContext(), DefaultRunExecutor.getRunExecutorInstance());
  }

  private static RunConfiguration findRunConfiguration(Project project, VirtualFile jdlFile) {
    var runConfigurations = RunManager.getInstance(project)
        .getConfigurationsList(JdlRunConfigurationType.getInstance());

    for (var runConfiguration : runConfigurations) {
      if (runConfiguration instanceof JdlRunConfiguration) {
        var jdlLocation = ((JdlRunConfiguration) runConfiguration).getOptions().getJdlLocation();
        //noinspection UnstableApiUsage
        if (VfsUtilCore.pathEqualsTo(jdlFile, jdlLocation)) {
          return runConfiguration;
        }
      }
    }
    return null;
  }
}
