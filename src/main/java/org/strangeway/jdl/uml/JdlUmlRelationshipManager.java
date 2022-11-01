package org.strangeway.jdl.uml;

import com.intellij.diagram.DiagramCategory;
import com.intellij.diagram.DiagramRelationshipInfo;
import com.intellij.diagram.DiagramRelationshipManager;
import org.jetbrains.annotations.Nullable;
import org.strangeway.jdl.uml.model.JdlNodeData;

final class JdlUmlRelationshipManager implements DiagramRelationshipManager<JdlNodeData> {
  @Override
  public @Nullable DiagramRelationshipInfo getDependencyInfo(JdlNodeData from, JdlNodeData to, DiagramCategory diagramCategory) {
    return null;
  }
}
