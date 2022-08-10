package org.strangeway.jdl;

import com.intellij.lang.ASTNode;
import com.intellij.lang.folding.CustomFoldingBuilder;
import com.intellij.lang.folding.FoldingDescriptor;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.project.DumbAware;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiElement;
import com.intellij.psi.tree.IElementType;
import org.jetbrains.annotations.NotNull;
import org.strangeway.jdl.psi.JdlTokenTypes;

import java.util.List;

public final class JdlFoldingBuilder extends CustomFoldingBuilder implements DumbAware {
  @Override
  protected boolean isRegionCollapsedByDefault(@NotNull ASTNode node) {
    return false;
  }

  @Override
  protected void buildLanguageFoldRegions(@NotNull List<FoldingDescriptor> descriptors, @NotNull PsiElement root,
                                          @NotNull Document document, boolean quick) {
    ASTNode node = root.getNode();
    if (node != null) {
      IElementType elementType = node.getElementType();
      if (elementType == JdlTokenTypes.BLOCK_COMMENT) {
        descriptors.add(new FoldingDescriptor(node, node.getTextRange()));
      } else if (isBracesFoldingParent(elementType)) {
        ASTNode lbrace = node.findChildByType(JdlTokenTypes.LBRACE);
        ASTNode rbrace = node.findChildByType(JdlTokenTypes.RBRACE);

        if (lbrace != null && rbrace != null) {
          TextRange textRange = node.getTextRange();
          TextRange foldingRange = new TextRange(textRange.getStartOffset() + lbrace.getStartOffsetInParent(),
              textRange.getStartOffset() + rbrace.getStartOffsetInParent() + rbrace.getTextLength());
          descriptors.add(new FoldingDescriptor(node, foldingRange));
        }
      }
    }

    for (var child : root.getChildren()) {
      buildLanguageFoldRegions(descriptors, child, document, quick);
    }
  }

  @Override
  protected String getLanguagePlaceholderText(@NotNull ASTNode node, @NotNull TextRange range) {
    IElementType elementType = node.getElementType();
    if (isBracesFoldingParent(elementType)) {
      return "{...}";
    }

    if (elementType == JdlTokenTypes.BLOCK_COMMENT) {
      return "/*...*/";
    }

    return null;
  }

  private static boolean isBracesFoldingParent(IElementType elementType) {
    return elementType == JdlTokenTypes.APPLICATION_BLOCK
        || elementType == JdlTokenTypes.CONFIG_BLOCK
        || elementType == JdlTokenTypes.ENTITY_BLOCK
        || elementType == JdlTokenTypes.RELATIONSHIP_BLOCK
        || elementType == JdlTokenTypes.ENUM_BLOCK;
  }
}
