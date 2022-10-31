package org.strangeway.jdl.uml.model;

import org.jetbrains.annotations.NotNull;

public final class JdlEnumNodeLink {
  private final JdlEntityNodeData entity;
  private final JdlEnumNodeData enumeration;

  public JdlEnumNodeLink(@NotNull JdlEntityNodeData entity, @NotNull JdlEnumNodeData enumeration) {
    this.entity = entity;
    this.enumeration = enumeration;
  }

  public @NotNull JdlEntityNodeData getEntity() {
    return entity;
  }

  public @NotNull JdlEnumNodeData getEnumeration() {
    return enumeration;
  }
}
