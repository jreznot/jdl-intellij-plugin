// This is a generated file. Not intended for manual editing.
package org.strangeway.jdl.psi;

import com.intellij.navigation.ItemPresentation;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface JdlEnumValue extends PsiElement {

  @NotNull
  JdlEnumKey getEnumKey();

  @Nullable
  JdlExplicitEnumMapping getExplicitEnumMapping();

  @NotNull String getName();

  @NotNull JdlEnumKey getNameElement();

  @NotNull ItemPresentation getPresentation();

}
