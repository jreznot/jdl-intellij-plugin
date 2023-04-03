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

import com.intellij.openapi.actionSystem.*;
import com.intellij.openapi.fileEditor.*;
import com.intellij.openapi.util.Key;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

public final class JdlEditorWithPreview extends TextEditorWithPreview {
  public static final Key<JdlEditorWithPreview> PARENT_SPLIT_EDITOR_KEY = Key.create("jdl.parentSplit");

  public JdlEditorWithPreview(@NotNull TextEditor editor, @NotNull JdlPreviewFileEditor preview) {
    super(
        editor,
        preview,
        "JDL Preview",
        Layout.SHOW_EDITOR_AND_PREVIEW,
        false
    );

    editor.putUserData(PARENT_SPLIT_EDITOR_KEY, this);
    preview.putUserData(PARENT_SPLIT_EDITOR_KEY, this);

    preview.setMainEditor(editor.getEditor());

    // todo subscribe to settings change
  }

  @Override
  protected void onLayoutChange(Layout oldValue, Layout newValue) {
    super.onLayoutChange(oldValue, newValue);
    // Editor tab will lose focus after switching to JCEF preview for some reason.
    // So we should explicitly request focus for our editor here.
    if (newValue == Layout.SHOW_PREVIEW) {
      requestFocusForPreview();
    }
  }

  private void requestFocusForPreview() {
    final var preferredComponent = myPreview.getPreferredFocusedComponent();
    if (preferredComponent != null) {
      preferredComponent.requestFocus();
      return;
    }
    myPreview.getComponent().requestFocus();
  }

  @Override
  public void setLayout(@NotNull Layout layout) {
    super.setLayout(layout);
  }

  @Override
  public void setState(@NotNull FileEditorState state) {
    if (state instanceof JdlEditorWithPreviewState actualState) {
      super.setState(actualState.getUnderlyingState());
    }
  }

  @Override
  public @NotNull FileEditorState getState(@NotNull FileEditorStateLevel level) {
    final var underlyingState = super.getState(level);
    return new JdlEditorWithPreviewState(underlyingState);
  }

  @Override
  protected @NotNull ToggleAction getShowEditorAction() {
    return (ToggleAction) Objects.requireNonNull(ActionManager.getInstance().getAction("JDL.Layout.EditorOnly"));
  }

  @Override
  protected @NotNull ToggleAction getShowEditorAndPreviewAction() {
    return (ToggleAction) Objects.requireNonNull(ActionManager.getInstance().getAction("JDL.Layout.EditorAndPreview"));
  }

  @Override
  protected @NotNull ToggleAction getShowPreviewAction() {
    return (ToggleAction) Objects.requireNonNull(ActionManager.getInstance().getAction("JDL.Layout.PreviewOnly"));
  }

  static @Nullable JdlEditorWithPreview findSplitEditor(AnActionEvent event) {
    FileEditor fileEditor = event.getData(PlatformCoreDataKeys.FILE_EDITOR);
    if (fileEditor == null) return null;

    if (fileEditor instanceof JdlEditorWithPreview) {
      return (JdlEditorWithPreview) fileEditor;
    }

    return fileEditor.getUserData(PARENT_SPLIT_EDITOR_KEY);
  }

  @NotNull
  protected ActionGroup createViewActionGroup() {
    return new DefaultActionGroup(
        getShowEditorAction(),
        getShowEditorAndPreviewAction(),
        getShowPreviewAction(),
        ActionManager.getInstance().getAction("JDL.Generate")
    );
  }
}
