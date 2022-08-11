package org.strangeway.jdl.model;

public enum JdlBuildTool implements JdlEnum {
  MAVEN("maven"),
  GRADLE("gradle");

  private final String id;

  JdlBuildTool(String id) {
    this.id = id;
  }

  @Override
  public String getId() {
    return id;
  }
}
