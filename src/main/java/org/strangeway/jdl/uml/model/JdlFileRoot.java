package org.strangeway.jdl.uml.model;

import com.intellij.openapi.vfs.VirtualFile;
import org.jetbrains.annotations.Nullable;
import org.strangeway.jdl.JhipsterIcons;

import javax.swing.*;

public final class JdlFileRoot implements JdlNodeData {

  private final VirtualFile virtualFile;
  private final String name;

  public JdlFileRoot(VirtualFile virtualFile) {
    this.virtualFile = virtualFile;
    this.name = virtualFile.getName();
  }

  @Override
  public String getName() {
    return name;
  }

  @Override
  public Icon getIcon() {
    return JhipsterIcons.FILE_ICON;
  }

  public @Nullable VirtualFile getVirtualFile() {
    if (!virtualFile.isValid()) return null;
    return virtualFile;
  }
}
