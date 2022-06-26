// This is a generated file. Not intended for manual editing.
package org.strangeway.jdl.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface JdlValue extends PsiElement {

  @Nullable
  JdlArrayLiteral getArrayLiteral();

  @Nullable
  JdlBooleanLiteral getBooleanLiteral();

  @Nullable
  JdlId getId();

  @Nullable
  JdlNumberLiteral getNumberLiteral();

  @Nullable
  JdlPatternCall getPatternCall();

  @Nullable
  JdlStringLiteral getStringLiteral();

}
