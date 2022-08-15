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
import com.intellij.navigation.ItemPresentation;

public class JdlEntityImpl extends ASTWrapperPsiElement implements JdlEntity {

  public JdlEntityImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull JdlVisitor visitor) {
    visitor.visitEntity(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof JdlVisitor) accept((JdlVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public List<JdlEntityFieldMapping> getEntityFieldMappingList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, JdlEntityFieldMapping.class);
  }

  @Override
  @Nullable
  public JdlEntityId getEntityId() {
    return findChildByClass(JdlEntityId.class);
  }

  @Override
  @Nullable
  public JdlEntityTableName getEntityTableName() {
    return findChildByClass(JdlEntityTableName.class);
  }

  @Override
  public @NotNull String getName() {
    return JdlPsiUtils.getName(this);
  }

  @Override
  public @Nullable JdlEntityId getNameElement() {
    return JdlPsiUtils.getNameElement(this);
  }

  @Override
  public @NotNull ItemPresentation getPresentation() {
    return JdlPsiUtils.getPresentation(this);
  }

}
