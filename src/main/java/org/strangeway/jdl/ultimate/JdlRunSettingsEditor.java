package org.strangeway.jdl.ultimate;

import com.intellij.openapi.options.ConfigurationException;
import com.intellij.openapi.options.SettingsEditor;
import com.intellij.util.ui.FormBuilder;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

final class JdlRunSettingsEditor extends SettingsEditor<JdlRunConfiguration> {

  private final JTextField appNameField = new JTextField();
  private final JCheckBox createSubFolderCheckbox = new JCheckBox();

  @Override
  protected void resetEditorFrom(@NotNull JdlRunConfiguration runConfiguration) {
    JdlRunConfigurationOptions options = runConfiguration.getOptions();

    createSubFolderCheckbox.setSelected(options.isCreateSubFolder());
    appNameField.setText(options.getApplicationName());
  }

  @Override
  protected void applyEditorTo(@NotNull JdlRunConfiguration runConfiguration) throws ConfigurationException {
    JdlRunConfigurationOptions options = runConfiguration.getOptions();
    
    options.setApplicationName(appNameField.getText());
    options.setCreateSubFolder(createSubFolderCheckbox.isSelected());
  }

  @Override
  protected @NotNull JComponent createEditor() {
    // todo create UI

    return FormBuilder.createFormBuilder()
        .addLabeledComponent("Application name", appNameField)
        .addLabeledComponent("Create nested folder", createSubFolderCheckbox)
        .getPanel();
  }
}
