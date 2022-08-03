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

public class JdlSyntaxHighlighter extends SyntaxHighlighterBase {
  public static final TextAttributesKey JDL_KEYWORD =
      createTextAttributesKey("JDL.KEYWORD", DefaultLanguageHighlighterColors.KEYWORD);

  public static final TextAttributesKey JDL_IDENTIFIER =
      createTextAttributesKey("JDL.IDENTIFIER", DefaultLanguageHighlighterColors.CLASS_NAME);

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
      createTextAttributesKey("JDL.OPTION_NAME", DefaultLanguageHighlighterColors.INSTANCE_FIELD);

  public static final TextAttributesKey JDL_OPTION_ENUM_VALUE =
      createTextAttributesKey("JDL.OPTION_ENUM_VALUE", DefaultLanguageHighlighterColors.CONSTANT);

  public static final TextAttributesKey JDL_FIELD_CONSTRAINT =
          createTextAttributesKey("JDL.FIELD_CONSTRAINT", DefaultLanguageHighlighterColors.METADATA);

  private static final Map<IElementType, TextAttributesKey> ourMap;

  static {
    ourMap = new HashMap<>();

    fillMap(ourMap, JDL_KEYWORD, JdlTokenTypes.WILDCARD,
        JdlTokenTypes.APPLICATION_KEYWORD, JdlTokenTypes.CONFIG_KEYWORD, JdlTokenTypes.ENTITIES_KEYWORD,
        JdlTokenTypes.DTO_KEYWORD, JdlTokenTypes.PAGINATE_KEYWORD, JdlTokenTypes.ENTITY_KEYWORD,
        JdlTokenTypes.SERVICE_KEYWORD, JdlTokenTypes.EXCEPT_KEYWORD, JdlTokenTypes.WITH_KEYWORD,
        JdlTokenTypes.ENUM_KEYWORD, JdlTokenTypes.DEPLOYMENT_KEYWORD, JdlTokenTypes.RELATIONSHIP_KEYWORD,
        JdlTokenTypes.TO_KEYWORD);

    fillMap(ourMap, JDL_BRACKETS,
        JdlTokenTypes.LBRACKET, JdlTokenTypes.RBRACKET);
    fillMap(ourMap, JDL_PARENTHESES,
        JdlTokenTypes.LPARENTH, JdlTokenTypes.RPARENTH);
    fillMap(ourMap, JDL_BRACES,
        JdlTokenTypes.LBRACE, JdlTokenTypes.RBRACE);
    fillMap(ourMap, JDL_COMMA, JdlTokenTypes.COMMA);

    fillMap(ourMap, JDL_NUMBER, JdlTokenTypes.INTEGER_NUMBER, JdlTokenTypes.DOUBLE_NUMBER);
    fillMap(ourMap, JDL_STRING, JdlTokenTypes.DOUBLE_QUOTED_STRING);
    fillMap(ourMap, JDL_BOOLEAN, JdlTokenTypes.TRUE, JdlTokenTypes.FALSE);

    fillMap(ourMap, JDL_LINE_COMMENT, JdlTokenTypes.LINE_COMMENT);
    fillMap(ourMap, JDL_BLOCK_COMMENT, JdlTokenTypes.BLOCK_COMMENT);
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
