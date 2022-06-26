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

public class JdlRelationshipMappingImpl extends ASTWrapperPsiElement implements JdlRelationshipMapping {

  public JdlRelationshipMappingImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull JdlVisitor visitor) {
    visitor.visitRelationshipMapping(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof JdlVisitor) accept((JdlVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public List<JdlEntityId> getEntityIdList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, JdlEntityId.class);
  }

  @Override
  @Nullable
  public JdlWithOptionValue getWithOptionValue() {
    return findChildByClass(JdlWithOptionValue.class);
  }

}
