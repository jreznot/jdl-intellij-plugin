package org.strangeway.jdl;

import com.intellij.lang.annotation.AnnotationHolder;
import com.intellij.lang.annotation.Annotator;
import com.intellij.lang.annotation.HighlightSeverity;
import com.intellij.psi.PsiElement;
import com.intellij.psi.util.PsiTreeUtil;
import org.jetbrains.annotations.NotNull;
import org.strangeway.jdl.model.JdlEnumListType;
import org.strangeway.jdl.model.JdlEnumType;
import org.strangeway.jdl.model.JdlOptionMapping;
import org.strangeway.jdl.model.JdlOptionModel;
import org.strangeway.jdl.psi.*;

import static org.strangeway.jdl.model.JdlOptionModel.BASE_NAME_ATTRIBUTE_NAME;

final class JdlAnnotator implements Annotator {
  @Override
  public void annotate(@NotNull PsiElement element, @NotNull AnnotationHolder holder) {
    if (element instanceof JdlOptionName) {
      holder.newSilentAnnotation(HighlightSeverity.INFORMATION)
          .range(element.getTextRange())
          .textAttributes(JdlSyntaxHighlighter.JDL_OPTION_NAME)
          .create();
    } else if (element instanceof JdlFieldName) {
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
      if (idParent.getParent() instanceof JdlFieldConstraintParameters) {
        holder.newSilentAnnotation(HighlightSeverity.INFORMATION)
            .range(element.getTextRange())
            .textAttributes(JdlSyntaxHighlighter.JDL_CONSTANT)
            .create();
      } else {
        PsiElement optionNameValue = PsiTreeUtil.findFirstParent(idParent, p -> p instanceof JdlOptionNameValue);
        if (optionNameValue instanceof JdlOptionNameValue) {
          annotateOptionNameEnumValue(element, holder, (JdlOptionNameValue) optionNameValue);
        }
      }
    } else if (element instanceof JdlRelationshipType) {
      holder.newSilentAnnotation(HighlightSeverity.INFORMATION)
          .range(element.getTextRange())
          .textAttributes(JdlSyntaxHighlighter.JDL_OPTION_ENUM_VALUE)
          .create();
    } else if (element.getNode().getElementType() == JdlTokenTypes.IDENTIFIER) {
      if (element.getParent() instanceof JdlFieldConstraint) {
        holder.newSilentAnnotation(HighlightSeverity.INFORMATION)
            .range(element.getTextRange())
            .textAttributes(JdlSyntaxHighlighter.JDL_FIELD_CONSTRAINT)
            .create();
      } else if (element.getParent() instanceof JdlConstantOption) {
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
    }
  }

  private static void annotateOptionNameEnumValue(@NotNull PsiElement element, @NotNull AnnotationHolder holder,
                                                  JdlOptionNameValue optionNameValue) {
    JdlOptionName optionName = optionNameValue.getOptionName();
    String optionKey = optionName.getText();

    if (optionNameValue.getParent() instanceof JdlConfigBlock) {
      if (BASE_NAME_ATTRIBUTE_NAME.equals(optionKey)) {
        holder.newSilentAnnotation(HighlightSeverity.INFORMATION)
            .range(element.getTextRange())
            .textAttributes(JdlSyntaxHighlighter.JDL_BASE_NAME)
            .create();
      } else {
        JdlOptionMapping optionMapping = JdlOptionModel.INSTANCE.getApplicationConfigOptions().get(optionKey);
        if (optionMapping != null && isEnumType(optionMapping)) {
          if (!"false".equals(element.getText())) { // false is highlighted as boolean instead
            holder.newSilentAnnotation(HighlightSeverity.INFORMATION)
                .range(element.getTextRange())
                .textAttributes(JdlSyntaxHighlighter.JDL_OPTION_ENUM_VALUE)
                .create();
          }
        }
      }
    }
  }

  private static boolean isEnumType(JdlOptionMapping optionMapping) {
    return optionMapping.getPropertyType() instanceof JdlEnumType
        || optionMapping.getPropertyType() instanceof JdlEnumListType;
  }
}
