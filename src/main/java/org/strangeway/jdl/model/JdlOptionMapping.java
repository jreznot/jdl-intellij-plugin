package org.strangeway.jdl.model;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class JdlOptionMapping {
  private final String name;
  private final JdlOptionType propertyType;
  private final String defaultValue;

  public JdlOptionMapping(@NotNull String name, @NotNull JdlOptionType propertyType) {
    this.name = name;
    this.propertyType = propertyType;
    this.defaultValue = null;
  }

  public JdlOptionMapping(@NotNull String name, @NotNull JdlOptionType optionType, String defaultValue) {
    this.name = name;
    this.propertyType = optionType;
    this.defaultValue = defaultValue;
  }

  public @NotNull String getName() {
    return name;
  }

  public @NotNull JdlOptionType getPropertyType() {
    return propertyType;
  }

  public @Nullable String getDefaultValue() {
    return defaultValue;
  }
}
