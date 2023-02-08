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

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import com.intellij.navigation.ItemPresentation;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.impl.source.tree.LeafElement;
import com.intellij.psi.search.LocalSearchScope;
import com.intellij.psi.search.SearchScope;
import com.intellij.util.IncorrectOperationException;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.strangeway.jdl.JhipsterIcons;

import javax.swing.*;

public abstract class JdlConstantMixin extends ASTWrapperPsiElement implements JdlConstant {
  public JdlConstantMixin(@NotNull ASTNode node) {
    super(node);
  }

  @Override
  public @NotNull String getName() {
    return getConstantName().getText();
  }

  @Override
  public @NotNull JdlConstantName getNameIdentifier() {
    return getConstantName();
  }

  @Override
  public int getTextOffset() {
    PsiElement name = getNameIdentifier();
    return name.getTextOffset();
  }

  @Override
  protected @Nullable Icon getElementIcon(int flags) {
    return JhipsterIcons.getConstantIcon();
  }

  @Override
  public PsiElement setName(@NotNull String name) throws IncorrectOperationException {
    ASTNode node = getConstantName().getNode();
    ((LeafElement) node.getFirstChildNode()).replaceWithText(name);
    return this;
  }

  @Override
  public @NotNull SearchScope getUseScope() {
    PsiFile containingFile = getContainingFile();
    if (containingFile != null) {
      return new LocalSearchScope(containingFile);
    }
    return super.getUseScope();
  }

  @Override
  public @NotNull ItemPresentation getPresentation() {
    return new ItemPresentation() {
      @Override
      public @NotNull String getPresentableText() {
        return getName();
      }

      @Override
      public @NotNull Icon getIcon(boolean unused) {
        return JhipsterIcons.getEntityIcon();
      }
    };
  }
}
