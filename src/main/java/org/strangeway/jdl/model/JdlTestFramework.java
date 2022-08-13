package org.strangeway.jdl.model;

public enum JdlTestFramework implements JdlEnum {
  CYPRESS("cypress"),
  PROTRACTOR("protractor"),
  CUCUMBER("cucumber"),
  GATLING("gatling");

  private final String id;

  JdlTestFramework(String id) {
    this.id = id;
  }

  @Override
  public String getId() {
    return id;
  }
}