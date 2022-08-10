// This is a generated file. Not intended for manual editing.
package org.strangeway.jdl.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface JdlEntityFieldMapping extends PsiElement {

  @NotNull
  List<JdlFieldConstraint> getFieldConstraintList();

  @NotNull
  JdlFieldName getFieldName();

  @Nullable
  JdlFieldType getFieldType();

}
