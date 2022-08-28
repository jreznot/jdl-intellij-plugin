package org.strangeway.jdl.ultimate;

import com.intellij.execution.configurations.ConfigurationFactory;
import com.intellij.execution.configurations.ConfigurationType;
import com.intellij.execution.configurations.ConfigurationTypeUtil;
import com.intellij.openapi.project.DumbAware;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import org.strangeway.jdl.JhipsterIcons;

import javax.swing.*;

final class JdlRunConfigurationType implements ConfigurationType, DumbAware {
  public static final String ID = "JHipsterJDL";

  @Override
  public @NotNull @NonNls String getId() {
    return ID;
  }

  @Override
  public @NotNull @Nls(capitalization = Nls.Capitalization.Title) String getDisplayName() {
    return "JHipster";
  }

  @Override
  public String getConfigurationTypeDescription() {
    return "JHipster JDL generator";
  }

  @Override
  public Icon getIcon() {
    return JhipsterIcons.FILE_ICON;
  }

  @Override
  public ConfigurationFactory[] getConfigurationFactories() {
    return new ConfigurationFactory[]{new JdlRunConfigurationTypeFactory(this)};
  }

  public static JdlRunConfigurationType getInstance() {
    return ConfigurationTypeUtil.findConfigurationType(JdlRunConfigurationType.class);
  }
}
