package org.strangeway.jdl.model;

public enum JdlApplicationType implements JdlEnum {
  MICROSERVICE("microservice"),
  MONOLITH("monolith");

  private final String id;

  JdlApplicationType(String id) {
    this.id = id;
  }

  @Override
  public String getId() {
    return id;
  }
}
