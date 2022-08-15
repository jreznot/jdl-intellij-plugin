package org.strangeway.jdl.model;

public enum JdlWebsocket implements JdlModelEnum {
  FALSE("false"),
  SPRINGWEBSOCKET("spring-websocket");

  private final String id;

  JdlWebsocket(String id) {
    this.id = id;
  }

  @Override
  public String getId() {
    return id;
  }
}
