package org.strangeway.jdl.uml.model;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class JdlEntityNodeField {
  private final String name;
  private final String type;
  private final boolean required;

  public JdlEntityNodeField(@NotNull String name, @Nullable String type, boolean required) {
    this.name = name;
    this.type = type;
    this.required = required;
  }

  public @NotNull String getName() {
    return name;
  }

  public @Nullable String getType() {
    return type;
  }

  public boolean isRequired() {
    return required;
  }
}
