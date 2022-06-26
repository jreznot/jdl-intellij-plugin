package org.strangeway.jdl.model;

import java.util.List;

public final class JdlEnumType<E extends Enum<E>> implements JdlOptionType {
  private final String name;
  private final List<E> values;

  public JdlEnumType(String name, Class<E> enumClass) {
    this.name = name;
    this.values = List.of(enumClass.getEnumConstants());
  }

  public String getName() {
    return name;
  }

  public List<E> getValues() {
    return values;
  }
}
