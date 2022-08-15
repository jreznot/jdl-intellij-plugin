package org.strangeway.jdl.model;

public enum JdlProdDatabaseType implements JdlModelEnum {
  MYSQL("mysql"),
  MARIADB("mariadb"),
  MSSQL("mssql"),
  POSTGRESQL("postgresql"),
  ORACLE("oracle"),
  NO("no");

  private final String id;

  JdlProdDatabaseType(String id) {
    this.id = id;
  }

  @Override
  public String getId() {
    return id;
  }
}
