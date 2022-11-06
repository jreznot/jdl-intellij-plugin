package org.strangeway.jdl.uml;

import com.intellij.openapi.fileEditor.FileEditor;
import com.intellij.openapi.fileEditor.FileEditorPolicy;
import com.intellij.openapi.fileEditor.FileEditorProvider;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import org.strangeway.jdl.JdlFileType;

final class JdlPreviewFileEditorProvider implements FileEditorProvider {
  @Override
  public @NotNull @NonNls String getEditorTypeId() {
    return "jhipster-uml-editor";
  }

  @Override
  public @NotNull FileEditorPolicy getPolicy() {
    return FileEditorPolicy.PLACE_AFTER_DEFAULT_EDITOR;
  }

  @Override
  public boolean accept(@NotNull Project project, @NotNull VirtualFile file) {
    return file.getFileType() == JdlFileType.INSTANCE;
  }

  @Override
  public @NotNull FileEditor createEditor(@NotNull Project project, @NotNull VirtualFile file) {
    return new JdlPreviewFileEditor(project, file);
  }
}
