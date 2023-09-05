// This is a generated file. Not intended for manual editing.
package org.strangeway.jdl.psi.impl;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElementVisitor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.strangeway.jdl.psi.JdlRelationshipOption;
import org.strangeway.jdl.psi.JdlRelationshipOptionId;
import org.strangeway.jdl.psi.JdlStringLiteral;
import org.strangeway.jdl.psi.JdlVisitor;

public class JdlRelationshipOptionImpl extends ASTWrapperPsiElement implements JdlRelationshipOption {

  public JdlRelationshipOptionImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull JdlVisitor visitor) {
    visitor.visitRelationshipOption(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof JdlVisitor) accept((JdlVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public JdlRelationshipOptionId getRelationshipOptionId() {
    return findChildByClass(JdlRelationshipOptionId.class);
  }

  @Override
  @Nullable
  public JdlStringLiteral getStringLiteral() {
    return findChildByClass(JdlStringLiteral.class);
  }

}
