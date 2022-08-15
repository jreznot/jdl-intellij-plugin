package org.strangeway.jdl.model;

public enum JdlDatabaseType implements JdlModelEnum {
  NO("no"),
  SQL("sql"),
  CASSANDRA("cassandra"),
  COUCHBASE("couchbase"),
  MONGODB("mongodb");

  private final String id;

  JdlDatabaseType(String id) {
    this.id = id;
  }

  @Override
  public String getId() {
    return id;
  }
}
