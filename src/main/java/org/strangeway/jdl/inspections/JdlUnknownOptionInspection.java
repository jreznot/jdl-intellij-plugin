package org.strangeway.jdl.inspections;

import com.intellij.codeInspection.LocalInspectionTool;
import com.intellij.codeInspection.ProblemsHolder;
import com.intellij.psi.PsiElementVisitor;
import org.jetbrains.annotations.NotNull;
import org.strangeway.jdl.model.JdlOptionModel;
import org.strangeway.jdl.psi.*;

final class JdlUnknownOptionInspection extends LocalInspectionTool {
  @Override
  public @NotNull PsiElementVisitor buildVisitor(@NotNull ProblemsHolder holder, boolean isOnTheFly) {
    return new JdlVisitor() {
      @Override
      public void visitOptionNameValue(@NotNull JdlOptionNameValue o) {
        super.visitOptionNameValue(o);

        String optionName = o.getName();

        JdlOptionModel model = JdlOptionModel.INSTANCE;

        if (o.getParent() instanceof JdlConfigBlock) {
          if (!model.getApplicationConfigOptions().containsKey(optionName)) {
            holder.registerProblem(o.getNameElement(), "Unknown option " + optionName);
          }
        } else if (o.getParent() instanceof JdlDeployment) {
          if (!model.getDeploymentOptions().containsKey(optionName)) {
            holder.registerProblem(o.getNameElement(), "Unknown option " + optionName);
          }
        }
      }
    };
  }
}
