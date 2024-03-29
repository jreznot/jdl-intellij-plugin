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
