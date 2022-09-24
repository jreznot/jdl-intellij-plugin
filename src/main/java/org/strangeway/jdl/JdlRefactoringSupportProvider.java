package org.strangeway.jdl;

import com.intellij.lang.refactoring.RefactoringSupportProvider;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.strangeway.jdl.psi.JdlConstant;
import org.strangeway.jdl.psi.JdlEntity;
import org.strangeway.jdl.psi.JdlEnum;

final class JdlRefactoringSupportProvider extends RefactoringSupportProvider {
  @Override
  public boolean isInplaceRenameAvailable(@NotNull PsiElement element, PsiElement context) {
    return element instanceof JdlEntity
        || element instanceof JdlEnum
        || element instanceof JdlConstant;
  }

  @Override
  public boolean isMemberInplaceRenameAvailable(@NotNull PsiElement element, @Nullable PsiElement context) {
    return isInplaceRenameAvailable(element, context);
  }
}
