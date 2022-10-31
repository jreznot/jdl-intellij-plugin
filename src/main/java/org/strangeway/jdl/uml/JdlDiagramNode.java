package org.strangeway.jdl.uml;

import com.intellij.diagram.DiagramNode;
import com.intellij.diagram.DiagramProvider;
import com.intellij.openapi.util.UserDataHolderBase;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.strangeway.jdl.uml.model.JdlNodeData;

import javax.swing.*;

public final class JdlDiagramNode extends UserDataHolderBase implements DiagramNode<JdlNodeData> {

  private final JdlNodeData data;
  private final DiagramProvider<JdlNodeData> provider;

  public JdlDiagramNode(JdlNodeData data, DiagramProvider<JdlNodeData> provider) {
    this.data = data;
    this.provider = provider;
  }

  @Override
  public @NotNull JdlNodeData getIdentifyingElement() {
    return data;
  }

  @Override
  public @NotNull DiagramProvider<JdlNodeData> getProvider() {
    return provider;
  }

  @Override
  public @Nullable @Nls String getTooltip() {
    return null;
  }

  @Override
  public @Nullable Icon getIcon() {
    return data.getIcon();
  }
}
