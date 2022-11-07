package org.strangeway.jdl.uml.model;

import org.jetbrains.annotations.NotNull;
import org.strangeway.jdl.JhipsterIcons;

import javax.swing.*;
import java.util.List;
import java.util.Objects;

public final class JdlEntityNodeData implements JdlNodeData {
  private final String name;
  private final List<JdlEntityNodeField> properties;

  public JdlEntityNodeData(@NotNull String name, @NotNull List<JdlEntityNodeField> properties) {
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

  public @NotNull List<JdlEntityNodeField> getProperties() {
    return properties;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    JdlEntityNodeData that = (JdlEntityNodeData) o;
    return name.equals(that.name) && properties.equals(that.properties);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, properties);
  }
}
