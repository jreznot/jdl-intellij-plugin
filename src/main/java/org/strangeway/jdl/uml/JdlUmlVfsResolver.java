package org.strangeway.jdl.uml;

import com.intellij.diagram.DiagramVfsResolver;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.strangeway.jdl.uml.model.JdlNodeData;

final class JdlUmlVfsResolver implements DiagramVfsResolver<JdlNodeData> {
  @Override
  public @Nullable String getQualifiedName(@Nullable JdlNodeData data) {
    if (data == null) return null;

    String name = data.getName();
    return name != null ? name : "";
  }

  @Override
  public @Nullable JdlNodeData resolveElementByFQN(@NotNull String s, @NotNull Project project) {
    return null;
  }
}
