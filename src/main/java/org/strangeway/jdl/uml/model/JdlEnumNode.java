package org.strangeway.jdl.uml.model;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public final class JdlEnumNode implements JdlDiagramNode {
  private final String name;
  private final List<String> options;

  public JdlEnumNode(@NotNull String name, @NotNull List<@NotNull String> options) {
    this.name = name;
    this.options = options;
  }

  @Override
  public @NotNull String getName() {
    return name;
  }

  public @NotNull List<@NotNull String> getOptions() {
    return options;
  }
}
