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

import com.intellij.lang.cacheBuilder.DefaultWordsScanner;
import com.intellij.lang.cacheBuilder.WordsScanner;
import com.intellij.lang.findUsages.FindUsagesProvider;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiNamedElement;
import com.intellij.psi.tree.TokenSet;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.strangeway.jdl.psi.*;

final class JdlFindUsagesProvider implements FindUsagesProvider {
  @Override
  public @NotNull WordsScanner getWordsScanner() {
    return new DefaultWordsScanner(new JdlLexer(),
        TokenSet.create(JdlTokenTypes.IDENTIFIER),
        JdlTokenSets.COMMENTS,
        TokenSet.EMPTY);
  }

  @Override
  public boolean canFindUsagesFor(@NotNull PsiElement psiElement) {
    return psiElement instanceof JdlEntity
        || psiElement instanceof JdlEnum
        || psiElement instanceof JdlConstant;
  }

  @Override
  public @Nullable @NonNls String getHelpId(@NotNull PsiElement psiElement) {
    return null;
  }

  @Override
  public @Nls @NotNull String getType(@NotNull PsiElement element) {
    if (element instanceof JdlEntity) {
      return "entity";
    }
    if (element instanceof JdlEnum) {
      return "enum";
    }
    if (element instanceof JdlConstant) {
      return "constant";
    }
    return "";
  }

  @Override
  public @Nls @NotNull String getDescriptiveName(@NotNull PsiElement element) {
    String name = element instanceof PsiNamedElement ? ((PsiNamedElement) element).getName() : null;
    return name != null ? name : "<unnamed>";
  }

  @Override
  public @Nls @NotNull String getNodeText(@NotNull PsiElement element, boolean useFullName) {
    return getDescriptiveName(element);
  }
}
