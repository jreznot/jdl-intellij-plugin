package org.strangeway.jdl.psi;

import com.intellij.openapi.util.TextRange;
import com.intellij.psi.AbstractElementManipulator;
import com.intellij.util.IncorrectOperationException;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

final class JdlIdManipulator extends AbstractElementManipulator<JdlId> {
  @Override
  public @Nullable JdlId handleContentChange(@NotNull JdlId element, @NotNull TextRange range,
                                             String newContent) throws IncorrectOperationException {
    // todo rename
    return null;
  }
}
