package org.strangeway.jdl;

import com.intellij.codeInsight.TailType;
import com.intellij.codeInsight.completion.*;
import com.intellij.codeInsight.lookup.LookupElementBuilder;
import com.intellij.codeInsight.lookup.TailTypeDecorator;
import com.intellij.icons.AllIcons;
import com.intellij.psi.PsiElement;
import com.intellij.psi.util.PsiTreeUtil;
import com.intellij.util.ProcessingContext;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.strangeway.jdl.model.*;
import org.strangeway.jdl.psi.*;

import java.util.List;
import java.util.Map;

import static com.intellij.patterns.PlatformPatterns.psiElement;
import static org.strangeway.jdl.JdlConstants.*;
import static org.strangeway.jdl.JdlPatterns.*;

final class JdlCompletionContributor extends CompletionContributor {
  public JdlCompletionContributor() {
    extend(CompletionType.BASIC, jdlIdentifier().inside(jdlApplicationBlock()).andNot(psiElement().inside(JdlConfigBlock.class)),
        new KeywordsCompletionProvider(JdlConstants.APPLICATION_NESTED_KEYWORDS));

    extend(CompletionType.BASIC, jdlIdentifier()
            .andNot(jdlIdentifier().inside(jdlTopLevelBlock()))
            .andNot(jdlIdentifier().inside(JdlEntitiesList.class)),
        new KeywordsCompletionProvider(JdlConstants.TOP_LEVEL_KEYWORDS));

    extend(CompletionType.BASIC, jdlIdentifier().inside(JdlRelationshipMapping.class),
        new KeywordsCompletionProvider(JdlConstants.RELATIONSHIP_NESTED_KEYWORDS));

    extend(CompletionType.BASIC, jdlIdentifier().inside(JdlRelationshipType.class), new CompletionProvider<>() {
      @Override
      protected void addCompletions(@NotNull CompletionParameters parameters, @NotNull ProcessingContext context,
                                    @NotNull CompletionResultSet result) {
        for (var relationshipType : RELATIONSHIP_TYPES) {
          result.addElement(LookupElementBuilder.create(relationshipType)
              .withIcon(AllIcons.General.InheritedMethod));
        }
      }
    });

    extend(CompletionType.BASIC, jdlIdentifier().inside(JdlFieldType.class), new CompletionProvider<>() {
      @Override
      protected void addCompletions(@NotNull CompletionParameters parameters, @NotNull ProcessingContext context,
                                    @NotNull CompletionResultSet result) {
        for (var fieldType : FIELD_TYPES.keySet()) {
          result.addElement(LookupElementBuilder.create(fieldType)
              .withIcon(AllIcons.Nodes.Type));
        }

        var allEnums = JdlDeclarationsModel.getAllEnums(parameters.getOriginalFile());
        for (JdlEnum enumBlock : allEnums) {
          String enumId = enumBlock.getName();

          if (enumId != null && !enumId.isEmpty() && !enumId.isBlank()) {
            result.addElement(LookupElementBuilder.create(enumBlock)
                .withIcon(AllIcons.Nodes.Enum));
          }
        }
      }
    });

    extend(CompletionType.BASIC, jdlIdentifier().withParent(JdlOptionName.class).inside(JdlConfigBlock.class),
        new CompletionProvider<>() {
          @Override
          protected void addCompletions(@NotNull CompletionParameters parameters, @NotNull ProcessingContext context,
                                        @NotNull CompletionResultSet result) {
            addOptions(result, JdlOptionModel.INSTANCE.getApplicationConfigOptions());
          }
        });

    extend(CompletionType.BASIC, jdlIdentifier().withParent(JdlOptionName.class).inside(JdlDeployment.class),
        new CompletionProvider<>() {
          @Override
          protected void addCompletions(@NotNull CompletionParameters parameters, @NotNull ProcessingContext context,
                                        @NotNull CompletionResultSet result) {
            addOptions(result, JdlOptionModel.INSTANCE.getDeploymentOptions());
          }
        });

    extend(CompletionType.BASIC, jdlIdentifier().inside(JdlValue.class).inside(JdlOptionNameValue.class).inside(JdlConfigBlock.class),
        new CompletionProvider<>() {
          @Override
          protected void addCompletions(@NotNull CompletionParameters parameters, @NotNull ProcessingContext context,
                                        @NotNull CompletionResultSet result) {
            PsiElement position = parameters.getPosition();
            PsiElement optionNameValue = PsiTreeUtil.findFirstParent(position, p -> p instanceof JdlOptionNameValue);

            if (optionNameValue instanceof JdlOptionNameValue) {
              String key = ((JdlOptionNameValue) optionNameValue).getName();

              addOptionValues(result, JdlOptionModel.INSTANCE.getApplicationConfigOptions().get(key));
            }
          }
        });

    extend(CompletionType.BASIC, jdlIdentifier().inside(JdlValue.class).inside(JdlOptionNameValue.class).inside(JdlDeployment.class),
        new CompletionProvider<>() {
          @Override
          protected void addCompletions(@NotNull CompletionParameters parameters, @NotNull ProcessingContext context,
                                        @NotNull CompletionResultSet result) {
            PsiElement position = parameters.getPosition();
            PsiElement optionNameValue = PsiTreeUtil.findFirstParent(position, p -> p instanceof JdlOptionNameValue);

            if (optionNameValue instanceof JdlOptionNameValue) {
              String key = ((JdlOptionNameValue) optionNameValue).getName();

              addOptionValues(result, JdlOptionModel.INSTANCE.getDeploymentOptions().get(key));
            }
          }
        });

    extend(CompletionType.BASIC, jdlIdentifier().inside(JdlFieldConstraint.class), new CompletionProvider<>() {
      @Override
      protected void addCompletions(@NotNull CompletionParameters parameters, @NotNull ProcessingContext context,
                                    @NotNull CompletionResultSet result) {
        for (String fieldValidation : FIELD_VALIDATIONS) {
          result.addElement(LookupElementBuilder.create(fieldValidation)
              .withTailText(fieldValidation.endsWith("()") ? "()" : null, true)
              .withPresentableText(fieldValidation.replace("()", ""))
              .withIcon(AllIcons.General.InspectionsOK));
        }
      }
    });
  }

