// This is a generated file. Not intended for manual editing.
package org.strangeway.jdl.psi.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import static org.strangeway.jdl.psi.JdlTokenTypes.*;
import org.strangeway.jdl.psi.*;

public class JdlStringLiteralImpl extends JdlValueImpl implements JdlStringLiteral {

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

}
