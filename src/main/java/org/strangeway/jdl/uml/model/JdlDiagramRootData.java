package org.strangeway.jdl.uml.model;

import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.openapi.vfs.pointers.VirtualFilePointer;
import org.jetbrains.annotations.Nullable;
import org.strangeway.jdl.JhipsterIcons;

import javax.swing.*;

public final class JdlDiagramRootData implements JdlNodeData {

  private final VirtualFilePointer virtualFile;
  private final String name;

  public JdlDiagramRootData(VirtualFilePointer virtualFile) {
    this.virtualFile = virtualFile;
    this.name = virtualFile.getFileName();
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
    return virtualFile.getFile();
  }
}
