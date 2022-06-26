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

public class JdlServiceOptionImpl extends ASTWrapperPsiElement implements JdlServiceOption {

  public JdlServiceOptionImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull JdlVisitor visitor) {
    visitor.visitServiceOption(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof JdlVisitor) accept((JdlVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public JdlEntitiesList getEntitiesList() {
    return findChildByClass(JdlEntitiesList.class);
  }

  @Override
  @Nullable
  public JdlExceptEntities getExceptEntities() {
    return findChildByClass(JdlExceptEntities.class);
  }

  @Override
  @Nullable
  public JdlWildcardLiteral getWildcardLiteral() {
    return findChildByClass(JdlWildcardLiteral.class);
  }

  @Override
  @Nullable
  public JdlWithOptionValue getWithOptionValue() {
    return findChildByClass(JdlWithOptionValue.class);
  }

}
