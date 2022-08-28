package org.strangeway.jdl.run;

import com.intellij.execution.configurations.ConfigurationFactory;
import com.intellij.execution.configurations.RunConfiguration;
import com.intellij.openapi.components.BaseState;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

final class JdlRunConfigurationTypeFactory extends ConfigurationFactory {
  public JdlRunConfigurationTypeFactory(JdlRunConfigurationType type) {
    super(type);
  }

  @Override
  public @NotNull @NonNls String getId() {
    return JdlRunConfigurationType.ID;
  }

  @Override
  public @NotNull RunConfiguration createTemplateConfiguration(@NotNull Project project) {
    return new JdlRunConfiguration(project, this, "JHipster Generate");
  }

  @Override
  public @NotNull Class<? extends BaseState> getOptionsClass() {
    return JdlRunConfigurationOptions.class;
  }

  @Override
  public boolean isEditableInDumbMode() {
    return true;
  }
}
