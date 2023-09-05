// This is a generated file. Not intended for manual editing.
package org.strangeway.jdl.psi.impl;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElementVisitor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.strangeway.jdl.psi.JdlAnnotationValue;
import org.strangeway.jdl.psi.JdlValue;
import org.strangeway.jdl.psi.JdlVisitor;
import org.strangeway.jdl.psi.JdlWithOptionValue;

public class JdlAnnotationValueImpl extends ASTWrapperPsiElement implements JdlAnnotationValue {

  public JdlAnnotationValueImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull JdlVisitor visitor) {
    visitor.visitAnnotationValue(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof JdlVisitor) accept((JdlVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public JdlValue getValue() {
    return findChildByClass(JdlValue.class);
  }

  @Override
  @Nullable
  public JdlWithOptionValue getWithOptionValue() {
    return findChildByClass(JdlWithOptionValue.class);
  }

}
