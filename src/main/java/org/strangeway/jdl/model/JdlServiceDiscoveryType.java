package org.strangeway.jdl.model;

public enum JdlServiceDiscoveryType implements JdlEnum {
  FALSE("false"),
  NO("no"),
  EUREKA("eureka"),
  CONSUL("consul");

  private final String id;

  JdlServiceDiscoveryType(String id) {
    this.id = id;
  }

  @Override
  public String getId() {
    return id;
  }
}
