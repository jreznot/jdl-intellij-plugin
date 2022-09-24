package org.strangeway.jdl.inspections;

import com.intellij.codeInspection.LocalInspectionTool;
import com.intellij.codeInspection.ProblemsHolder;
import com.intellij.psi.PsiElementVisitor;
import org.jetbrains.annotations.NotNull;
import org.strangeway.jdl.model.JdlOptionMapping;
import org.strangeway.jdl.model.JdlOptionModel;
import org.strangeway.jdl.psi.*;

import java.util.Map;

final class JdlUnknownOptionInspection extends LocalInspectionTool {
  @Override
  public @NotNull PsiElementVisitor buildVisitor(@NotNull ProblemsHolder holder, boolean isOnTheFly) {
    return new JdlVisitor() {
      @Override
      public void visitOptionNameValue(@NotNull JdlOptionNameValue o) {
        super.visitOptionNameValue(o);

        String optionName = o.getName();

        JdlOptionModel model = JdlOptionModel.INSTANCE;

        Map<String, JdlOptionMapping> options;
        if (o.getParent() instanceof JdlConfigBlock) {
          options = model.getApplicationConfigOptions();
        } else if (o.getParent() instanceof JdlDeployment) {
          options = model.getDeploymentOptions();
        } else {
          return;
        }

        if (!options.containsKey(optionName)) {
          holder.registerProblem(o.getNameElement(), "Unknown option " + optionName);
        }
      }
    };
  }
}
