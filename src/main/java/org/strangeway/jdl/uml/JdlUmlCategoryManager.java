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
