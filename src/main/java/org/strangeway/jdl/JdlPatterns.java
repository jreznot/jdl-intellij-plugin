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
