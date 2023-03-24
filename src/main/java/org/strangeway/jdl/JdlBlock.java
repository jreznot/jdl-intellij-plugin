/*
 * Copyright © 2023 Yuriy Artamonov
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software
 * and associated documentation files (the "Software"), to deal in the Software without
 * restriction, including without limitation the rights to use, copy, modify, merge, publish,
 * distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the
 * Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies
 * or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING
 * BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM,
 * DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING
 * FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package org.strangeway.jdl;

import com.intellij.formatting.*;
import com.intellij.lang.ASTNode;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.strangeway.jdl.psi.*;

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
      int fieldAlignment = jdlCodeStyleSettings.FIELD_ALIGNMENT;

      var children = node.getChildren(null);
      subBlocks = new ArrayList<>(children.length);

      for (var child : children) {
        if (isWhitespaceOrEmpty(child)) continue;
        if (child.getElementType() == JdlTokenTypes.NEWLINE) continue;

        subBlocks.add(makeSubBlock(child, propertyAlignment, fieldAlignment));
      }
    }
    return subBlocks;
  }

  private Block makeSubBlock(ASTNode child, int propertyAlignment, int fieldAlignment) {
    Indent indent = Indent.getNoneIndent();
    Alignment alignment = null;
    Wrap wrap = null;

    if (isJdlCodeBlock(node)) {
      if (child.getElementType() == JdlTokenTypes.COMMA) {
        wrap = Wrap.createWrap(WrapType.NONE, true);
      } else if (!JdlTokenSets.BRACES.contains(child.getElementType())
          && !BLOCK_KEYWORDS.contains(child.getElementType())
          && !BLOCK_IDENTIFIERS.contains(child.getElementType())) {

        if (!COMMENTS.contains(child.getElementType())) {
          wrap = childWrap;
        }

        indent = Indent.getNormalIndent();
      }
    } else if (child.getPsi() instanceof JdlValue
        && parent != null
        && psiElement instanceof JdlOptionNameValue
        && propertyAlignment == ALIGN_PROPERTY_ON_VALUE) {
      alignment = parent.propertyValueAlignment;
    } else if (child.getPsi() instanceof JdlFieldType
        && parent != null
        && psiElement instanceof JdlEntityFieldMapping
        && fieldAlignment == ALIGN_PROPERTY_ON_VALUE) {
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