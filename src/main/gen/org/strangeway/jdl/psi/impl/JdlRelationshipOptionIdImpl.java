// This is a generated file. Not intended for manual editing.
package org.strangeway.jdl.psi.impl;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElementVisitor;
import org.jetbrains.annotations.NotNull;
import org.strangeway.jdl.psi.JdlRelationshipOptionId;
import org.strangeway.jdl.psi.JdlVisitor;

public class JdlRelationshipOptionIdImpl extends ASTWrapperPsiElement implements JdlRelationshipOptionId {

  public JdlRelationshipOptionIdImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull JdlVisitor visitor) {
    visitor.visitRelationshipOptionId(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof JdlVisitor) accept((JdlVisitor)visitor);
    else super.accept(visitor);
  }

}
