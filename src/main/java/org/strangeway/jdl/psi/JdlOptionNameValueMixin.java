package org.strangeway.jdl.psi;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.util.IncorrectOperationException;
import org.jetbrains.annotations.NotNull;

public abstract class JdlOptionNameValueMixin extends ASTWrapperPsiElement implements JdlOptionNameValue {

  public JdlOptionNameValueMixin(@NotNull ASTNode node) {
    super(node);
  }

  @Override
  public PsiElement setName(@NotNull String name) throws IncorrectOperationException {
    // todo implement
    return null;
  }
}
