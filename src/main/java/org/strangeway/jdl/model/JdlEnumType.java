package org.strangeway.jdl.model;

import java.util.List;

public final class JdlEnumType<E extends Enum<E>> implements JdlOptionType {
  private final List<E> values;

  public JdlEnumType(Class<E> enumClass) {
    this.values = List.of(enumClass.getEnumConstants());
  }

  public List<E> getValues() {
    return values;
  }
}
