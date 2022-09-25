package org.strangeway.jdl.uml;

import com.intellij.diagram.DiagramVfsResolver;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.strangeway.jdl.uml.model.JdlDiagramNode;

final class JdlUmlVfsResolver implements DiagramVfsResolver<JdlDiagramNode> {
  @Override
  public @Nullable String getQualifiedName(@Nullable JdlDiagramNode jdlDiagramNode) {
    return null;
  }

  @Override
  public @Nullable JdlDiagramNode resolveElementByFQN(@NotNull String s, @NotNull Project project) {
    return null;
  }
}
