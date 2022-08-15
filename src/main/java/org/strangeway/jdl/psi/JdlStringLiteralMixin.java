package org.strangeway.jdl.psi;

import com.intellij.lang.ASTNode;
import org.jetbrains.annotations.NotNull;
import org.strangeway.jdl.psi.impl.JdlValueImpl;

public abstract class JdlStringLiteralMixin extends JdlValueImpl {
  public JdlStringLiteralMixin(@NotNull ASTNode node) {
    super(node);
  }

  @Override
  public void subtreeChanged() {
    putUserData(JdlPsiUtils.STRING_FRAGMENTS, null);
  }
}
