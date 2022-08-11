package org.strangeway.jdl.model;

public enum JdlAuthenticationType implements JdlEnum {
  NO("no"),
  SESSION("session"),
  OAUTH2("oauth2");

  private final String id;

  JdlAuthenticationType(String id) {
    this.id = id;
  }

  @Override
  public String getId() {
    return id;
  }
}
