// This is a generated file. Not intended for manual editing.
package org.strangeway.jdl.psi.impl;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElementVisitor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.strangeway.jdl.psi.*;

public class JdlRelationshipEntityImpl extends ASTWrapperPsiElement implements JdlRelationshipEntity {

  public JdlRelationshipEntityImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull JdlVisitor visitor) {
    visitor.visitRelationshipEntity(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof JdlVisitor) accept((JdlVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public JdlId getId() {
    return findNotNullChildByClass(JdlId.class);
  }

  @Override
  @Nullable
  public JdlRelationshipDetails getRelationshipDetails() {
    return findChildByClass(JdlRelationshipDetails.class);
  }

  @Override
  @Nullable
  public JdlRelationshipOption getRelationshipOption() {
    return findChildByClass(JdlRelationshipOption.class);
  }

}
