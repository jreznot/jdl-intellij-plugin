package org.strangeway.jdl;

import com.intellij.lexer.FlexAdapter;
import org.strangeway.jdl.lexer._JdlLexer;

public final class JdlLexer extends FlexAdapter {
  public JdlLexer() {
    super(new _JdlLexer(null));
  }
}
