package org.strangeway.jdl.uml;

import com.intellij.diagram.DiagramCategory;
import com.intellij.diagram.DiagramRelationshipInfo;
import com.intellij.diagram.DiagramRelationshipManager;
import org.jetbrains.annotations.Nullable;
import org.strangeway.jdl.uml.model.JdlDiagramNode;

final class JdlUmlRelationshipManager implements DiagramRelationshipManager<JdlDiagramNode> {
  @Override
  public @Nullable DiagramRelationshipInfo getDependencyInfo(JdlDiagramNode jdlDiagramNode, JdlDiagramNode t1, DiagramCategory diagramCategory) {
    return null;
  }
}
