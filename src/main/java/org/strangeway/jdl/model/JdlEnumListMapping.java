package org.strangeway.jdl.model;

import org.jetbrains.annotations.NotNull;

public final class JdlEnumListMapping extends JdlOptionMapping {
  public <T extends Enum<T> & JdlEnum> JdlEnumListMapping(@NotNull String name, @NotNull Class<T> enumClass) {
    super(name, new JdlEnumListType<>(enumClass));
  }

  public <T extends Enum<T> & JdlEnum> JdlEnumListMapping(@NotNull String name, @NotNull Class<T> enumClass, @NotNull T defaultValue) {
    super(name, new JdlEnumListType<>(enumClass), defaultValue.getId());
  }
}
