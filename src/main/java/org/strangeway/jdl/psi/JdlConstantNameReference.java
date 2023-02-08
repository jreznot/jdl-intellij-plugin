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

import com.intellij.codeInsight.lookup.LookupElement;
import com.intellij.codeInsight.lookup.LookupElementBuilder;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiReferenceBase;
import com.intellij.psi.ResolveState;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.strangeway.jdl.JhipsterIcons;
import org.strangeway.jdl.model.JdlDeclarationsModel;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

import static org.apache.commons.lang.ArrayUtils.EMPTY_OBJECT_ARRAY;

final class JdlConstantNameReference extends PsiReferenceBase<JdlId> {
  public JdlConstantNameReference(@NotNull JdlId element) {
    super(element);
  }

  @Override
  public @Nullable PsiElement resolve() {
    PsiFile containingFile = myElement.getContainingFile();
    if (containingFile == null) return null;

    List<JdlConstant> constantRefs = new ArrayList<>();

    String myId = myElement.getText(); // todo move ID to mixin

    // todo support files in the same directory
    containingFile.processDeclarations((element, state) -> {
      if (element instanceof JdlConstant && Objects.equals(myId, ((JdlConstant) element).getName())) {
        constantRefs.add((JdlConstant) element);
        return false;
      }
      return true;
    }, ResolveState.initial(), null, myElement);

    if (constantRefs.size() != 1) return null;

    return constantRefs.get(0);
  }

  @Override
  public Object @NotNull [] getVariants() {
    PsiFile containingFile = myElement.getContainingFile();
    if (containingFile == null) return EMPTY_OBJECT_ARRAY;

    Collection<JdlConstant> constants = JdlDeclarationsModel.findAllJdlConstants(containingFile);
    List<LookupElement> items = new ArrayList<>();

    for (JdlConstant entity : constants) {
      items.add(LookupElementBuilder.create(entity)
          .withIcon(JhipsterIcons.getConstantIcon()));
    }

    return items.toArray();
  }
}
