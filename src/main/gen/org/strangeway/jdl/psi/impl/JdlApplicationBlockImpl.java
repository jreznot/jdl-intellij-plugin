// This is a generated file. Not intended for manual editing.
package org.strangeway.jdl.psi.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import static org.strangeway.jdl.psi.JdlTokenTypes.*;
import com.intellij.extapi.psi.ASTWrapperPsiElement;
import org.strangeway.jdl.psi.*;

public class JdlApplicationBlockImpl extends ASTWrapperPsiElement implements JdlApplicationBlock {

  public JdlApplicationBlockImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull JdlVisitor visitor) {
    visitor.visitApplicationBlock(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof JdlVisitor) accept((JdlVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public JdlConfigBlock getConfigBlock() {
    return findChildByClass(JdlConfigBlock.class);
  }

  @Override
  @NotNull
  public List<JdlDtoOption> getDtoOptionList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, JdlDtoOption.class);
  }

  @Override
  @NotNull
  public List<JdlEntitiesOption> getEntitiesOptionList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, JdlEntitiesOption.class);
  }

  @Override
  @NotNull
  public List<JdlPaginateOption> getPaginateOptionList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, JdlPaginateOption.class);
  }

  @Override
  @NotNull
  public List<JdlServiceOption> getServiceOptionList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, JdlServiceOption.class);
  }

}
