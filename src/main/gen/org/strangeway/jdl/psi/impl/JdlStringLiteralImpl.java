// This is a generated file. Not intended for manual editing.
package org.strangeway.jdl.psi.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import static org.strangeway.jdl.psi.JdlTokenTypes.*;
import org.strangeway.jdl.psi.JdlStringLiteralMixin;
import org.strangeway.jdl.psi.*;
import com.intellij.openapi.util.Pair;
import com.intellij.openapi.util.TextRange;

public class JdlStringLiteralImpl extends JdlStringLiteralMixin implements JdlStringLiteral {

  public JdlStringLiteralImpl(@NotNull ASTNode node) {
    super(node);
  }

  @Override
  public void accept(@NotNull JdlVisitor visitor) {
    visitor.visitStringLiteral(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof JdlVisitor) accept((JdlVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  public @NotNull List<Pair<TextRange, String>> getTextFragments() {
    return JdlPsiUtils.getTextFragments(this);
  }

}
