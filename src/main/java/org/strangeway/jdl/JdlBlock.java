package org.strangeway.jdl;

import com.intellij.formatting.*;
import com.intellij.lang.ASTNode;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.strangeway.jdl.psi.JdlOptionNameValue;
import org.strangeway.jdl.psi.JdlTokenSets;
import org.strangeway.jdl.psi.JdlTokenTypes;
import org.strangeway.jdl.psi.JdlValue;

import java.util.ArrayList;
import java.util.List;

import static com.intellij.psi.formatter.FormatterUtil.isWhitespaceOrEmpty;
import static org.strangeway.jdl.JdlCodeStyleSettings.ALIGN_PROPERTY_ON_VALUE;
import static org.strangeway.jdl.psi.JdlTokenSets.*;

final class JdlBlock implements ASTBlock {

  private final ASTNode node;
  private final JdlBlock parent;
  private final PsiElement psiElement;
  private final Alignment alignment;
  private final Indent indent;
  private final Wrap wrap;
  private final Wrap childWrap;
  private final SpacingBuilder spacingBuilder;

  private final JdlCodeStyleSettings jdlCodeStyleSettings;
  private final Alignment propertyValueAlignment;

  private List<Block> subBlocks;

  public JdlBlock(@NotNull ASTNode node,
                  @Nullable JdlBlock parent,
                  @NotNull JdlCodeStyleSettings jdlCodeStyleSettings,
                  @Nullable Alignment alignment,
                  @Nullable Indent indent,
                  @Nullable Wrap wrap,
                  @NotNull SpacingBuilder spacingBuilder) {
    this.node = node;
    this.parent = parent;
    this.psiElement = node.getPsi();
    this.jdlCodeStyleSettings = jdlCodeStyleSettings;
    this.alignment = alignment;
    this.indent = indent;
    this.wrap = wrap;
    this.spacingBuilder = spacingBuilder;

    if (isJdlCodeBlock(node)) {
      childWrap = Wrap.createWrap(jdlCodeStyleSettings.BLOCK_WRAPPING, true);
    } else {
      childWrap = null;
    }

    this.propertyValueAlignment = isJdlCodeBlock(node) ? Alignment.createAlignment(true) : null;
  }

  @Override
  public @Nullable ASTNode getNode() {
    return node;
  }

  @Override
  public @NotNull TextRange getTextRange() {
    return node.getTextRange();
  }

  @Override
  public @NotNull List<Block> getSubBlocks() {
    if (subBlocks == null) {
      int propertyAlignment = jdlCodeStyleSettings.PROPERTY_ALIGNMENT;

      var children = node.getChildren(null);
      subBlocks = new ArrayList<>(children.length);

      for (var child : children) {
        if (isWhitespaceOrEmpty(child)) continue;
        if (child.getElementType() == JdlTokenTypes.NEWLINE) continue;

        subBlocks.add(makeSubBlock(child, propertyAlignment));
      }
    }
    return subBlocks;
  }

  private Block makeSubBlock(ASTNode child, int propertyAlignment) {
    Indent indent = Indent.getNoneIndent();
    Alignment alignment = null;
    Wrap wrap = null;

    if (isJdlCodeBlock(node)) {
      if (child.getElementType() == JdlTokenTypes.COMMA) {
        wrap = Wrap.createWrap(WrapType.NONE, true);
      } else if (!JdlTokenSets.BRACES.contains(child.getElementType())
          && !BLOCK_KEYWORDS.contains(child.getElementType())
          && !BLOCK_IDENTIFIERS.contains(child.getElementType())) {
        wrap = childWrap;
        indent = Indent.getNormalIndent();
      }
    } else if (child.getPsi() instanceof JdlValue
        && parent != null
        && psiElement instanceof JdlOptionNameValue
        && propertyAlignment == ALIGN_PROPERTY_ON_VALUE) {
      alignment = parent.propertyValueAlignment;
    }

    return new JdlBlock(child, this, jdlCodeStyleSettings, alignment, indent, wrap, spacingBuilder);
  }

  @Override
  public @Nullable Wrap getWrap() {
    return wrap;
  }

  @Override
  public @Nullable Indent getIndent() {
    return indent;
  }

  @Override
  public @Nullable Alignment getAlignment() {
    return alignment;
  }

  @Override
  public @Nullable Spacing getSpacing(@Nullable Block child1, @NotNull Block child2) {
    return spacingBuilder.getSpacing(this, child1, child2);
  }

  @Override
  public @NotNull ChildAttributes getChildAttributes(int newChildIndex) {
    if (node.getPsi() instanceof PsiFile) {
      return new ChildAttributes(Indent.getNoneIndent(), null);
    }

    if (isJdlCodeBlock(node)) {
      return new ChildAttributes(Indent.getNormalIndent(), null);
    }

    return new ChildAttributes(null, null);
  }

  @Override
  public boolean isIncomplete() {
    var lastChildNode = node.getLastChildNode();
    if (isJdlCodeBlock(node)) {
      return lastChildNode != null && lastChildNode.getElementType() != JdlTokenTypes.RBRACE;
    }
    if (node.getElementType() == JdlTokenTypes.ARRAY_LITERAL) {
      return lastChildNode != null && lastChildNode.getElementType() != JdlTokenTypes.RBRACKET;
    }
    return false;
  }

  private boolean isJdlCodeBlock(ASTNode node) {
    return TOP_LEVEL_BLOCKS.contains(node.getElementType())
        || node.getElementType() == JdlTokenTypes.CONFIG_BLOCK;
  }

  @Override
  public boolean isLeaf() {
    return node.getFirstChildNode() == null;
  }
}