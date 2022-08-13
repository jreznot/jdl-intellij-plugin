package org.strangeway.jdl;

import com.intellij.codeInsight.TailType;
import com.intellij.codeInsight.completion.*;
import com.intellij.codeInsight.lookup.LookupElementBuilder;
import com.intellij.codeInsight.lookup.TailTypeDecorator;
import com.intellij.icons.AllIcons;
import com.intellij.util.ProcessingContext;
import org.jetbrains.annotations.NotNull;
import org.strangeway.jdl.model.JdlDeclarationsModel;
import org.strangeway.jdl.model.JdlOptionModel;
import org.strangeway.jdl.psi.*;

import java.util.List;

import static com.intellij.patterns.PlatformPatterns.psiElement;
import static org.strangeway.jdl.JdlConstants.FIELD_TYPES;
import static org.strangeway.jdl.JdlConstants.RELATIONSHIP_TYPES;
import static org.strangeway.jdl.JdlPatterns.*;

final class JdlCompletionContributor extends CompletionContributor {
  public JdlCompletionContributor() {
    extend(CompletionType.BASIC, jdlIdentifier().inside(jdlApplicationBlock()).andNot(psiElement().inside(JdlConfigBlock.class)),
        new KeywordsCompletionProvider(JdlConstants.APPLICATION_NESTED_KEYWORDS));

    extend(CompletionType.BASIC, jdlIdentifier().andNot(jdlIdentifier().inside(jdlTopLevelBlock())),
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
        for (var relationshipType : FIELD_TYPES) {
          result.addElement(LookupElementBuilder.create(relationshipType)
              .withIcon(AllIcons.Nodes.Type));
        }

        var allEnums = JdlDeclarationsModel.getAllEnums(parameters.getOriginalFile());
        for (JdlEnumBlock enumBlock : allEnums) {
          JdlEnumId enumId = enumBlock.getEnumId(); // todo move to PSI mixin

          if (enumId != null) {
            result.addElement(LookupElementBuilder.create(enumId.getText())
                .withPsiElement(enumBlock)
                .withIcon(AllIcons.Nodes.Type));
          }
        }
      }
    });

    extend(CompletionType.BASIC, jdlIdentifier().withParent(JdlOptionName.class).inside(JdlConfigBlock.class),
        new CompletionProvider<>() {
          @Override
          protected void addCompletions(@NotNull CompletionParameters parameters, @NotNull ProcessingContext context,
                                        @NotNull CompletionResultSet result) {
            var options = JdlOptionModel.INSTANCE.getApplicationConfigOptions();

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
        });
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
  }

  private static void addKeywords(@NotNull CompletionResultSet result, List<String> applicationNestedKeywords) {
    for (var topLevelKeyword : applicationNestedKeywords) {
      var element = LookupElementBuilder.create(topLevelKeyword).withBoldness(true);
      result.addElement(TailTypeDecorator.withTail(element, TailType.INSERT_SPACE));
    }
  }
}