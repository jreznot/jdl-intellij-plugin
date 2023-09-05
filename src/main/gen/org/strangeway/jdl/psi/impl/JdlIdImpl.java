// This is a generated file. Not intended for manual editing.
package org.strangeway.jdl.psi.impl;

import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElementVisitor;
import org.jetbrains.annotations.NotNull;
import org.strangeway.jdl.psi.JdlId;
import org.strangeway.jdl.psi.JdlIdMixin;
import org.strangeway.jdl.psi.JdlVisitor;

public class JdlIdImpl extends JdlIdMixin implements JdlId {

  public JdlIdImpl(@NotNull ASTNode node) {
    super(node);
  }

  @Override
  public void accept(@NotNull JdlVisitor visitor) {
    visitor.visitId(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof JdlVisitor) accept((JdlVisitor)visitor);
    else super.accept(visitor);
  }

}
