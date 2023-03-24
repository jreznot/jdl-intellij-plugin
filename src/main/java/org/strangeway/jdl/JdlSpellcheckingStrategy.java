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
      String key = element.getText();
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
