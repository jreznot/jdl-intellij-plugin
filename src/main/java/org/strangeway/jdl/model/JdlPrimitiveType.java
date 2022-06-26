package org.strangeway.jdl.model;

public final class JdlPrimitiveType implements JdlOptionType {
  public final String name;

  public JdlPrimitiveType(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }
}
