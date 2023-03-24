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

package org.strangeway.jdl;

import com.intellij.codeInspection.ProblemHighlightType;
import com.intellij.lang.annotation.AnnotationHolder;
import com.intellij.lang.annotation.Annotator;
import com.intellij.lang.annotation.HighlightSeverity;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.roots.ProjectRootManager;
import com.intellij.psi.JavaPsiFacade;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiElement;
import com.intellij.psi.util.CachedValueProvider.Result;
import com.intellij.psi.util.CachedValuesManager;
import org.jetbrains.annotations.NotNull;
import org.strangeway.jdl.model.JdlEnumListType;
import org.strangeway.jdl.model.JdlEnumType;
import org.strangeway.jdl.model.JdlOptionMapping;
import org.strangeway.jdl.model.JdlOptionModel;
import org.strangeway.jdl.psi.*;

import static com.intellij.psi.search.GlobalSearchScope.allScope;
import static com.intellij.psi.util.PsiTreeUtil.findFirstParent;
import static org.strangeway.jdl.JdlConstants.APPLICATION_BASE_NAME;

final class JdlAnnotator implements Annotator {
  @Override
  public void annotate(@NotNull PsiElement element, @NotNull AnnotationHolder holder) {
    if (element instanceof JdlWildcardLiteral) {
      holder.newSilentAnnotation(HighlightSeverity.INFORMATION)
          .range(element.getTextRange())
          .textAttributes(JdlSyntaxHighlighter.JDL_KEYWORD)
          .create();
    } else if (element instanceof JdlOptionName) {
      holder.newSilentAnnotation(HighlightSeverity.INFORMATION)
          .range(element.getTextRange())
          .textAttributes(JdlSyntaxHighlighter.JDL_OPTION_NAME)
          .create();
    } else if (element instanceof JdlFieldName || element instanceof JdlFieldNameRef) {
      holder.newSilentAnnotation(HighlightSeverity.INFORMATION)
          .range(element.getTextRange())
          .textAttributes(JdlSyntaxHighlighter.JDL_FIELD_NAME)
          .create();
    } else if ((element instanceof JdlWithOptionValue) || (element instanceof JdlEnumKey)) {
      holder.newSilentAnnotation(HighlightSeverity.INFORMATION)
          .range(element.getTextRange())
          .textAttributes(JdlSyntaxHighlighter.JDL_OPTION_ENUM_VALUE)
          .create();
    } else if (element instanceof JdlEntityId || element instanceof JdlEnumId) {
      holder.newSilentAnnotation(HighlightSeverity.INFORMATION)
          .range(element.getTextRange())
          .textAttributes(JdlSyntaxHighlighter.JDL_IDENTIFIER)
          .create();
    } else if (element instanceof JdlId) {
      PsiElement idParent = element.getParent();
      if (idParent instanceof JdlFieldConstraintParameters) {
        holder.newSilentAnnotation(HighlightSeverity.INFORMATION)
            .range(element.getTextRange())
            .textAttributes(JdlSyntaxHighlighter.JDL_CONSTANT)
            .create();
      } else {
        PsiElement optionNameValue = findFirstParent(idParent, p -> p instanceof JdlOptionNameValue);
        if (optionNameValue instanceof JdlOptionNameValue) {
          annotateOptionNameEnumValue(element, holder, (JdlOptionNameValue) optionNameValue);
        }
      }
    } else if (element instanceof JdlFieldConstraintId) {
      holder.newSilentAnnotation(HighlightSeverity.INFORMATION)
          .range(element.getTextRange())
          .textAttributes(JdlSyntaxHighlighter.JDL_FIELD_CONSTRAINT)
          .create();
    } else if (element instanceof JdlRelationshipType) {
      holder.newSilentAnnotation(HighlightSeverity.INFORMATION)
          .range(element.getTextRange())
          .textAttributes(JdlSyntaxHighlighter.JDL_OPTION_ENUM_VALUE)
          .create();
    } else if (element.getNode().getElementType() == JdlTokenTypes.IDENTIFIER) {
      if (element.getParent() instanceof JdlConstantName) {
        holder.newSilentAnnotation(HighlightSeverity.INFORMATION)
            .range(element.getTextRange())
            .textAttributes(JdlSyntaxHighlighter.JDL_CONSTANT)
            .create();
      }
    } else if (element instanceof JdlConfigurationOptionName) {
      holder.newSilentAnnotation(HighlightSeverity.INFORMATION)
          .range(element.getTextRange())
          .textAttributes(JdlSyntaxHighlighter.JDL_KEYWORD)
          .create();
    } else if (element instanceof JdlAnnotationId) {
      holder.newSilentAnnotation(HighlightSeverity.INFORMATION)
          .range(element.getTextRange())
          .textAttributes(JdlSyntaxHighlighter.JDL_ANNOTATION)
          .create();
    }

    resolveIdentifierRefs(element, holder);
  }

