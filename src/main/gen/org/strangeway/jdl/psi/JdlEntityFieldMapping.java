// This is a generated file. Not intended for manual editing.
package org.strangeway.jdl.psi;

import com.intellij.navigation.ItemPresentation;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public interface JdlEntityFieldMapping extends PsiElement {

  @NotNull
  List<JdlAnnotation> getAnnotationList();

  @NotNull
  List<JdlFieldConstraint> getFieldConstraintList();

  @NotNull
  JdlFieldName getFieldName();

  @Nullable
  JdlFieldType getFieldType();

  @NotNull String getName();

  @Nullable String getType();

  JdlFieldName getNameElement();

  @NotNull ItemPresentation getPresentation();

  boolean isRequired();

}
