package org.strangeway.jdl.model;

public enum JdlClientFramework implements JdlEnum {
  ANGULARX("angularX"),
  ANGULAR("angular"),
  REACT("react"),
  VUE("vue"),
  SVELTE("svelte"),
  NO("no");

  private final String id;

  JdlClientFramework(String id) {
    this.id = id;
  }

  @Override
  public String getId() {
    return id;
  }
}
