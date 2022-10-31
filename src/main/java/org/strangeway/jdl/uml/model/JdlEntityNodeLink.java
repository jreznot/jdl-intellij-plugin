package org.strangeway.jdl.uml.model;

import org.jetbrains.annotations.NotNull;

public final class JdlEntityNodeLink {
  private final JdlEntityNodeLinkType type;
  private final JdlEntityNodeData fromEntity;
  private final JdlEntityNodeData toEntity;

  public JdlEntityNodeLink(@NotNull JdlEntityNodeLinkType type,
                           @NotNull JdlEntityNodeData fromEntity,
                           @NotNull JdlEntityNodeData toEntity) {
    this.type = type;
    this.fromEntity = fromEntity;
    this.toEntity = toEntity;
  }

  public @NotNull JdlEntityNodeLinkType getType() {
    return type;
  }

  public @NotNull JdlEntityNodeData getFromEntity() {
    return fromEntity;
  }

  public @NotNull JdlEntityNodeData getToEntity() {
    return toEntity;
  }
}