  private void resolveIdentifierRefs(PsiElement element, AnnotationHolder holder) {
    if (element instanceof JdlFieldType
        && looksLikeJdkType((JdlFieldType) element)
        && !isJdkConfigured(element.getProject())) {
      return;
    }

    if (element instanceof JdlId || element instanceof JdlFieldType) {
      var reference = element.getReference();
      if (reference != null && reference.resolve() == null) {
        var message = String.format("Cannot resolve symbol '%s'", reference.getCanonicalText());
        holder.newAnnotation(HighlightSeverity.ERROR, message)
            .highlightType(ProblemHighlightType.LIKE_UNKNOWN_SYMBOL)
            .range(element)
            .create();
      }
    }
  }

  private static boolean looksLikeJdkType(@NotNull JdlFieldType fieldType) {
    return JdlConstants.FIELD_TYPES.containsKey(fieldType.getTypeName());
  }

  private static boolean isJdkConfigured(@NotNull Project project) {
    return CachedValuesManager.getManager(project).getCachedValue(project, () -> {
      PsiClass stringClass = JavaPsiFacade.getInstance(project).findClass("java.lang.String", allScope(project));
      return Result.create(stringClass != null, ProjectRootManager.getInstance(project));
    });
  }

  private static void annotateOptionNameEnumValue(@NotNull PsiElement element, @NotNull AnnotationHolder holder,
                                                  JdlOptionNameValue optionNameValue) {
    var optionName = optionNameValue.getOptionName();
    var optionKey = optionName.getText();

    if (APPLICATION_BASE_NAME.equals(optionKey) && optionNameValue.getParent() instanceof JdlConfigBlock) {
      holder.newSilentAnnotation(HighlightSeverity.INFORMATION)
          .range(element.getTextRange())
          .textAttributes(JdlSyntaxHighlighter.JDL_BASE_NAME)
          .create();
    } else {
      JdlOptionMapping optionMapping;
      if (findFirstParent(element, p -> p instanceof JdlConfigBlock) != null) {
        optionMapping = JdlOptionModel.INSTANCE.getApplicationConfigOptions().get(optionKey);
      } else if (findFirstParent(element, p -> p instanceof JdlDeployment) != null) {
        optionMapping = JdlOptionModel.INSTANCE.getDeploymentOptions().get(optionKey);
      } else {
        optionMapping = null;
      }

      if (optionMapping != null && isEnumType(optionMapping)) {
        if (!JdlConstants.FALSE.equals(element.getText())) { // false is highlighted as boolean instead
          holder.newSilentAnnotation(HighlightSeverity.INFORMATION)
              .range(element.getTextRange())
              .textAttributes(JdlSyntaxHighlighter.JDL_OPTION_ENUM_VALUE)
              .create();
        }
      }
    }
  }

  private static boolean isEnumType(JdlOptionMapping optionMapping) {
    return optionMapping.getPropertyType() instanceof JdlEnumType
        || optionMapping.getPropertyType() instanceof JdlEnumListType;
  }
}
