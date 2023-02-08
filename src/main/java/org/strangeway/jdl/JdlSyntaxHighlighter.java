/*
 * Copyright © 2023 Yuriy Artamonov
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software
 * and associated documentation files (the “Software”), to deal in the Software without
 * restriction, including without limitation the rights to use, copy, modify, merge, publish,
 * distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the
 * Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies
 * or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED “AS IS”, WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING
 * BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM,
 * DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING
 * FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package org.strangeway.jdl;

import com.intellij.lexer.Lexer;
import com.intellij.openapi.editor.DefaultLanguageHighlighterColors;
import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.fileTypes.SyntaxHighlighterBase;
import com.intellij.psi.tree.IElementType;
import org.jetbrains.annotations.NotNull;
import org.strangeway.jdl.psi.JdlTokenTypes;

import java.util.HashMap;
import java.util.Map;

import static com.intellij.openapi.editor.colors.TextAttributesKey.createTextAttributesKey;

final class JdlSyntaxHighlighter extends SyntaxHighlighterBase {
  public static final TextAttributesKey JDL_KEYWORD =
      createTextAttributesKey("JDL.KEYWORD", DefaultLanguageHighlighterColors.KEYWORD);

  public static final TextAttributesKey JDL_IDENTIFIER =
      createTextAttributesKey("JDL.IDENTIFIER", DefaultLanguageHighlighterColors.CLASS_NAME);

  public static final TextAttributesKey JDL_BASE_NAME =
      createTextAttributesKey("JDL.BASE_NAME", DefaultLanguageHighlighterColors.LABEL);

  public static final TextAttributesKey JDL_NUMBER =
      createTextAttributesKey("JDL.NUMBER", DefaultLanguageHighlighterColors.NUMBER);

  public static final TextAttributesKey JDL_BOOLEAN =
      createTextAttributesKey("JDL.BOOLEAN", DefaultLanguageHighlighterColors.NUMBER);

  public static final TextAttributesKey JDL_STRING =
      createTextAttributesKey("JDL.STRING", DefaultLanguageHighlighterColors.STRING);

  public static final TextAttributesKey JDL_PARENTHESES =
      createTextAttributesKey("JDL.PARENTHESES", DefaultLanguageHighlighterColors.PARENTHESES);

  public static final TextAttributesKey JDL_BRACKETS =
      createTextAttributesKey("JDL.BRACKETS", DefaultLanguageHighlighterColors.BRACKETS);

  public static final TextAttributesKey JDL_BRACES =
      createTextAttributesKey("JDL.BRACES", DefaultLanguageHighlighterColors.BRACES);

  public static final TextAttributesKey JDL_COMMA =
      createTextAttributesKey("JDL.COMMA", DefaultLanguageHighlighterColors.COMMA);

  public static final TextAttributesKey JDL_LINE_COMMENT =
      createTextAttributesKey("JDL.LINE_COMMENT", DefaultLanguageHighlighterColors.LINE_COMMENT);

  public static final TextAttributesKey JDL_BLOCK_COMMENT =
      createTextAttributesKey("JDL.BLOCK_COMMENT", DefaultLanguageHighlighterColors.BLOCK_COMMENT);

  public static final TextAttributesKey JDL_OPTION_NAME =
      createTextAttributesKey("JDL.OPTION_NAME", DefaultLanguageHighlighterColors.INSTANCE_METHOD);

  public static final TextAttributesKey JDL_OPTION_ENUM_VALUE =
      createTextAttributesKey("JDL.OPTION_ENUM_VALUE", DefaultLanguageHighlighterColors.CONSTANT);

  public static final TextAttributesKey JDL_FIELD_NAME =
      createTextAttributesKey("JDL.FIELD_NAME", DefaultLanguageHighlighterColors.INSTANCE_FIELD);

  public static final TextAttributesKey JDL_CONSTANT =
      createTextAttributesKey("JDL.CONSTANT", DefaultLanguageHighlighterColors.CONSTANT);

  public static final TextAttributesKey JDL_FIELD_CONSTRAINT =
      createTextAttributesKey("JDL.FIELD_CONSTRAINT", DefaultLanguageHighlighterColors.METADATA);

  public static final TextAttributesKey JDL_ANNOTATION =
      createTextAttributesKey("JDL.ANNOTATION", DefaultLanguageHighlighterColors.METADATA);

  private static final Map<IElementType, TextAttributesKey> ourMap;

  static {
    ourMap = new HashMap<>();

    fillMap(ourMap, JDL_KEYWORD,
        JdlTokenTypes.APPLICATION_KEYWORD, JdlTokenTypes.CONFIG_KEYWORD, JdlTokenTypes.ENTITY_KEYWORD,
        JdlTokenTypes.EXCEPT_KEYWORD, JdlTokenTypes.WITH_KEYWORD, JdlTokenTypes.ENUM_KEYWORD,
        JdlTokenTypes.DEPLOYMENT_KEYWORD, JdlTokenTypes.RELATIONSHIP_KEYWORD,
        JdlTokenTypes.TO_KEYWORD, JdlTokenTypes.USE_KEYWORD, JdlTokenTypes.FOR_KEYWORD);

    fillMap(ourMap, JDL_BRACKETS,
        JdlTokenTypes.LBRACKET, JdlTokenTypes.RBRACKET);
    fillMap(ourMap, JDL_PARENTHESES,
        JdlTokenTypes.LPARENTH, JdlTokenTypes.RPARENTH);
    fillMap(ourMap, JDL_BRACES,
        JdlTokenTypes.LBRACE, JdlTokenTypes.RBRACE);
    fillMap(ourMap, JDL_COMMA, JdlTokenTypes.COMMA);

    fillMap(ourMap, JDL_NUMBER, JdlTokenTypes.INTEGER_NUMBER, JdlTokenTypes.DOUBLE_NUMBER);
    fillMap(ourMap, JDL_STRING, JdlTokenTypes.DOUBLE_QUOTED_STRING, JdlTokenTypes.REGEX_STRING);
    fillMap(ourMap, JDL_BOOLEAN, JdlTokenTypes.TRUE, JdlTokenTypes.FALSE);

    fillMap(ourMap, JDL_LINE_COMMENT, JdlTokenTypes.LINE_COMMENT);
    fillMap(ourMap, JDL_BLOCK_COMMENT, JdlTokenTypes.BLOCK_COMMENT);

    fillMap(ourMap, JDL_ANNOTATION, JdlTokenTypes.STRUDEL);
  }

  @Override
  public @NotNull Lexer getHighlightingLexer() {
    return new JdlLexer();
  }

  @Override
  public TextAttributesKey @NotNull [] getTokenHighlights(IElementType tokenType) {
    return pack(ourMap.get(tokenType));
  }
}
