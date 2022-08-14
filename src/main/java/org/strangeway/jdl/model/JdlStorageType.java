package org.strangeway.jdl.model;

public enum JdlStorageType implements JdlEnum {
  EPHEMERAL("ephemeral"),
  PERSISTENT("persistent");

  private final String id;

  JdlStorageType(String id) {
    this.id = id;
  }

  @Override
  public String getId() {
    return id;
  }
}
