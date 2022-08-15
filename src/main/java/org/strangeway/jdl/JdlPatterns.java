package org.strangeway.jdl;

import com.intellij.patterns.PsiElementPattern;
import com.intellij.psi.PsiElement;
import org.strangeway.jdl.psi.JdlApplication;
import org.strangeway.jdl.psi.JdlTokenTypes;

import static com.intellij.patterns.PlatformPatterns.psiElement;
import static org.strangeway.jdl.psi.JdlTokenSets.TOP_LEVEL_BLOCKS;

public final class JdlPatterns {
  private JdlPatterns() {
  }

  public static PsiElementPattern.Capture<PsiElement> jdlIdentifier() {
    return psiElement(JdlTokenTypes.IDENTIFIER);
  }

  public static PsiElementPattern.Capture<JdlApplication> jdlApplicationBlock() {
    return psiElement(JdlApplication.class);
  }

  public static PsiElementPattern.Capture<PsiElement> jdlTopLevelBlock() {
    return psiElement().withElementType(TOP_LEVEL_BLOCKS);
  }
}
