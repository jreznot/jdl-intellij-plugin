package org.strangeway.jdl.inspections;

import com.intellij.codeInspection.LocalInspectionTool;
import com.intellij.codeInspection.ProblemsHolder;
import com.intellij.psi.PsiElementVisitor;
import org.jetbrains.annotations.NotNull;
import org.strangeway.jdl.model.*;
import org.strangeway.jdl.psi.*;

import java.util.List;
import java.util.Map;

final class JdlIncorrectOptionTypeInspection extends LocalInspectionTool {
  @Override
  public @NotNull PsiElementVisitor buildVisitor(@NotNull ProblemsHolder holder, boolean isOnTheFly) {
    return new JdlVisitor() {
      @Override
      public void visitOptionNameValue(@NotNull JdlOptionNameValue o) {
        super.visitOptionNameValue(o);

        JdlValue actualValue = o.getValue();
        if (actualValue == null) return;

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

        JdlOptionMapping optionMapping = options.get(optionName);
        if (optionMapping == null) return;

        JdlOptionType expectedType = optionMapping.getPropertyType();

        boolean matches = true;
        String expectedComment = "";

        if (expectedType == JdlPrimitiveType.BOOLEAN_TYPE) {
          matches = actualValue instanceof JdlBooleanLiteral;
          expectedComment = expectedType.getName();
        } else if (expectedType == JdlPrimitiveType.INTEGER_TYPE) {
          matches = actualValue instanceof JdlNumberLiteral;
          expectedComment = expectedType.getName();
        } else if (expectedType == JdlPrimitiveType.STRING_ARRAY_TYPE) {
          matches = actualValue instanceof JdlArrayLiteral;
          expectedComment = expectedType.getName();
        } else if (optionMapping instanceof JdlEnumMapping) {
          List<String> supportedOptions = ((JdlEnumMapping) optionMapping).getOptions();
          matches = (actualValue instanceof JdlId || actualValue instanceof JdlBooleanLiteral)
              && supportedOptions.contains(actualValue.getText());

          if (!matches) {
            expectedComment = "one of [" + String.join(", ", supportedOptions) + "]";
          }
        }

        if (!matches) {
          holder.registerProblem(actualValue, "incorrect value type, expected " + expectedComment);
        }
      }
    };
  }
}
