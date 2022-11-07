package org.strangeway.jdl.uml;

import com.intellij.diagram.AbstractDiagramNodeContentManager;
import com.intellij.diagram.DiagramBuilder;
import com.intellij.diagram.DiagramCategory;
import com.intellij.icons.AllIcons;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.strangeway.jdl.uml.model.JdlEntityNodeField;
import org.strangeway.jdl.uml.model.JdlEnumNodeItem;

final class JdlUmlCategoryManager extends AbstractDiagramNodeContentManager {
  private static final @NotNull DiagramCategory FIELDS = new DiagramCategory(() -> "Fields", AllIcons.Nodes.Field, true, false);
  private static final @NotNull DiagramCategory ENUM_VALUES = new DiagramCategory(() -> "Enum Values", AllIcons.Nodes.Enum, true, false);

  private static final DiagramCategory[] CATEGORIES = new DiagramCategory[]{ENUM_VALUES, FIELDS};

  @Override
  public DiagramCategory @NotNull [] getContentCategories() {
    return CATEGORIES;
  }

  @Override
  public boolean isInCategory(@Nullable Object nodeElement, @Nullable Object item,
                              @NotNull DiagramCategory category, @Nullable DiagramBuilder builder) {
    if (nodeElement instanceof JdlEntityNodeField) {
      return category == FIELDS;
    }
    if (nodeElement instanceof JdlEnumNodeItem) {
      return category == ENUM_VALUES;
    }
    return super.isInCategory(nodeElement, item, category, builder);
  }
}
