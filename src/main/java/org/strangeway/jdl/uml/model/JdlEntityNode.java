package org.strangeway.jdl.uml.model;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public final class JdlEntityNode implements JdlDiagramNode {
  private final String name;
  private final List<JdlEntityNodeProperty> properties;

  public JdlEntityNode(@NotNull String name, @NotNull List<JdlEntityNodeProperty> properties) {
    this.name = name;
    this.properties = properties;
  }

  @Override
  public @NotNull String getName() {
    return name;
  }

  public @NotNull List<JdlEntityNodeProperty> getProperties() {
    return properties;
  }
}
