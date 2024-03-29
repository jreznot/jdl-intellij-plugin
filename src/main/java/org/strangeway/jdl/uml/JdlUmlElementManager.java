/*
 * Copyright © 2023 Yuriy Artamonov
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software
 * and associated documentation files (the "Software"), to deal in the Software without
 * restriction, including without limitation the rights to use, copy, modify, merge, publish,
 * distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the
 * Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies
 * or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING
 * BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM,
 * DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING
 * FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package org.strangeway.jdl.uml;

import com.intellij.diagram.AbstractDiagramElementManager;
import com.intellij.diagram.DiagramBuilder;
import com.intellij.openapi.actionSystem.DataContext;
import com.intellij.openapi.actionSystem.PlatformCoreDataKeys;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.openapi.vfs.pointers.VirtualFilePointerManager;
import com.intellij.ui.SimpleColoredText;
import com.intellij.ui.SimpleTextAttributes;
import com.intellij.util.ArrayUtil;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.strangeway.jdl.JhipsterIcons;
import org.strangeway.jdl.psi.JdlFile;
import org.strangeway.jdl.uml.model.*;

import javax.swing.*;

final class JdlUmlElementManager extends AbstractDiagramElementManager<JdlNodeData> {
  @Override
  public @Nullable JdlNodeData findInDataContext(@NotNull DataContext dataContext) {
    var file = PlatformCoreDataKeys.PSI_FILE.getData(dataContext);
    if (!(file instanceof JdlFile)) return null;

    var virtualFile = file.getVirtualFile();
    if (virtualFile == null) return null;

    return getRootData(file.getProject(), virtualFile);
  }

  @NotNull
  static JdlDiagramRootData getRootData(Project project, VirtualFile virtualFile) {
    var disposable = project.getService(JdlDiagramService.class);
    var filePointer = VirtualFilePointerManager.getInstance().create(virtualFile, disposable, null);

    return new JdlDiagramRootData(filePointer);
  }

  @Override
  public Object @NotNull [] getNodeItems(JdlNodeData nodeElement) {
    if (nodeElement instanceof JdlEntityNodeData) {
      return ((JdlEntityNodeData) nodeElement).getProperties().toArray();
    }

    if (nodeElement instanceof JdlEnumNodeData) {
      return ((JdlEnumNodeData) nodeElement).getOptions().toArray();
    }

    return ArrayUtil.EMPTY_OBJECT_ARRAY;
  }

  @Override
  public boolean canBeBuiltFrom(@Nullable Object element) {
    return element instanceof JdlDiagramRootData || super.canBeBuiltFrom(element);
  }

  @Override
  public boolean isAcceptableAsNode(@Nullable Object o) {
    return o instanceof JdlEntityNodeData || o instanceof JdlEnumNodeData;
  }

  @Override
  public @Nullable SimpleColoredText getItemName(@Nullable JdlNodeData nodeElement, @Nullable Object nodeItem, @NotNull DiagramBuilder builder) {
    if (nodeItem instanceof JdlEntityNodeField) {
      return new SimpleColoredText(((JdlEntityNodeField) nodeItem).getName(), SimpleTextAttributes.REGULAR_ATTRIBUTES);
    }
    if (nodeItem instanceof JdlEnumNodeItem) {
      return new SimpleColoredText(((JdlEnumNodeItem) nodeItem).getName(), SimpleTextAttributes.REGULAR_ATTRIBUTES);
    }

    return null;
  }

  @Override
  public @Nullable SimpleColoredText getItemType(@Nullable JdlNodeData nodeElement, @Nullable Object nodeItem, @Nullable DiagramBuilder builder) {
    if (nodeItem instanceof JdlEntityNodeField) {
      String type = ((JdlEntityNodeField) nodeItem).getType();
      if (type == null) return null;

      return new SimpleColoredText(type, SimpleTextAttributes.REGULAR_ATTRIBUTES);
    }
    return null;
  }

  @Override
  public @Nullable Icon getItemIcon(@Nullable JdlNodeData nodeElement, @Nullable Object nodeItem, @Nullable DiagramBuilder builder) {
    if (nodeItem instanceof JdlEntityNodeField) {
      if (((JdlEntityNodeField) nodeItem).isRequired()) {
        return JhipsterIcons.getRequiredFieldIcon();
      }
      return JhipsterIcons.getFieldIcon();
    }
    if (nodeItem instanceof JdlEnumNodeItem) {
      return JhipsterIcons.getFieldIcon();
    }
    return null;
  }

  @Override
  public @Nullable @Nls String getElementTitle(JdlNodeData node) {
    return node.getName();
  }

  @Override
  public @Nullable @Nls String getNodeTooltip(JdlNodeData node) {
    return null;
  }
}
