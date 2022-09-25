package org.strangeway.jdl.uml;

import com.intellij.diagram.DiagramDataModel;
import com.intellij.diagram.DiagramEdge;
import com.intellij.diagram.DiagramNode;
import com.intellij.diagram.DiagramProvider;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.ModificationTracker;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.strangeway.jdl.psi.JdlFile;
import org.strangeway.jdl.uml.model.JdlDiagramData;
import org.strangeway.jdl.uml.model.JdlDiagramNode;

import java.util.Collection;
import java.util.List;

final class JdlUmlDataModel extends DiagramDataModel<JdlDiagramNode> {
  public JdlUmlDataModel(@NotNull Project project, @NotNull DiagramProvider<JdlDiagramNode> provider) {
    super(project, provider);
  }

  @Override
  public @NotNull ModificationTracker getModificationTracker() {
    return null;
  }

  @Override
  public @NotNull Collection<? extends DiagramNode<JdlDiagramNode>> getNodes() {
    return null;
  }

  @Override
  public @NotNull String getNodeName(@NotNull DiagramNode<JdlDiagramNode> diagramNode) {
    return null;
  }

  @Override
  public @Nullable DiagramNode<JdlDiagramNode> addElement(@Nullable JdlDiagramNode JdlDiagramNode) {
    return null;
  }

  @Override
  public @NotNull Collection<? extends DiagramEdge<JdlDiagramNode>> getEdges() {
    return null;
  }

  @Override
  public void dispose() {

  }

  public static @NotNull JdlDiagramData extractData(@NotNull JdlFile file) {
    return new JdlDiagramData(List.of(), List.of(), List.of(), List.of());
  }
}
