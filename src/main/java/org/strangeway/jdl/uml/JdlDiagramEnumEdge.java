/*
 * Copyright © 2023 Yuriy Artamonov
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software
 * and associated documentation files (the “Software”), to deal in the Software without
 * restriction, including without limitation the rights to use, copy, modify, merge, publish,
 * distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the
 * Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies
 * or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED “AS IS”, WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING
 * BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM,
 * DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING
 * FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

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
