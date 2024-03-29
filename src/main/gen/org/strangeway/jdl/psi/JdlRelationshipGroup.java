// This is a generated file. Not intended for manual editing.
package org.strangeway.jdl.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;
import com.intellij.navigation.ItemPresentation;

public interface JdlRelationshipGroup extends PsiElement {

  @NotNull
  List<JdlRelationshipMapping> getRelationshipMappingList();

  @Nullable
  JdlRelationshipType getRelationshipType();

  @NotNull String getType();

  @NotNull ItemPresentation getPresentation();

}
