// This is a generated file. Not intended for manual editing.
package org.strangeway.jdl.psi.impl;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import com.intellij.navigation.ItemPresentation;
import com.intellij.psi.PsiElementVisitor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.strangeway.jdl.psi.*;

public class JdlEnumValueImpl extends ASTWrapperPsiElement implements JdlEnumValue {

  public JdlEnumValueImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull JdlVisitor visitor) {
    visitor.visitEnumValue(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof JdlVisitor) accept((JdlVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public JdlEnumKey getEnumKey() {
    return findNotNullChildByClass(JdlEnumKey.class);
  }

  @Override
  @Nullable
  public JdlExplicitEnumMapping getExplicitEnumMapping() {
    return findChildByClass(JdlExplicitEnumMapping.class);
  }

  @Override
  public @NotNull String getName() {
    return JdlPsiUtils.getName(this);
  }

  @Override
  public @NotNull JdlEnumKey getNameElement() {
    return JdlPsiUtils.getNameElement(this);
  }

  @Override
  public @NotNull ItemPresentation getPresentation() {
    return JdlPsiUtils.getPresentation(this);
  }

}
