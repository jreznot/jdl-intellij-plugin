package org.strangeway.jdl.model;

import org.jetbrains.annotations.NotNull;

public final class JdlEnumMapping extends JdlOptionMapping {
  public <T extends Enum<T> & JdlModelEnum> JdlEnumMapping(@NotNull String name, @NotNull Class<T> enumClass) {
    super(name, new JdlEnumType<>(enumClass));
  }

  public <T extends Enum<T> & JdlModelEnum> JdlEnumMapping(@NotNull String name, @NotNull Class<T> enumClass, @NotNull T defaultValue) {
    super(name, new JdlEnumType<>(enumClass), defaultValue.getId());
  }
}
