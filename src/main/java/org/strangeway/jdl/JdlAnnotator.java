package org.strangeway.jdl;

import com.intellij.lang.annotation.AnnotationHolder;
import com.intellij.lang.annotation.Annotator;
import com.intellij.lang.annotation.HighlightSeverity;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;
import org.strangeway.jdl.model.JdlEnumType;
import org.strangeway.jdl.model.JdlOptionMapping;
import org.strangeway.jdl.model.JdlOptionModel;
import org.strangeway.jdl.psi.*;

public class JdlAnnotator implements Annotator {
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
      if (idParent instanceof JdlValue)
        if (idParent.getParent() instanceof JdlFieldConstraintParameters) {
          holder.newSilentAnnotation(HighlightSeverity.INFORMATION)
              .range(element.getTextRange())
              .textAttributes(JdlSyntaxHighlighter.JDL_CONSTANT)
              .create();
        } else if (idParent.getParent() instanceof JdlOptionNameValue) {
          JdlOptionNameValue optionNameValue = (JdlOptionNameValue) idParent.getParent();
          JdlOptionName optionName = optionNameValue.getOptionName();
          String optionKey = optionName.getText();

          JdlOptionMapping optionMapping = JdlOptionModel.INSTANCE.getApplicationConfigOptions().get(optionKey);
          if (optionMapping != null && optionMapping.getPropertyType() instanceof JdlEnumType) {
            holder.newSilentAnnotation(HighlightSeverity.INFORMATION)
                .range(element.getTextRange())
                .textAttributes(JdlSyntaxHighlighter.JDL_OPTION_ENUM_VALUE)
                .create();
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

    // todo resolve optionValue id
  }
}
