/*
 * Copyright © 2023 Yuriy Artamonov
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software
 * and associated documentation files (the “Software”), to deal in the Software without
 * restriction, including without limitation the rights to use, copy, modify, merge, publish,
 * distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the
 * Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies
 * or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED “AS IS”, WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING
 * BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM,
 * DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING
 * FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package org.strangeway.jdl.run;

import com.intellij.execution.actions.ConfigurationContext;
import com.intellij.execution.actions.LazyRunConfigurationProducer;
import com.intellij.execution.configurations.ConfigurationFactory;
import com.intellij.openapi.util.Ref;
import com.intellij.openapi.vfs.VfsUtilCore;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.strangeway.jdl.JdlFileType;

import static com.intellij.openapi.util.io.FileUtil.toSystemDependentName;
import static com.intellij.openapi.vfs.VfsUtilCore.virtualToIoFile;

final class JdlRunConfigurationProducer extends LazyRunConfigurationProducer<JdlRunConfiguration> {
  @Override
  protected boolean setupConfigurationFromContext(@NotNull JdlRunConfiguration configuration,
                                                  @NotNull ConfigurationContext context,
                                                  @NotNull Ref<PsiElement> sourceElement) {
    VirtualFile jdlFile = getFileFromContext(context);

    if (jdlFile != null && jdlFile.getFileType() == JdlFileType.INSTANCE) {
      var options = configuration.getOptions();
      options.setJdlLocation(toSystemDependentName(virtualToIoFile(jdlFile).getPath()));
      configuration.setName(jdlFile.getName());
      return true;
    }

    return false;
  }

  @Override
  public boolean isConfigurationFromContext(@NotNull JdlRunConfiguration configuration,
                                            @NotNull ConfigurationContext context) {
    var jdlFile = getFileFromContext(context);

    //noinspection UnstableApiUsage
    return jdlFile != null && VfsUtilCore.pathEqualsTo(jdlFile, configuration.getOptions().getJdlLocation());
  }

  @NotNull
  @Override
  public ConfigurationFactory getConfigurationFactory() {
    return JdlRunConfigurationType.getInstance().getConfigurationFactories()[0];
  }

  private static @Nullable VirtualFile getFileFromContext(@NotNull ConfigurationContext context) {
    var location = context.getLocation();
    return location != null ? location.getVirtualFile() : null;
  }
}
