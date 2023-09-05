// This is a generated file. Not intended for manual editing.
package org.strangeway.jdl.psi.impl;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import com.intellij.navigation.ItemPresentation;
import com.intellij.psi.PsiElementVisitor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.strangeway.jdl.psi.*;

public class JdlOptionNameValueImpl extends ASTWrapperPsiElement implements JdlOptionNameValue {

  public JdlOptionNameValueImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull JdlVisitor visitor) {
    visitor.visitOptionNameValue(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof JdlVisitor) accept((JdlVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public JdlOptionName getOptionName() {
    return findNotNullChildByClass(JdlOptionName.class);
  }

  @Override
  @Nullable
  public JdlValue getValue() {
    return findChildByClass(JdlValue.class);
  }

  @Override
  public @NotNull String getName() {
    return JdlPsiUtils.getName(this);
  }

  @Override
  public @NotNull JdlOptionName getNameElement() {
    return JdlPsiUtils.getNameElement(this);
  }

  @Override
  public @NotNull ItemPresentation getPresentation() {
    return JdlPsiUtils.getPresentation(this);
  }

}
