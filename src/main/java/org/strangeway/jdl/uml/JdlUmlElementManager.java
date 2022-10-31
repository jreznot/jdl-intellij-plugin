package org.strangeway.jdl.uml;

import com.intellij.diagram.AbstractDiagramElementManager;
import com.intellij.diagram.DiagramBuilder;
import com.intellij.openapi.actionSystem.DataContext;
import com.intellij.openapi.actionSystem.PlatformCoreDataKeys;
import com.intellij.psi.PsiFile;
import com.intellij.util.containers.ContainerUtil;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.strangeway.jdl.psi.JdlFile;
import org.strangeway.jdl.uml.model.JdlDiagramData;
import org.strangeway.jdl.uml.model.JdlEntityNodeData;
import org.strangeway.jdl.uml.model.JdlEnumNodeData;
import org.strangeway.jdl.uml.model.JdlNodeData;

import java.util.Collection;
import java.util.Collections;

final class JdlUmlElementManager extends AbstractDiagramElementManager<JdlNodeData> {
  @Override
  public @Nullable JdlNodeData findInDataContext(@NotNull DataContext dataContext) {
    return null;
  }

  @Override
  public @NotNull Collection<JdlNodeData> findElementsInDataContext(@NotNull DataContext dataContext) {
    PsiFile file = PlatformCoreDataKeys.PSI_FILE.getData(dataContext);
    if (!(file instanceof JdlFile)) return Collections.emptyList();

    JdlDiagramData data = JdlUmlDataModel.extractData((JdlFile) file);
    return ContainerUtil.concat(data.getEntities(), data.getEnums());
  }

  @Override
  public Object @NotNull [] getNodeItems(JdlNodeData nodeElement, @NotNull DiagramBuilder builder) {
    return super.getNodeItems(nodeElement, builder); // todo
  }

  @Override
  public boolean canBeBuiltFrom(@Nullable Object element) {
    return element instanceof JdlFile || super.canBeBuiltFrom(element);
  }

  @Override
  public boolean isAcceptableAsNode(@Nullable Object o) {
    return o instanceof JdlEntityNodeData || o instanceof JdlEnumNodeData;
  }

  @Override
  public Object @NotNull [] getNodeItems(JdlNodeData jdlNodeData) {
    return new Object[0];
  }

  @Override
  public @Nullable @Nls String getElementTitle(JdlNodeData node) {
    return node.getName();
  }

  @Override
  public @Nullable @Nls String getNodeTooltip(JdlNodeData node) {
    return null;
  }
}
