package org.strangeway.jdl.uml.model;

import com.intellij.util.containers.ContainerUtil;
import org.jetbrains.annotations.NotNull;
import org.strangeway.jdl.JhipsterIcons;

import javax.swing.*;
import java.util.List;

public final class JdlEnumNodeData implements JdlNodeData {
  private final String name;
  private final List<JdlEnumNodeItem> options;

  public JdlEnumNodeData(@NotNull String name, @NotNull List<@NotNull String> options) {
    this.name = name;
    this.options = ContainerUtil.map(options, JdlEnumNodeItem::new);
  }

  @Override
  public @NotNull String getName() {
    return name;
  }

  @Override
  public Icon getIcon() {
    return JhipsterIcons.getEnumIcon();
  }

  public @NotNull List<@NotNull JdlEnumNodeItem> getOptions() {
    return options;
  }
}
