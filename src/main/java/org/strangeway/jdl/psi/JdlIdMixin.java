package org.strangeway.jdl.psi;

import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiReference;
import org.jetbrains.annotations.NotNull;
import org.strangeway.jdl.psi.impl.JdlValueImpl;

public abstract class JdlIdMixin extends JdlValueImpl implements JdlId {
  public JdlIdMixin(@NotNull ASTNode node) {
    super(node);
  }

  @Override
  public PsiReference getReference() {
    PsiElement parent = getParent();
    if (parent instanceof JdlEntitiesList
        || parent instanceof JdlRelationshipEntity) {
      return new JdlEntityIdReference(this);
    }
    if (parent instanceof JdlFieldConstraintParameters) {
      return new JdlConstantNameReference(this);
    }
    return null;
  }
}
