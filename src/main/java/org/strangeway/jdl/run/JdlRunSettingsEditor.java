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

import com.intellij.openapi.fileChooser.FileChooser;
import com.intellij.openapi.fileChooser.FileChooserDescriptorFactory;
import com.intellij.openapi.options.ConfigurationException;
import com.intellij.openapi.options.SettingsEditor;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.TextFieldWithBrowseButton;
import com.intellij.util.ui.FormBuilder;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import static com.intellij.openapi.util.io.FileUtilRt.toSystemDependentName;
import static com.intellij.openapi.vfs.VfsUtilCore.virtualToIoFile;

final class JdlRunSettingsEditor extends SettingsEditor<JdlRunConfiguration> {

  private final TextFieldWithBrowseButton jdlLocationField = new TextFieldWithBrowseButton(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
      var descriptor = FileChooserDescriptorFactory.createSingleFileDescriptor();
      var file = FileChooser.chooseFile(descriptor, jdlLocationField, project, null);
      if (file != null) {
        File f = virtualToIoFile(file);
        jdlLocationField.setText(toSystemDependentName(f.getPath()));
      }
    }
  });

  private final TextFieldWithBrowseButton outputLocationField = new TextFieldWithBrowseButton(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
      var descriptor = FileChooserDescriptorFactory.createSingleFolderDescriptor();
      var file = FileChooser.chooseFile(descriptor, outputLocationField, project, null);
      if (file != null) {
        File f = virtualToIoFile(file);
        outputLocationField.setText(toSystemDependentName(f.getPath()));
      }
    }
  });

  private final TextFieldWithBrowseButton jhipsterLocationField = new TextFieldWithBrowseButton(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
      var descriptor = FileChooserDescriptorFactory.createSingleFileDescriptor();
      var file = FileChooser.chooseFile(descriptor, jhipsterLocationField, project, null);
      if (file != null) {
        File f = virtualToIoFile(file);
        jhipsterLocationField.setText(toSystemDependentName(f.getPath()));
      }
    }
  });

  private final Project project;

  public JdlRunSettingsEditor(Project project) {
    this.project = project;
  }

  @Override
  protected void resetEditorFrom(@NotNull JdlRunConfiguration runConfiguration) {
    JdlRunConfigurationOptions options = runConfiguration.getOptions();

    jdlLocationField.setText(options.getJdlLocation());
    outputLocationField.setText(options.getOutputLocation());
    jhipsterLocationField.setText(options.getJHipsterLocation());
  }

  @Override
  protected void applyEditorTo(@NotNull JdlRunConfiguration runConfiguration) throws ConfigurationException {
    JdlRunConfigurationOptions options = runConfiguration.getOptions();

    String jdlLocation = jdlLocationField.getText();
    File jdlFile = new File(jdlLocation);
    if (!jdlFile.exists() || !jdlFile.isFile()) {
      throw new ConfigurationException("JDL file does not exist: '" + jdlLocation + "'");
    }

    options.setJdlLocation(jdlLocation);
    options.setJhipsterLocation(jhipsterLocationField.getText());
    options.setOutputLocation(outputLocationField.getText());
  }

  @Override
  protected @NotNull JComponent createEditor() {
    return FormBuilder.createFormBuilder()
        .addLabeledComponent("JDL file:", jdlLocationField)
        .addLabeledComponent("Output location:", outputLocationField)
        .addLabeledComponent("JHipster executable:", jhipsterLocationField)
        .getPanel();
  }
}
