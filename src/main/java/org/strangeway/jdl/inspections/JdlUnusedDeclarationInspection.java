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

package org.strangeway.jdl.inspections;

import com.intellij.codeInspection.LocalInspectionTool;
import com.intellij.codeInspection.ProblemHighlightType;
import com.intellij.codeInspection.ProblemsHolder;
import com.intellij.psi.PsiElementVisitor;
import org.jetbrains.annotations.NotNull;
import org.strangeway.jdl.JdlInspectionUtil;
import org.strangeway.jdl.psi.*;

import java.util.Collection;

final class JdlUnusedDeclarationInspection extends LocalInspectionTool {
  @Override
  public @NotNull PsiElementVisitor buildVisitor(@NotNull ProblemsHolder holder, boolean isOnTheFly) {
    return new JdlVisitor() {
      @Override
      public void visitEntity(@NotNull JdlEntity o) {
        super.visitEntity(o);

        JdlEntityId entityId = o.getEntityId();
        if (entityId == null) return;

        Collection<@NotNull JdlEntity> usedEntities = JdlInspectionUtil.getUsedEntities(holder.getFile());
        if (!usedEntities.contains(o)) {
          holder.registerProblem(entityId, "Unused entity " + o.getName(), ProblemHighlightType.LIKE_UNUSED_SYMBOL);
        }
      }

      @Override
      public void visitEnum(@NotNull JdlEnum o) {
        super.visitEnum(o);

        JdlEnumId enumId = o.getEnumId();
        if (enumId == null) return;

        Collection<@NotNull JdlEnum> usedEnums = JdlInspectionUtil.getUsedEnums(holder.getFile());
        if (!usedEnums.contains(o)) {
          holder.registerProblem(enumId, "Unused enum " + o.getName(), ProblemHighlightType.LIKE_UNUSED_SYMBOL);
        }
      }
    };
  }
}
