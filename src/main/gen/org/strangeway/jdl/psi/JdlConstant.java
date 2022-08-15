// This is a generated file. Not intended for manual editing.
package org.strangeway.jdl.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;
import com.intellij.navigation.ItemPresentation;

public interface JdlConstant extends PsiElement {

  @NotNull
  JdlConstantName getConstantName();

  @Nullable
  JdlValue getValue();

  @NotNull String getName();

  @NotNull JdlConstantName getNameElement();

  @NotNull ItemPresentation getPresentation();

}
