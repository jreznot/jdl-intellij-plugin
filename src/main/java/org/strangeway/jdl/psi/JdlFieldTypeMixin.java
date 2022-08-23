package org.strangeway.jdl.psi;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiReference;
import org.jetbrains.annotations.NotNull;

public abstract class JdlFieldTypeMixin extends ASTWrapperPsiElement implements JdlFieldType {
  public JdlFieldTypeMixin(@NotNull ASTNode node) {
    super(node);
  }

  @Override
  public PsiReference getReference() {
    return new JdlFieldTypeReference(this);
  }
}
