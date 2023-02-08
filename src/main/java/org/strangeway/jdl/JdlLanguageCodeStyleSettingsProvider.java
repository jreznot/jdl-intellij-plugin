/*
 * Copyright © 2023 Yuriy Artamonov
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software
 * and associated documentation files (the “Software”), to deal in the Software without
 * restriction, including without limitation the rights to use, copy, modify, merge, publish,
 * distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the
 * Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies
 * or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED “AS IS”, WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING
 * BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM,
 * DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING
 * FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package org.strangeway.jdl;

import com.intellij.application.options.IndentOptionsEditor;
import com.intellij.application.options.SmartIndentOptionsEditor;
import com.intellij.lang.Language;
import com.intellij.psi.codeStyle.CodeStyleSettingsCustomizable;
import com.intellij.psi.codeStyle.CommonCodeStyleSettings;
import com.intellij.psi.codeStyle.LanguageCodeStyleSettingsProvider;
import com.intellij.util.ArrayUtil;
import com.intellij.util.containers.ContainerUtil;
import org.jetbrains.annotations.NotNull;
import org.strangeway.jdl.JdlCodeStyleSettings.PropertyAlignment;

import java.util.Arrays;

import static com.intellij.psi.codeStyle.CodeStyleSettingsCustomizableOptions.getInstance;

final class JdlLanguageCodeStyleSettingsProvider extends LanguageCodeStyleSettingsProvider {

  private static final String[] ALIGN_OPTIONS = Arrays.stream(PropertyAlignment.values())
      .map(PropertyAlignment::getDescription)
      .toArray(String[]::new);

  private static final int[] ALIGN_VALUES = ArrayUtil.toIntArray(
      ContainerUtil.map(PropertyAlignment.values(), PropertyAlignment::getId));

  @org.intellij.lang.annotations.Language("JDL")
  @Override
  public @NotNull String getCodeSample(@NotNull SettingsType settingsType) {
    return "application {\n" +
        "  config {\n" +
        "    baseName cars\n" +
        "    serverPort 8080\n" +
        "    languages [en, fr]\n" +
        "  }\n" +
        "  entities Car\n" +
        "  dto * with mapstruct\n" +
        "}\n" +
        "\n" +
        "// The comment\n" +
        "entity Car {\n" +
        "  departmentName String required\n" +
        "  handle String required\n" +
        "}\n" +
        "\n" +
        "deployment {\n" +
        "  deploymentType docker-compose\n" +
        "  dockerRepositoryName \"YourDockerLoginName\"\n" +
        "}";
  }

  @Override
  public IndentOptionsEditor getIndentOptionsEditor() {
    return new SmartIndentOptionsEditor(this);
  }

  @Override
  public @NotNull Language getLanguage() {
    return JdlLanguage.INSTANCE;
  }

  @Override
  protected void customizeDefaults(@NotNull CommonCodeStyleSettings commonSettings, CommonCodeStyleSettings.@NotNull IndentOptions indentOptions) {
    super.customizeDefaults(commonSettings, indentOptions);

    indentOptions.INDENT_SIZE = 2;
  }

  @Override
  public void customizeSettings(@NotNull CodeStyleSettingsCustomizable consumer, @NotNull SettingsType settingsType) {
    if (settingsType == SettingsType.SPACING_SETTINGS) {
      consumer.showStandardOptions(
          "SPACE_WITHIN_BRACKETS",
          "SPACE_WITHIN_BRACES",
          "SPACE_AFTER_COMMA",
          "SPACE_BEFORE_COMMA"
      );
      consumer.renameStandardOption("SPACE_WITHIN_BRACES", JdlBundle.message("formatter.space_within_braces.label"));
      consumer.showCustomOption(JdlCodeStyleSettings.class, "SPACE_BEFORE_LBRACE",
          JdlBundle.message("formatter.space_before_brace.label"), getInstance().SPACES_OTHER);
    } else if (settingsType == SettingsType.BLANK_LINES_SETTINGS) {
      consumer.showStandardOptions("KEEP_BLANK_LINES_IN_CODE");
    } else if (settingsType == SettingsType.WRAPPING_AND_BRACES_SETTINGS) {
      consumer.showStandardOptions(
          "RIGHT_MARGIN",
          "WRAP_ON_TYPING",
          "KEEP_LINE_BREAKS",
          "WRAP_LONG_LINES"
      );

      consumer.showCustomOption(JdlCodeStyleSettings.class,
          "PROPERTY_ALIGNMENT",
          JdlBundle.message("formatter.align.properties.caption"),
          JdlBundle.message("formatter.objects.label"),
          ALIGN_OPTIONS,
          ALIGN_VALUES);

      consumer.showCustomOption(JdlCodeStyleSettings.class,
          "FIELD_ALIGNMENT",
          JdlBundle.message("formatter.align.entity.fields.caption"),
          JdlBundle.message("formatter.objects.label"),
          ALIGN_OPTIONS,
          ALIGN_VALUES);
    }
  }
}
