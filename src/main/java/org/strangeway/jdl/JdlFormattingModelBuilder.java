package org.strangeway.jdl;

import com.intellij.formatting.*;
import com.intellij.psi.codeStyle.CodeStyleSettings;
import org.jetbrains.annotations.NotNull;

import static com.intellij.formatting.FormattingModelProvider.createFormattingModelForPsiFile;
import static org.strangeway.jdl.psi.JdlTokenSets.SPACING_TAIL_ELEMENTS;
import static org.strangeway.jdl.psi.JdlTokenTypes.*;

final class JdlFormattingModelBuilder implements FormattingModelBuilder {
  @Override
  public @NotNull FormattingModel createModel(@NotNull FormattingContext formattingContext) {
    var settings = formattingContext.getCodeStyleSettings();
    var spacingBuilder = createSpacingBuilder(settings);
    var topBlock = new JdlBlock(
        formattingContext.getNode(),
        null,
        settings.getCustomSettings(JdlCodeStyleSettings.class),
        null,
        Indent.getSmartIndent(Indent.Type.CONTINUATION),
        null,
        spacingBuilder
    );
    return createFormattingModelForPsiFile(formattingContext.getContainingFile(), topBlock, settings);
  }

  static @NotNull SpacingBuilder createSpacingBuilder(CodeStyleSettings settings) {
    var jdlSettings = settings.getCustomSettings(JdlCodeStyleSettings.class);
    var commonSettings = settings.getCommonSettings(JdlLanguage.INSTANCE);

    var spacesBeforeComma = commonSettings.SPACE_BEFORE_COMMA ? 1 : 0;
    var spacesBeforeLbrace = jdlSettings.SPACE_BEFORE_LBRACE ? 1 : 0;

    return new SpacingBuilder(settings, JdlLanguage.INSTANCE)
        .withinPair(LBRACKET, RBRACKET).spaceIf(commonSettings.SPACE_WITHIN_BRACKETS, true)
        .withinPair(LBRACE, RBRACE).spaceIf(commonSettings.SPACE_WITHIN_BRACES, true)
        .before(COMMA).spacing(spacesBeforeComma, spacesBeforeComma, 0, false, 0)
        .after(COMMA).spaceIf(commonSettings.SPACE_AFTER_COMMA)
        .before(LBRACE).spacing(spacesBeforeLbrace, spacesBeforeLbrace, 0, false, 0)
        .after(SPACING_TAIL_ELEMENTS).spacing(1, 1, 0, false, 0);
  }
}
