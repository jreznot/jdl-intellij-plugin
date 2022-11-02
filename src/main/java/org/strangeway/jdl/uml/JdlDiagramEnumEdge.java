package org.strangeway.jdl.uml;

import com.intellij.diagram.DiagramEdgeBase;
import com.intellij.diagram.DiagramNode;
import com.intellij.diagram.DiagramRelationshipInfo;
import com.intellij.diagram.DiagramRelationshipInfoAdapter;
import com.intellij.diagram.presentation.DiagramLineType;
import org.jetbrains.annotations.NotNull;
import org.strangeway.jdl.uml.model.JdlNodeData;

final class JdlDiagramEnumEdge extends DiagramEdgeBase<JdlNodeData> {
  public JdlDiagramEnumEdge(@NotNull DiagramNode<JdlNodeData> source, @NotNull DiagramNode<JdlNodeData> target) {
    super(source, target, USE_ENUM);
  }

  static final DiagramRelationshipInfo USE_ENUM = (new DiagramRelationshipInfoAdapter.Builder()).setName("TO_ONE")
      .setLineType(DiagramLineType.DASHED)
      .setSourceArrow(DiagramRelationshipInfo.NONE)
      .setTargetArrow(DiagramRelationshipInfo.ANGLE)
      .create();
}
