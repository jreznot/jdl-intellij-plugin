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
package org.strangeway.jdl.psi;

import com.intellij.extapi.psi.PsiFileBase;
import com.intellij.lang.ASTNode;
import com.intellij.openapi.fileTypes.FileType;
import com.intellij.psi.FileViewProvider;
import com.intellij.psi.PsiElement;
import com.intellij.psi.ResolveState;
import com.intellij.psi.scope.PsiScopeProcessor;
import com.intellij.psi.util.CachedValueProvider.Result;
import com.intellij.psi.util.CachedValuesManager;
import com.intellij.psi.util.PsiModificationTracker;
import org.jetbrains.annotations.NotNull;
import org.strangeway.jdl.JdlFileType;
import org.strangeway.jdl.JdlLanguage;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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

  @Override
  public boolean processDeclarations(@NotNull PsiScopeProcessor processor, @NotNull ResolveState state,
                                     PsiElement lastParent, @NotNull PsiElement place) {
    List<PsiElement> nodes = getDeclarations();
    for (PsiElement node : nodes) {
      if (!processor.execute(node, state)) return false;
    }
    return true;
  }

  public List<PsiElement> getDeclarations() {
    return CachedValuesManager.getCachedValue(this, () ->
        Result.create(findDeclarations(this), PsiModificationTracker.MODIFICATION_COUNT)
    );
  }

  private static List<PsiElement> findDeclarations(@NotNull JdlFile file) {
    ASTNode[] nodes = file.getNode().getChildren(JdlTokenSets.DECLARATIONS);
    return Arrays.stream(nodes)
        .map(ASTNode::getPsi)
        .collect(Collectors.toList());
  }
}
