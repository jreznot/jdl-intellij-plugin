package org.strangeway.jdl.model;

public enum JdlDevDatabaseType implements JdlModelEnum {
  H2DISK("h2Disk"),
  H2MEMORY("h2Memory"),
  MYSQL("mysql"),
  MARIADB("mariadb"),
  MSSQL("mssql"),
  POSTGRESQL("postgresql"),
  ORACLE("oracle"),
  NO("no");

  private final String id;

  JdlDevDatabaseType(String id) {
    this.id = id;
  }

  @Override
  public String getId() {
    return id;
  }
}
