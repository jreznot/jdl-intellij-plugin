package org.strangeway.jdl.psi;

import com.intellij.lang.Language;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiElement;
import com.intellij.psi.SyntheticElement;
import com.intellij.psi.impl.FakePsiElement;
import com.intellij.psi.meta.PsiMetaData;
import com.intellij.psi.meta.PsiMetaOwner;
import com.intellij.psi.meta.PsiPresentableMetaData;
import com.intellij.psi.search.GlobalSearchScope;
import com.intellij.psi.search.SearchScope;
import com.intellij.util.PsiNavigateUtil;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.strangeway.jdl.JdlLanguage;
import org.strangeway.jdl.JhipsterIcons;

import javax.swing.*;
import java.util.Objects;

public final class JdlPredefinedSdkEntity extends FakePsiElement implements SyntheticElement, PsiMetaOwner, PsiPresentableMetaData {
  private final PsiElement parent;
  private final String entityName;

  public JdlPredefinedSdkEntity(@NotNull PsiElement parent, @NotNull String entityName) {
    this.parent = parent;
    this.entityName = entityName;
  }

  @Override
  public PsiElement getParent() {
    return parent;
  }

  @Override
  public PsiElement getDeclaration() {
    return this;
  }

  @Override
  public @Nullable TextRange getTextRange() {
    return parent.getTextRange();
  }

  @Override
  public @NonNls String getName(PsiElement context) {
    return getName();
  }

  @Override
  public String getName() {
    return entityName;
  }

  @Override
  public void init(PsiElement element) {
  }

  @Override
  public @NotNull PsiMetaData getMetaData() {
    return this;
  }

  @Override
  public @Nls @NotNull String getTypeName() {
    return "entity";
  }

  @Override
  public @Nullable Icon getIcon() {
    return JhipsterIcons.getEntityIcon();
  }

  @Override
  public @NotNull PsiElement getNavigationElement() {
    return this;
  }

  @Override
  public @NotNull SearchScope getUseScope() {
    return GlobalSearchScope.allScope(this.getProject());
  }

  @Override
  public @NotNull GlobalSearchScope getResolveScope() {
    return GlobalSearchScope.allScope(this.getProject());
  }

  @Override
  @NotNull
  public Language getLanguage() {
    return JdlLanguage.INSTANCE;
  }

  @Override
  public boolean canNavigate() {
    return true;
  }

  @Override
  public void navigate(boolean requestFocus) {
    PsiNavigateUtil.navigate(parent);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    JdlPredefinedSdkEntity that = (JdlPredefinedSdkEntity) o;
    return entityName.equals(that.entityName);
  }

  @Override
  public int hashCode() {
    return Objects.hash(entityName);
  }
}
