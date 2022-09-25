package org.strangeway.jdl.uml.model;

import org.jetbrains.annotations.NotNull;

public final class JdlEnumNodeLink {
  private final JdlEntityNode entity;
  private final JdlEnumNode enumeration;

  public JdlEnumNodeLink(@NotNull JdlEntityNode entity, @NotNull JdlEnumNode enumeration) {
    this.entity = entity;
    this.enumeration = enumeration;
  }

  public @NotNull JdlEntityNode getEntity() {
    return entity;
  }

  public @NotNull JdlEnumNode getEnumeration() {
    return enumeration;
  }
}
