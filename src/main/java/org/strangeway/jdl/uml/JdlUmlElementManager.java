package org.strangeway.jdl.uml;

import com.intellij.diagram.AbstractDiagramElementManager;
import com.intellij.openapi.actionSystem.DataContext;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.strangeway.jdl.uml.model.JdlDiagramNode;

import java.util.Collection;
import java.util.Collections;

final class JdlUmlElementManager extends AbstractDiagramElementManager<JdlDiagramNode> {
  @Override
  public @Nullable JdlDiagramNode findInDataContext(@NotNull DataContext dataContext) {
    return null;
  }

  @Override
  public @NotNull Collection<JdlDiagramNode> findElementsInDataContext(@NotNull DataContext dataContext) {
    return Collections.emptyList();
  }

  @Override
  public boolean isAcceptableAsNode(@Nullable Object o) {
    return false;
  }

  @Override
  public Object @NotNull [] getNodeItems(JdlDiagramNode jdlDiagramNode) {
    return new Object[0];
  }

  @Override
  public boolean canCollapse(JdlDiagramNode jdlDiagramNode) {
    return false;
  }

  @Override
  public @Nullable @Nls String getElementTitle(JdlDiagramNode node) {
    return node.getName();
  }

  @Override
  public @Nullable @Nls String getNodeTooltip(JdlDiagramNode node) {
    return null;
  }
}
