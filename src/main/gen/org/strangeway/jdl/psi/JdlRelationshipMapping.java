// This is a generated file. Not intended for manual editing.
package org.strangeway.jdl.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface JdlRelationshipMapping extends PsiElement {

  @NotNull
  List<JdlRelationshipEntity> getRelationshipEntityList();

  @Nullable
  JdlWithOptionValue getWithOptionValue();

  @Nullable
  PsiElement getNewline();

}
