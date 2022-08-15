package org.strangeway.jdl;

import com.intellij.openapi.util.Pair;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiElement;
import com.intellij.spellchecker.inspections.PlainTextSplitter;
import com.intellij.spellchecker.tokenizer.SpellcheckingStrategy;
import com.intellij.spellchecker.tokenizer.TokenConsumer;
import com.intellij.spellchecker.tokenizer.Tokenizer;
import org.jetbrains.annotations.NotNull;
import org.strangeway.jdl.model.JdlOptionModel;
import org.strangeway.jdl.psi.*;

final class JdlSpellcheckingStrategy extends SpellcheckingStrategy {
  private final Tokenizer<JdlStringLiteral> ourStringLiteralTokenizer = new Tokenizer<>() {
    @Override
    public void tokenize(@NotNull JdlStringLiteral element, TokenConsumer consumer) {
      final PlainTextSplitter textSplitter = PlainTextSplitter.getInstance();
      if (element.textContains('\\')) {
        var fragments = element.getTextFragments();
        for (Pair<TextRange, String> fragment : fragments) {
          TextRange fragmentRange = fragment.getFirst();
          String escaped = fragment.getSecond();
          // Fragment without escaping, also not a broken escape sequence or a unicode code point
          if (escaped.length() == fragmentRange.getLength() && !escaped.startsWith("\\")) {
            consumer.consumeToken(element, escaped, false, fragmentRange.getStartOffset(), TextRange.allOf(escaped), textSplitter);
          }
        }
      } else {
        consumer.consumeToken(element, textSplitter);
      }
    }
  };

  private final Tokenizer<PsiElement> idLiteralTokenizer = new Tokenizer<>() {
    @Override
    public void tokenize(@NotNull PsiElement element, TokenConsumer consumer) {
      PlainTextSplitter textSplitter = PlainTextSplitter.getInstance();
      consumer.consumeToken(element, textSplitter);
    }
  };

  @Override
  public @NotNull Tokenizer<?> getTokenizer(PsiElement element) {
    if (element instanceof JdlStringLiteral) {
      return ourStringLiteralTokenizer;
    }

    if (element instanceof JdlOptionName) {
      String key = element.getText();  // todo mixin
      if (JdlOptionModel.INSTANCE.getApplicationConfigOptions().get(key) != null
          || JdlOptionModel.INSTANCE.getDeploymentOptions().get(key) != null) {
        return EMPTY_TOKENIZER;
      }
    }

    if (element instanceof JdlId
        || element instanceof JdlEntityId
        || element instanceof JdlEnumId
        || element instanceof JdlEnumKey
        || element instanceof JdlFieldName) {
      return idLiteralTokenizer;
    }

    return super.getTokenizer(element);
  }
}
