// Copyright 2000-2020 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.
package org.strangeway.jdl.psi;

import com.intellij.extapi.psi.PsiFileBase;
import com.intellij.openapi.fileTypes.FileType;
import com.intellij.psi.FileViewProvider;
import org.jetbrains.annotations.NotNull;
import org.strangeway.jdl.JdlFileType;
import org.strangeway.jdl.JdlLanguage;

public final class JdlFile extends PsiFileBase {
  public JdlFile(@NotNull FileViewProvider viewProvider) {
    super(viewProvider, JdlLanguage.INSTANCE);
  }

  @Override
  public @NotNull FileType getFileType() {
    return JdlFileType.INSTANCE;
  }

  @Override
  public String toString() {
    return "JdlFile";
  }
}
