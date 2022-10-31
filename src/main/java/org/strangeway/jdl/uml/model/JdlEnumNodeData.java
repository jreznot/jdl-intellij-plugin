package org.strangeway.jdl.uml.model;

import org.jetbrains.annotations.NotNull;
import org.strangeway.jdl.JhipsterIcons;

import javax.swing.*;
import java.util.List;

public final class JdlEnumNodeData implements JdlNodeData {
  private final String name;
  private final List<String> options;

  public JdlEnumNodeData(@NotNull String name, @NotNull List<@NotNull String> options) {
    this.name = name;
    this.options = options;
  }

  @Override
  public @NotNull String getName() {
    return name;
  }

  @Override
  public Icon getIcon() {
    return JhipsterIcons.getEnumIcon();
  }

  public @NotNull List<@NotNull String> getOptions() {
    return options;
  }
}
