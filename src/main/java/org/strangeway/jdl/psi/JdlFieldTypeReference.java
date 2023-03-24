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

package org.strangeway.jdl.psi;

import com.intellij.openapi.project.Project;
import com.intellij.psi.*;
import com.intellij.util.ArrayUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.strangeway.jdl.JdlConstants;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.intellij.psi.search.GlobalSearchScope.allScope;

public final class JdlFieldTypeReference extends PsiReferenceBase<JdlFieldType> {
  public JdlFieldTypeReference(@NotNull JdlFieldType element) {
    super(element);
  }

  @Override
  public @Nullable PsiElement resolve() {
    String typeName = myElement.getTypeName();
    String jvmTypeName = JdlConstants.FIELD_TYPES.get(typeName);

    if (jvmTypeName != null) {
      Project project = myElement.getProject();
      return JavaPsiFacade.getInstance(project).findClass(jvmTypeName, allScope(project));
    }

    PsiFile containingFile = myElement.getContainingFile();
    if (containingFile == null) return null;

    List<JdlEnum> enumRefs = new ArrayList<>();

    // todo support files in the same directory
    containingFile.processDeclarations((element, state) -> {
      if (element instanceof JdlEnum && Objects.equals(typeName, ((JdlEnum) element).getName())) {
        enumRefs.add((JdlEnum) element);
        return false;
      }
      return true;
    }, ResolveState.initial(), null, myElement);

    if (enumRefs.size() != 1) return null;

    return enumRefs.get(0);
  }

  @Override
  public Object @NotNull [] getVariants() {
    return ArrayUtil.EMPTY_OBJECT_ARRAY; // provided by completion contributor
  }
}
