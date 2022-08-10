// This is a generated file. Not intended for manual editing.
package org.strangeway.jdl.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface JdlEntityBlock extends PsiElement {

  @NotNull
  List<JdlEntityFieldMapping> getEntityFieldMappingList();

  @Nullable
  JdlEntityId getEntityId();

  @Nullable
  JdlEntityTableName getEntityTableName();

}
