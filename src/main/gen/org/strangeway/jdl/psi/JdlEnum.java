// This is a generated file. Not intended for manual editing.
package org.strangeway.jdl.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;
import com.intellij.navigation.ItemPresentation;

public interface JdlEnum extends PsiElement {

  @Nullable
  JdlEnumId getEnumId();

  @NotNull
  List<JdlEnumValue> getEnumValueList();

  @NotNull String getName();

  @Nullable JdlEnumId getNameElement();

  @NotNull ItemPresentation getPresentation();

}
