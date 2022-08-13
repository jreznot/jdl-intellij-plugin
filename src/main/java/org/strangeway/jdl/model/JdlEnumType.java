package org.strangeway.jdl.model;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public final class JdlEnumType<E extends Enum<E> & JdlEnum> implements JdlOptionType {
  private final List<E> values;

  public JdlEnumType(Class<E> enumClass) {
    this.values = List.of(enumClass.getEnumConstants());
  }

  public List<E> getValues() {
    return values;
  }

  @Override
  public @NotNull String getName() {
    return "Enum";
  }
}
