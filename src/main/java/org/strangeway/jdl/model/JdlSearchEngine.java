package org.strangeway.jdl.model;

public enum JdlSearchEngine implements JdlModelEnum {
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
