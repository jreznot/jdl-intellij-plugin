package org.strangeway.jdl.model;

public enum JdlClientPackageManager implements JdlModelEnum {
  NPM("npm"),
  YARN("yarn");

  private final String id;

  JdlClientPackageManager(String id) {
    this.id = id;
  }

  @Override
  public String getId() {
    return id;
  }
}
