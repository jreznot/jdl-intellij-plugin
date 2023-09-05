// This is a generated file. Not intended for manual editing.
package org.strangeway.jdl.psi;

import com.intellij.navigation.ItemPresentation;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface JdlConfigurationOption extends PsiElement {

  @NotNull
  JdlConfigurationOptionName getConfigurationOptionName();

  @Nullable
  JdlEntitiesList getEntitiesList();

  @Nullable
  JdlExceptEntities getExceptEntities();

  @Nullable
  JdlWildcardLiteral getWildcardLiteral();

  @Nullable
  JdlWithOptionValue getWithOptionValue();

  @NotNull String getName();

  @NotNull JdlConfigurationOptionName getNameElement();

  @NotNull ItemPresentation getPresentation();

}
