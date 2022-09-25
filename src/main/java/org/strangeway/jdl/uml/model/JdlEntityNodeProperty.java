package org.strangeway.jdl.uml.model;

import org.jetbrains.annotations.NotNull;

public final class JdlEntityNodeProperty {
  public final String name;
  public final String type;
  public final boolean required;

  public JdlEntityNodeProperty(@NotNull String name, @NotNull String type, boolean required) {
    this.name = name;
    this.type = type;
    this.required = required;
  }

  public @NotNull String getName() {
    return name;
  }

  public @NotNull String getType() {
    return type;
  }

  public boolean isRequired() {
    return required;
  }
}
