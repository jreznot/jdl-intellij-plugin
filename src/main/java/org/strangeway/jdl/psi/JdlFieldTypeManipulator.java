package org.strangeway.jdl.psi;

import com.intellij.openapi.util.TextRange;
import com.intellij.psi.AbstractElementManipulator;
import com.intellij.util.IncorrectOperationException;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

final class JdlFieldTypeManipulator extends AbstractElementManipulator<JdlFieldType> {
  @Override
  public @Nullable JdlFieldType handleContentChange(@NotNull JdlFieldType element, @NotNull TextRange range,
                                                    String newContent) throws IncorrectOperationException {
    // todo rename
    return null;
  }
}
