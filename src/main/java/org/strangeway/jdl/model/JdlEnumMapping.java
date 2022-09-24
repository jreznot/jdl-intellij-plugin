package org.strangeway.jdl.model;

import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.stream.Collectors;

public final class JdlEnumMapping extends JdlOptionMapping {
  public <T extends Enum<T> & JdlModelEnum> JdlEnumMapping(@NotNull String name, @NotNull Class<T> enumClass) {
    super(name, new JdlEnumType<>(enumClass));
  }

  public <T extends Enum<T> & JdlModelEnum> JdlEnumMapping(@NotNull String name, @NotNull Class<T> enumClass, @NotNull T defaultValue) {
    super(name, new JdlEnumType<>(enumClass), defaultValue.getId());
  }

  @Override
  public @NotNull JdlEnumType<?> getPropertyType() {
    return (JdlEnumType<?>) super.getPropertyType();
  }

  public List<String> getOptions() {
    return getPropertyType().getValues().stream()
        .map(JdlModelEnum::getId)
        .collect(Collectors.toList());
  }
}
