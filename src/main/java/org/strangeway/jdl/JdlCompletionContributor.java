package org.strangeway.jdl;

import com.intellij.codeInsight.TailType;
import com.intellij.codeInsight.completion.*;
import com.intellij.codeInsight.lookup.LookupElementBuilder;
import com.intellij.codeInsight.lookup.TailTypeDecorator;
import com.intellij.util.ProcessingContext;
import org.jetbrains.annotations.NotNull;
import org.strangeway.jdl.psi.JdlConfigBlock;

import java.util.List;

import static com.intellij.patterns.PlatformPatterns.psiElement;
import static org.strangeway.jdl.JdlPatterns.*;

final class JdlCompletionContributor extends CompletionContributor {
  public JdlCompletionContributor() {
    extend(CompletionType.BASIC, jdlIdentifier().inside(jdlApplicationBlock()).andNot(psiElement().inside(JdlConfigBlock.class)),
        new KeywordsCompletionProvider(JdlConstants.APPLICATION_NESTED_KEYWORDS));

    extend(CompletionType.BASIC, jdlIdentifier().andNot(jdlIdentifier().inside(jdlTopLevelBlock())),
        new KeywordsCompletionProvider(JdlConstants.TOP_LEVEL_KEYWORDS));

    extend(CompletionType.BASIC, jdlIdentifier().inside(jdlRelationshipBlock()),
        new KeywordsCompletionProvider(JdlConstants.RELATIONSHIP_NESTED_KEYWORDS));
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
    for (String topLevelKeyword : applicationNestedKeywords) {
      LookupElementBuilder element = LookupElementBuilder.create(topLevelKeyword).withBoldness(true);
      result.addElement(TailTypeDecorator.withTail(element, TailType.INSERT_SPACE));
    }
  }
}