package org.strangeway.jdl.uml;

import com.intellij.diagram.AbstractDiagramNodeContentManager;
import com.intellij.diagram.DiagramCategory;
import org.jetbrains.annotations.NotNull;

final class JdlUmlCategoryManager extends AbstractDiagramNodeContentManager {
  @Override
  public DiagramCategory @NotNull [] getContentCategories() {
    return new DiagramCategory[0]; // todo
  }
}
