package org.strangeway.jdl.psi;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import com.intellij.navigation.ItemPresentation;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.impl.source.tree.LeafElement;
import com.intellij.psi.search.LocalSearchScope;
import com.intellij.psi.search.SearchScope;
import com.intellij.util.IncorrectOperationException;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.strangeway.jdl.JhipsterIcons;

import javax.swing.*;

public abstract class JdlEntityMixin extends ASTWrapperPsiElement implements JdlEntity {
  public JdlEntityMixin(@NotNull ASTNode node) {
    super(node);
  }

  @Override
  public @NotNull String getName() {
    JdlEntityId enumId = getEntityId();
    if (enumId == null) return "";

    return enumId.getText();
  }

  @Override
  public @Nullable JdlEntityId getNameIdentifier() {
    return getEntityId();
  }

  @Override
  public int getTextOffset() {
    PsiElement name = getNameIdentifier();
    return name != null ? name.getTextOffset() : super.getTextOffset();
  }

  @Override
  protected @Nullable Icon getElementIcon(int flags) {
    return JhipsterIcons.getEntityIcon();
  }

  @Override
  public PsiElement setName(@NotNull String name) throws IncorrectOperationException {
    if (getEntityId() != null) {
      ASTNode node = getEntityId().getNode();
      ((LeafElement) node.getFirstChildNode()).replaceWithText(name);
    }
    return this;
  }

  @Override
  public @NotNull SearchScope getUseScope() {
    PsiFile containingFile = getContainingFile();
    if (containingFile != null) {
      return new LocalSearchScope(containingFile);
    }
    return super.getUseScope();
  }

  @Override
  public @NotNull ItemPresentation getPresentation() {
    return new ItemPresentation() {
      @Override
      public @NotNull String getPresentableText() {
        return getName();
      }

      @Override
      public @NotNull Icon getIcon(boolean unused) {
        return JhipsterIcons.getEntityIcon();
      }
    };
  }
}
