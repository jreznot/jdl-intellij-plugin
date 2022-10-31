package org.strangeway.jdl.uml.model;

import org.jetbrains.annotations.NotNull;
import org.strangeway.jdl.JhipsterIcons;

import javax.swing.*;
import java.util.List;

public final class JdlEntityNodeData implements JdlNodeData {
  private final String name;
  private final List<JdlEntityNodeProperty> properties;

  public JdlEntityNodeData(@NotNull String name, @NotNull List<JdlEntityNodeProperty> properties) {
    this.name = name;
    this.properties = properties;
  }

  @Override
  public @NotNull String getName() {
    return name;
  }

  @Override
  public Icon getIcon() {
    return JhipsterIcons.getEntityIcon();
  }

  public @NotNull List<JdlEntityNodeProperty> getProperties() {
    return properties;
  }
}