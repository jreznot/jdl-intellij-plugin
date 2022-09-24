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
