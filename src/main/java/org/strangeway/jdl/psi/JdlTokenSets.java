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

package org.strangeway.jdl.psi;

import com.intellij.psi.tree.TokenSet;

public final class JdlTokenSets {
  private JdlTokenSets() {
  }

  public static final TokenSet STRINGS = TokenSet.create(JdlTokenTypes.DOUBLE_QUOTED_STRING);
  public static final TokenSet BRACES = TokenSet.create(JdlTokenTypes.LBRACE, JdlTokenTypes.RBRACE);
  public static final TokenSet COMMENTS = TokenSet.create(JdlTokenTypes.LINE_COMMENT, JdlTokenTypes.BLOCK_COMMENT);
  public static final TokenSet DECLARATIONS = TokenSet.create(JdlTokenTypes.ENTITY, JdlTokenTypes.ENUM, JdlTokenTypes.CONSTANT);

  public static final TokenSet TOP_LEVEL_BLOCKS = TokenSet.create(
      JdlTokenTypes.APPLICATION,
      JdlTokenTypes.ENTITY,
      JdlTokenTypes.ENUM,
      JdlTokenTypes.RELATIONSHIP_GROUP,
      JdlTokenTypes.DEPLOYMENT
  );

  public static final TokenSet BLOCK_KEYWORDS = TokenSet.create(
      JdlTokenTypes.APPLICATION_KEYWORD,
      JdlTokenTypes.CONFIG_KEYWORD,
      JdlTokenTypes.ENTITY_KEYWORD,
      JdlTokenTypes.ENUM_KEYWORD,
      JdlTokenTypes.RELATIONSHIP_KEYWORD,
      JdlTokenTypes.DEPLOYMENT_KEYWORD
  );

  public static final TokenSet BLOCK_IDENTIFIERS = TokenSet.create(
      JdlTokenTypes.ENTITY_ID,
      JdlTokenTypes.ENUM_ID,
      JdlTokenTypes.RELATIONSHIP_TYPE
  );

  public static final TokenSet SPACING_TAIL_ELEMENTS = TokenSet.create(
      JdlTokenTypes.OPTION_NAME,
      JdlTokenTypes.CONFIGURATION_OPTION_NAME,
      JdlTokenTypes.ENTITY_KEYWORD,
      JdlTokenTypes.ENUM_KEYWORD,
      JdlTokenTypes.FIELD_NAME,
      JdlTokenTypes.RELATIONSHIP_KEYWORD
  );
}
