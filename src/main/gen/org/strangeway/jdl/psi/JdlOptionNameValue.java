// This is a generated file. Not intended for manual editing.
package org.strangeway.jdl.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;
import com.intellij.navigation.ItemPresentation;

public interface JdlOptionNameValue extends PsiElement {

  @NotNull
  JdlOptionName getOptionName();

  @Nullable
  JdlValue getValue();

  @NotNull String getName();

  JdlOptionName getNameElement();

  @NotNull ItemPresentation getPresentation();

}
