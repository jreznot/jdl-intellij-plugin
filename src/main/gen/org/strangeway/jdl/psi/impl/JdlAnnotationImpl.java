// This is a generated file. Not intended for manual editing.
package org.strangeway.jdl.psi.impl;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElementVisitor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.strangeway.jdl.psi.JdlAnnotation;
import org.strangeway.jdl.psi.JdlAnnotationId;
import org.strangeway.jdl.psi.JdlAnnotationValue;
import org.strangeway.jdl.psi.JdlVisitor;

public class JdlAnnotationImpl extends ASTWrapperPsiElement implements JdlAnnotation {

  public JdlAnnotationImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull JdlVisitor visitor) {
    visitor.visitAnnotation(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof JdlVisitor) accept((JdlVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public JdlAnnotationId getAnnotationId() {
    return findChildByClass(JdlAnnotationId.class);
  }

  @Override
  @Nullable
  public JdlAnnotationValue getAnnotationValue() {
    return findChildByClass(JdlAnnotationValue.class);
  }

}
