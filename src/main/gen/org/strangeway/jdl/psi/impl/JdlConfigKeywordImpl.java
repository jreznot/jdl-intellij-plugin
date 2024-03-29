// This is a generated file. Not intended for manual editing.
package org.strangeway.jdl.psi.impl;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElementVisitor;
import org.jetbrains.annotations.NotNull;
import org.strangeway.jdl.psi.JdlConfigKeyword;
import org.strangeway.jdl.psi.JdlVisitor;

public class JdlConfigKeywordImpl extends ASTWrapperPsiElement implements JdlConfigKeyword {

  public JdlConfigKeywordImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull JdlVisitor visitor) {
    visitor.visitConfigKeyword(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof JdlVisitor) accept((JdlVisitor)visitor);
    else super.accept(visitor);
  }

}
