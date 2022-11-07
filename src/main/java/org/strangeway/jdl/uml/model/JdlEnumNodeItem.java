package org.strangeway.jdl.uml.model;

import java.util.Objects;

public final class JdlEnumNodeItem {
  private final String name;

  public JdlEnumNodeItem(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    JdlEnumNodeItem that = (JdlEnumNodeItem) o;
    return Objects.equals(name, that.name);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name);
  }
}
