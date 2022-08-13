package org.strangeway.jdl.model;

public enum JdlSearchEngine implements JdlEnum {
  FALSE("false"),
  ELASTICSEARCH("elasticsearch");

  private final String id;

  JdlSearchEngine(String id) {
    this.id = id;
  }

  @Override
  public String getId() {
    return id;
  }
}