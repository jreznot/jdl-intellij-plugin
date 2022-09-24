package org.strangeway.jdl.inspections;

import com.intellij.codeInspection.LocalInspectionTool;
import com.intellij.codeInspection.ProblemsHolder;
import com.intellij.psi.PsiElementVisitor;
import org.jetbrains.annotations.NotNull;
import org.strangeway.jdl.JdlInspectionUtil;
import org.strangeway.jdl.psi.*;

import java.util.List;
import java.util.Map;

final class JdlDuplicatedDeclarationInspection extends LocalInspectionTool {
  @Override
  public @NotNull PsiElementVisitor buildVisitor(@NotNull ProblemsHolder holder, boolean isOnTheFly) {
    return new JdlVisitor() {
      @Override
      public void visitEntity(@NotNull JdlEntity o) {
        super.visitEntity(o);

        JdlEntityId entityId = o.getEntityId();
        if (entityId == null) return;

        String entityName = o.getName();

        Map<String, List<@NotNull JdlEntity>> map = JdlInspectionUtil.getAllEntities(holder.getFile());
        List<@NotNull JdlEntity> sameNameEntities = map.get(entityName);
        if (sameNameEntities != null && sameNameEntities.size() > 1) {
          holder.registerProblem(entityId, "Duplicated entity " + entityName);
        }
      }

      @Override
      public void visitEnum(@NotNull JdlEnum o) {
        super.visitEnum(o);

        JdlEnumId enumId = o.getEnumId();
        if (enumId == null) return;

        String entityName = o.getName();

        Map<String, List<@NotNull JdlEnum>> map = JdlInspectionUtil.getAllEnums(holder.getFile());
        List<@NotNull JdlEnum> sameNameEnums = map.get(entityName);
        if (sameNameEnums != null && sameNameEnums.size() > 1) {
          holder.registerProblem(enumId, "Duplicated enum " + entityName);
        }
      }
    };
  }
}
