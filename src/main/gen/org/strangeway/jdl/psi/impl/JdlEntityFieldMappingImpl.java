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

public class JdlEntityFieldMappingImpl extends ASTWrapperPsiElement implements JdlEntityFieldMapping {

  public JdlEntityFieldMappingImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull JdlVisitor visitor) {
    visitor.visitEntityFieldMapping(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof JdlVisitor) accept((JdlVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public List<JdlFieldConstraint> getFieldConstraintList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, JdlFieldConstraint.class);
  }

  @Override
  @NotNull
  public JdlFieldName getFieldName() {
    return findNotNullChildByClass(JdlFieldName.class);
  }

  @Override
  @Nullable
  public JdlFieldType getFieldType() {
    return findChildByClass(JdlFieldType.class);
  }

  @Override
  @Nullable
  public PsiElement getNewline() {
    return findChildByType(NEWLINE);
  }

}
