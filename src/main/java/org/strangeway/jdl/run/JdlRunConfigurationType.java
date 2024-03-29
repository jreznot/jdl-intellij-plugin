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
