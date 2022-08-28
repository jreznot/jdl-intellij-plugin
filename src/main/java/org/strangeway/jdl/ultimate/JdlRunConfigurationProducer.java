package org.strangeway.jdl.ultimate;

import com.intellij.execution.actions.ConfigurationContext;
import com.intellij.execution.actions.LazyRunConfigurationProducer;
import com.intellij.execution.configurations.ConfigurationFactory;
import com.intellij.openapi.util.Ref;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;

final class JdlRunConfigurationProducer extends LazyRunConfigurationProducer<JdlRunConfiguration> {
  @Override
  protected boolean setupConfigurationFromContext(@NotNull JdlRunConfiguration configuration,
                                                  @NotNull ConfigurationContext context,
                                                  @NotNull Ref<PsiElement> sourceElement) {
    // todo
    // 1. Run from PSI element
    // 2. Run from file
    return false;
  }

  @Override
  public boolean isConfigurationFromContext(@NotNull JdlRunConfiguration configuration,
                                            @NotNull ConfigurationContext context) {
    // todo
    // 1. Run from PSI element
    // 2. Run from file
    return false;
  }

  @NotNull
  @Override
  public ConfigurationFactory getConfigurationFactory() {
    return JdlRunConfigurationType.getInstance().getConfigurationFactories()[0];
  }
}
