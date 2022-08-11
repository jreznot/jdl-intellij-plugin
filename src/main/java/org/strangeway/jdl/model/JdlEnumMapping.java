package org.strangeway.jdl.model;

public final class JdlEnumMapping extends JdlOptionMapping {
  public <T extends Enum<T>> JdlEnumMapping(String name, Class<T> enumClass) {
    super(name, new JdlEnumType<>(enumClass));
  }
}
