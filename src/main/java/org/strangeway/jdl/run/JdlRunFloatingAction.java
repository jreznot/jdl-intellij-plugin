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
import com.intellij.psi.PsiFile;
import org.jetbrains.annotations.NotNull;
import org.strangeway.jdl.JdlFileType;

import java.util.List;

import static com.intellij.openapi.util.io.FileUtil.toSystemDependentName;
import static com.intellij.openapi.vfs.VfsUtilCore.virtualToIoFile;

final class JdlRunFloatingAction extends AnAction implements DumbAware {
  @Override
  public void update(@NotNull AnActionEvent e) {
    PsiFile psiFile = e.getDataContext().getData(CommonDataKeys.PSI_FILE);
    e.getPresentation().setEnabledAndVisible(psiFile != null && psiFile.getFileType() == JdlFileType.INSTANCE);
  }

  @Override
  public void actionPerformed(@NotNull AnActionEvent e) {
    Project project = e.getProject();
    if (project == null) return;

    PsiFile psiFile = e.getDataContext().getData(CommonDataKeys.PSI_FILE);
    if (psiFile == null) return;

    VirtualFile jdlFile = psiFile.getVirtualFile();
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
    List<RunConfiguration> runConfigurations = RunManager.getInstance(project)
        .getConfigurationsList(JdlRunConfigurationType.getInstance());

    for (RunConfiguration runConfiguration : runConfigurations) {
      if (runConfiguration instanceof JdlRunConfiguration) {
        String jdlLocation = ((JdlRunConfiguration) runConfiguration).getOptions().getJdlLocation();
        //noinspection UnstableApiUsage
        if (VfsUtilCore.pathEqualsTo(jdlFile, jdlLocation)) {
          return runConfiguration;
        }
      }
    }
    return null;
  }
}
