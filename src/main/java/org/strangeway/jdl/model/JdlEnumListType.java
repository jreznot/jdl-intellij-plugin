package org.strangeway.jdl.model;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public final class JdlEnumListType<E extends Enum<E> & JdlModelEnum> implements JdlOptionType {
  private final List<E> values;

  public JdlEnumListType(Class<E> enumClass) {
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
