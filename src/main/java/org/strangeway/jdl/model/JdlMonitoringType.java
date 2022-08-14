package org.strangeway.jdl.model;

public enum JdlMonitoringType implements JdlEnum {
  NO("no"),
  PROMETHEUS("prometheus");

  private final String id;

  JdlMonitoringType(String id) {
    this.id = id;
  }

  @Override
  public String getId() {
    return id;
  }
}
