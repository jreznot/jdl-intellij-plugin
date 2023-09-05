// This is a generated file. Not intended for manual editing.
package org.strangeway.jdl.psi;

import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface JdlRelationshipEntity extends PsiElement {

  @NotNull
  JdlId getId();

  @Nullable
  JdlRelationshipDetails getRelationshipDetails();

  @Nullable
  JdlRelationshipOption getRelationshipOption();

}
