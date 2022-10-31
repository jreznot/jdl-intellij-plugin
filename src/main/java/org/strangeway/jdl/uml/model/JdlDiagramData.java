package org.strangeway.jdl.uml.model;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public final class JdlDiagramData {

  private final List<JdlEntityNodeData> entities;
  private final List<JdlEnumNodeData> enums;
  private final List<JdlEntityNodeLink> entityLinks;
  private final List<JdlEnumNodeLink> enumLinks;

  public JdlDiagramData(@NotNull List<JdlEntityNodeData> entities,
                        @NotNull List<JdlEnumNodeData> enums,
                        @NotNull List<JdlEntityNodeLink> entityLinks,
                        @NotNull List<JdlEnumNodeLink> enumLinks) {
    this.entities = entities;
    this.enums = enums;
    this.entityLinks = entityLinks;
    this.enumLinks = enumLinks;
  }

  public @NotNull List<JdlEntityNodeData> getEntities() {
    return entities;
  }

  public @NotNull List<JdlEnumNodeData> getEnums() {
    return enums;
  }

  public @NotNull List<JdlEntityNodeLink> getEntityLinks() {
    return entityLinks;
  }

  public @NotNull List<JdlEnumNodeLink> getEnumLinks() {
    return enumLinks;
  }
}