  private static void addOptionValues(@NotNull CompletionResultSet result, @Nullable JdlOptionMapping optionMapping) {
    if (optionMapping != null) {
      if (optionMapping.getPropertyType() instanceof JdlEnumType) {
        @SuppressWarnings({"unchecked", "rawtypes"})
        List<JdlModelEnum> values = ((JdlEnumType) optionMapping.getPropertyType()).getValues();

        for (JdlModelEnum value : values) {
          result.addElement(LookupElementBuilder.create(value.getId()).withIcon(AllIcons.Nodes.Enum));
        }
      } else if (optionMapping.getPropertyType() instanceof JdlEnumListType) {
        @SuppressWarnings({"unchecked", "rawtypes"})
        List<JdlModelEnum> values = ((JdlEnumListType) optionMapping.getPropertyType()).getValues();

        for (JdlModelEnum value : values) {
          result.addElement(LookupElementBuilder.create(value.getId()).withIcon(AllIcons.Nodes.Enum));
        }
      } else if (optionMapping.getPropertyType() == JdlPrimitiveType.BOOLEAN_TYPE) {
        result.addElement(LookupElementBuilder.create(JdlConstants.TRUE).withBoldness(true));
        result.addElement(LookupElementBuilder.create(JdlConstants.FALSE).withBoldness(true));
      }
    }
  }

  private static void addOptions(@NotNull CompletionResultSet result, Map<String, JdlOptionMapping> options) {
    for (var optionMapping : options.values()) {
      var element = LookupElementBuilder.create(optionMapping.getName());
      if (optionMapping.getDefaultValue() != null) {
        element = element.withTailText("=" + optionMapping.getDefaultValue(), true);
      }

      result.addElement(element
          .withTypeText(optionMapping.getPropertyType().getName())
          .withIcon(AllIcons.Nodes.Property));
    }
  }

  private static final class KeywordsCompletionProvider extends CompletionProvider<CompletionParameters> {
    private final List<String> keywords;

    private KeywordsCompletionProvider(List<String> keywords) {
      this.keywords = keywords;
    }

    @Override
    protected void addCompletions(@NotNull CompletionParameters parameters, @NotNull ProcessingContext context,
                                  @NotNull CompletionResultSet result) {
      addKeywords(result, keywords);
    }

    private static void addKeywords(@NotNull CompletionResultSet result, List<String> applicationNestedKeywords) {
      for (var topLevelKeyword : applicationNestedKeywords) {
        var element = LookupElementBuilder.create(topLevelKeyword).withBoldness(true);
        result.addElement(TailTypeDecorator.withTail(element, TailType.INSERT_SPACE));
      }
    }
  }
}