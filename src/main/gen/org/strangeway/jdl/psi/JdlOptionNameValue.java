// This is a generated file. Not intended for manual editing.
package org.strangeway.jdl.psi;

import com.intellij.navigation.ItemPresentation;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface JdlOptionNameValue extends PsiElement {

  @NotNull
  JdlOptionName getOptionName();

  @Nullable
  JdlValue getValue();

  @NotNull String getName();

  @NotNull JdlOptionName getNameElement();

  @NotNull ItemPresentation getPresentation();

}
