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
      JdlRunConfigurationOptions options = configuration.getOptions();
      options.setJdlLocation(toSystemDependentName(virtualToIoFile(jdlFile).getPath()));
      configuration.setName(jdlFile.getName());
      return true;
    }

    return false;
  }

  @Override
  public boolean isConfigurationFromContext(@NotNull JdlRunConfiguration configuration,
                                            @NotNull ConfigurationContext context) {
    VirtualFile jdlFile = getFileFromContext(context);

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
