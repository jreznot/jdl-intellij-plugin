// This is a generated file. Not intended for manual editing.
package org.strangeway.jdl.psi.impl;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import com.intellij.navigation.ItemPresentation;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import org.jetbrains.annotations.NotNull;
import org.strangeway.jdl.psi.*;

import java.util.List;

public class JdlConfigBlockImpl extends ASTWrapperPsiElement implements JdlConfigBlock {

  public JdlConfigBlockImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull JdlVisitor visitor) {
    visitor.visitConfigBlock(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof JdlVisitor) accept((JdlVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public JdlConfigKeyword getConfigKeyword() {
    return findNotNullChildByClass(JdlConfigKeyword.class);
  }

  @Override
  @NotNull
  public List<JdlOptionNameValue> getOptionNameValueList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, JdlOptionNameValue.class);
  }

  @Override
  public @NotNull ItemPresentation getPresentation() {
    return JdlPsiUtils.getPresentation(this);
  }

}
