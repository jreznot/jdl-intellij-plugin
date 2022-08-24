package org.strangeway.jdl.psi;

import com.intellij.lang.ASTNode;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.AbstractElementManipulator;
import com.intellij.psi.impl.source.tree.LeafElement;
import com.intellij.util.IncorrectOperationException;
import org.jetbrains.annotations.NotNull;

final class JdlIdManipulator extends AbstractElementManipulator<JdlId> {
  @Override
  public @NotNull JdlId handleContentChange(@NotNull JdlId element, @NotNull TextRange range,
                                            String newContent) throws IncorrectOperationException {
    var replacement = range.replace(element.getText(), newContent);

    ASTNode node = element.getNode();
    ((LeafElement) node.getFirstChildNode()).replaceWithText(replacement);

    return element;
  }
}
