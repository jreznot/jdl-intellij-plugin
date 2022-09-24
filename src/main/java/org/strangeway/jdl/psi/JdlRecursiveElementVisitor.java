package org.strangeway.jdl.psi;

import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiRecursiveVisitor;
import org.jetbrains.annotations.NotNull;

public abstract class JdlRecursiveElementVisitor extends JdlVisitor implements PsiRecursiveVisitor {
  @Override
  public void visitElement(@NotNull final PsiElement element) {
    element.acceptChildren(this);
  }
}