package org.strangeway.jdl.model;

public enum JdlMessageBroker implements JdlEnum {
  FALSE("false"),
  KAFKA("kafka");

  private final String id;

  JdlMessageBroker(String id) {
    this.id = id;
  }

  @Override
  public String getId() {
    return id;
  }
}
