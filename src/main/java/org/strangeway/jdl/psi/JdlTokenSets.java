package org.strangeway.jdl.psi;

import com.intellij.psi.TokenType;
import com.intellij.psi.tree.TokenSet;

public final class JdlTokenSets {
  private JdlTokenSets() {
  }

  public static final TokenSet STRINGS = TokenSet.create(JdlTokenTypes.DOUBLE_QUOTED_STRING);
  public static final TokenSet COMMENTS = TokenSet.create(JdlTokenTypes.LINE_COMMENT, JdlTokenTypes.BLOCK_COMMENT);
  public static final TokenSet WHITESPACES = TokenSet.create(TokenType.WHITE_SPACE);

  public static final TokenSet TOP_LEVEL_BLOCKS = TokenSet.create(
      JdlTokenTypes.APPLICATION_BLOCK,
      JdlTokenTypes.ENTITY_BLOCK,
      JdlTokenTypes.ENUM_BLOCK,
      JdlTokenTypes.RELATIONSHIP_BLOCK,
      JdlTokenTypes.DEPLOYMENT_BLOCK
  );
}
