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

import com.intellij.openapi.fileEditor.*;
import com.intellij.openapi.fileEditor.impl.text.PsiAwareTextEditorProvider;
import com.intellij.openapi.project.DumbAware;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

final class JdlSplitEditorProvider implements FileEditorProvider, DumbAware {
  private final FileEditorProvider myFirstProvider = new PsiAwareTextEditorProvider();
  private final FileEditorProvider mySecondProvider = new JdlPreviewFileEditorProvider();

  @Override
  public @NotNull @NonNls String getEditorTypeId() {
    return "jhipster-split-editor";
  }

  @Override
  public @NotNull FileEditorPolicy getPolicy() {
    return FileEditorPolicy.HIDE_DEFAULT_EDITOR;
  }

  @Override
  public boolean accept(@NotNull Project project, @NotNull VirtualFile file) {
    return myFirstProvider.accept(project, file) && mySecondProvider.accept(project, file);
  }

  @Override
  public @NotNull FileEditor createEditor(@NotNull Project project, @NotNull VirtualFile file) {
    return createEditorAsync(project, file).build();
  }

  @NotNull
  public AsyncFileEditorProvider.Builder createEditorAsync(@NotNull final Project project, @NotNull final VirtualFile file) {
    final AsyncFileEditorProvider.Builder firstBuilder = getBuilderFromEditorProvider(myFirstProvider, project, file);
    final AsyncFileEditorProvider.Builder secondBuilder = getBuilderFromEditorProvider(mySecondProvider, project, file);

    return new AsyncFileEditorProvider.Builder() {
      @Override
      public FileEditor build() {
        return new JdlEditorWithPreview((TextEditor) firstBuilder.build(), (JdlPreviewFileEditor) secondBuilder.build());
      }
    };
  }

  @NotNull
  static AsyncFileEditorProvider.Builder getBuilderFromEditorProvider(@NotNull FileEditorProvider provider,
                                                                      @NotNull Project project,
                                                                      @NotNull VirtualFile file) {
    if (provider instanceof AsyncFileEditorProvider) {
      return ((AsyncFileEditorProvider) provider).createEditorAsync(project, file);
    } else {
      return new AsyncFileEditorProvider.Builder() {
        @Override
        public FileEditor build() {
          return provider.createEditor(project, file);
        }
      };
    }
  }
}
