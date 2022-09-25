package org.strangeway.jdl.uml.model;

import org.jetbrains.annotations.NotNull;

public final class JdlEntityNodeLink {
  private final JdlEntityNodeLinkType type;
  private final JdlEntityNode fromEntity;
  private final JdlEntityNode toEntity;

  public JdlEntityNodeLink(@NotNull JdlEntityNodeLinkType type,
                           @NotNull JdlEntityNode fromEntity,
                           @NotNull JdlEntityNode toEntity) {
    this.type = type;
    this.fromEntity = fromEntity;
    this.toEntity = toEntity;
  }

  public @NotNull JdlEntityNodeLinkType getType() {
    return type;
  }

  public @NotNull JdlEntityNode getFromEntity() {
    return fromEntity;
  }

  public @NotNull JdlEntityNode getToEntity() {
    return toEntity;
  }
}
