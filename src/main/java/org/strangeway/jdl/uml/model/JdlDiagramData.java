package org.strangeway.jdl.uml.model;

import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.List;

public final class JdlDiagramData {

  private final Collection<JdlEntityNodeData> entities;
  private final Collection<JdlEnumNodeData> enums;
  private final Collection<JdlEntityNodeLink> entityLinks;
  private final Collection<JdlEnumNodeLink> enumLinks;

  public JdlDiagramData(@NotNull Collection<JdlEntityNodeData> entities,
                        @NotNull Collection<JdlEnumNodeData> enums,
                        @NotNull Collection<JdlEntityNodeLink> entityLinks,
                        @NotNull Collection<JdlEnumNodeLink> enumLinks) {
    this.entities = entities;
    this.enums = enums;
    this.entityLinks = entityLinks;
    this.enumLinks = enumLinks;
  }

  public @NotNull Collection<JdlEntityNodeData> getEntities() {
    return entities;
  }

  public @NotNull Collection<JdlEnumNodeData> getEnums() {
    return enums;
  }

  public @NotNull Collection<JdlEntityNodeLink> getEntityLinks() {
    return entityLinks;
  }

  public @NotNull Collection<JdlEnumNodeLink> getEnumLinks() {
    return enumLinks;
  }
}
