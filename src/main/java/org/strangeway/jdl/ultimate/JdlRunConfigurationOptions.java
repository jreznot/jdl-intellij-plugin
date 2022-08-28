package org.strangeway.jdl.ultimate;

import com.intellij.execution.configurations.RunConfigurationOptions;
import com.intellij.openapi.components.StoredProperty;

final class JdlRunConfigurationOptions extends RunConfigurationOptions {
  private final StoredProperty<String> jhipsterLocation = string("").provideDelegate(this, "jhipsterLocation");
  private final StoredProperty<String> jdlFileLocation = string("").provideDelegate(this, "jdlLocation");
  private final StoredProperty<String> outputLocation = string("").provideDelegate(this, "outputLocation");

  private final StoredProperty<String> applicationName = string("").provideDelegate(this, "applicationName");
  private final StoredProperty<Boolean> createSubFolder = property(false).provideDelegate(this, "createSubFolder");

  // todo --force parameter

  public String getJHipsterLocation() {
    return jhipsterLocation.getValue(this);
  }

  public void setJhipsterLocation(String path) {
    jhipsterLocation.setValue(this, path);
  }

  public String getJdlLocation() {
    return jdlFileLocation.getValue(this);
  }

  public void setJdlLocation(String path) {
    jdlFileLocation.setValue(this, path);
  }

  public String getOutputLocation() {
    return outputLocation.getValue(this);
  }

  public void setOutputLocation(String path) {
    outputLocation.setValue(this, path);
  }

  public void setCreateSubFolder(boolean value) {
    createSubFolder.setValue(this, value);
  }

  public boolean isCreateSubFolder() {
    return createSubFolder.getValue(this);
  }

  public String getApplicationName() {
    return applicationName.getValue(this);
  }

  public void setApplicationName(String value) {
    applicationName.setValue(this, value);
  }
}
