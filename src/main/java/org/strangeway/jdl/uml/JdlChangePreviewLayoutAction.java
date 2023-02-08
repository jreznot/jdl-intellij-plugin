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

import com.intellij.openapi.actionSystem.ActionUpdateThread;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.ToggleAction;
import com.intellij.openapi.fileEditor.TextEditorWithPreview;
import com.intellij.openapi.project.DumbAware;
import org.jetbrains.annotations.NotNull;

import static org.strangeway.jdl.uml.JdlEditorWithPreview.findSplitEditor;

abstract class JdlChangePreviewLayoutAction extends ToggleAction implements DumbAware {

  private final TextEditorWithPreview.Layout layout;

  JdlChangePreviewLayoutAction(TextEditorWithPreview.Layout layout) {
    super(layout.getName(), layout.getName(), layout.getIcon(null));
    this.layout = layout;
  }

  @Override
  public @NotNull ActionUpdateThread getActionUpdateThread() {
    return ActionUpdateThread.EDT;
  }

  @Override
  public boolean isSelected(@NotNull AnActionEvent e) {
    var editor = findSplitEditor(e);
    if (editor == null) return false;

    return editor.getLayout() == layout;
  }

  @Override
  public void setSelected(@NotNull AnActionEvent e, boolean state) {
    var editor = findSplitEditor(e);
    if (editor == null) return;

    editor.setLayout(layout);
  }

  @Override
  public void update(@NotNull AnActionEvent e) {
    super.update(e);

    var editor = findSplitEditor(e);
    if (editor == null) return;
    e.getPresentation().setIcon(layout.getIcon(editor));
  }

  static class EditorOnly extends JdlChangePreviewLayoutAction {
    EditorOnly() {
      super(TextEditorWithPreview.Layout.SHOW_EDITOR);
    }
  }

  static class EditorAndPreview extends JdlChangePreviewLayoutAction {
    EditorAndPreview() {
      super(TextEditorWithPreview.Layout.SHOW_EDITOR_AND_PREVIEW);
    }
  }

  static class PreviewOnly extends JdlChangePreviewLayoutAction {
    PreviewOnly() {
      super(TextEditorWithPreview.Layout.SHOW_PREVIEW);
    }
  }
}
