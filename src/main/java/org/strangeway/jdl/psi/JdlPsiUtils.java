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

package org.strangeway.jdl.psi;

import com.intellij.navigation.ItemPresentation;
import com.intellij.openapi.util.Key;
import com.intellij.openapi.util.Pair;
import com.intellij.openapi.util.TextRange;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.strangeway.jdl.JdlConstants;
import org.strangeway.jdl.JhipsterIcons;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static org.strangeway.jdl.JdlConstants.*;

public final class JdlPsiUtils {
  private static final String ourEscapesTable = "\"\"\\\\//b\bf\fn\nr\rt\t";

  static final Key<List<Pair<TextRange, String>>> STRING_FRAGMENTS = new Key<>("JDL string fragments");

  private JdlPsiUtils() {
  }

  public static @NotNull List<Pair<TextRange, String>> getTextFragments(@NotNull JdlStringLiteral literal) {
    List<Pair<TextRange, String>> cached = literal.getUserData(STRING_FRAGMENTS);
    if (cached != null) return cached;

    List<Pair<TextRange, String>> result = new ArrayList<>();
    String text = literal.getText();
    int length = text.length();
    int pos = 1, unescapedSequenceStart = 1;
    while (pos < length) {
      if (text.charAt(pos) == '\\') {
        if (unescapedSequenceStart != pos) {
          result.add(Pair.create(new TextRange(unescapedSequenceStart, pos), text.substring(unescapedSequenceStart, pos)));
        }
        if (pos == length - 1) {
          result.add(Pair.create(new TextRange(pos, pos + 1), "\\"));
          break;
        }
        char next = text.charAt(pos + 1);
        switch (next) {
          case '"':
          case '\\':
          case '/':
          case 'b':
          case 'f':
          case 'n':
          case 'r':
          case 't':
            final int idx = ourEscapesTable.indexOf(next);
            result.add(Pair.create(new TextRange(pos, pos + 2), ourEscapesTable.substring(idx + 1, idx + 2)));
            pos += 2;
            break;
          case 'u':
            int i = pos + 2;
            for (; i < pos + 6; i++) {
              if (i == length || !StringUtil.isHexDigit(text.charAt(i))) {
                break;
              }
            }
            result.add(Pair.create(new TextRange(pos, i), text.substring(pos, i)));
            pos = i;
            break;
          default:
            result.add(Pair.create(new TextRange(pos, pos + 2), text.substring(pos, pos + 2)));
            pos += 2;
        }
        unescapedSequenceStart = pos;
      }
      else {
        pos++;
      }
    }
    int contentEnd = text.charAt(0) == text.charAt(length - 1) ? length - 1 : length;
    if (unescapedSequenceStart < contentEnd) {
      result.add(Pair.create(new TextRange(unescapedSequenceStart, contentEnd), text.substring(unescapedSequenceStart, contentEnd)));
    }
    result = Collections.unmodifiableList(result);
    literal.putUserData(STRING_FRAGMENTS, result);
    return result;
  }

  public static @NotNull String getName(@NotNull JdlApplication applicationBlock) {
    return applicationBlock.getConfigBlockList().stream()
        .flatMap(c -> c.getOptionNameValueList().stream())
        .filter(o -> JdlConstants.APPLICATION_BASE_NAME.equals(o.getName()))
        .map(o -> {
          JdlValue value = o.getValue();
          if (value instanceof JdlId) {
            return value.getText();
          }
          if (value instanceof JdlStringLiteral) {
            return ((JdlStringLiteral) value).getTextFragments().stream()
                .map(f -> f.getSecond())
                .collect(Collectors.joining());
          }
          return APPLICATION_UNNAMED;
        })
        .findFirst()
        .orElse(APPLICATION_UNNAMED);
  }

  public static @NotNull ItemPresentation getPresentation(@NotNull JdlApplication applicationBlock) {
    return new ItemPresentation() {
      @Override
      public String getPresentableText() {
        return getName(applicationBlock);
      }

      @Override
      public Icon getIcon(boolean unused) {
        return JhipsterIcons.getApplicationIcon();
      }
    };
  }

  public static @NotNull String getName(@NotNull JdlOptionNameValue property) {
    return property.getOptionName().getText();
  }

  public static @NotNull JdlOptionName getNameElement(@NotNull JdlOptionNameValue property) {
    return property.getOptionName();
  }

  public static @NotNull ItemPresentation getPresentation(@NotNull JdlOptionNameValue property) {
    return new ItemPresentation() {
      @Override
      public @NotNull String getPresentableText() {
        return property.getName();
      }

      @Override
      public @Nullable String getLocationString() {
        JdlValue value = property.getValue();
        if (value == null) return null;

        return value.getText();
      }

      @Override
      public @NotNull Icon getIcon(boolean unused) {
        return JhipsterIcons.getPropertyIcon();
      }
    };
  }

  public static @NotNull String getName(@NotNull JdlEntityFieldMapping fieldMapping) {
    return fieldMapping.getFieldName().getText();
  }

  public static @NotNull JdlFieldName getNameElement(@NotNull JdlEntityFieldMapping fieldMapping) {
    return fieldMapping.getFieldName();
  }

  public static @Nullable String getType(@NotNull JdlEntityFieldMapping fieldMapping) {
    JdlFieldType fieldType = fieldMapping.getFieldType();
    if (fieldType == null) return null;

    return fieldType.getText();
  }

  public static boolean isRequired(@NotNull JdlEntityFieldMapping fieldMapping) {
    return fieldMapping.getFieldConstraintList().stream()
        .anyMatch(c -> "required".equals(c.getFieldConstraintId().getText()));
  }

  public static @NotNull ItemPresentation getPresentation(@NotNull JdlEntityFieldMapping fieldMapping) {
    return new ItemPresentation() {
      @Override
      public @NotNull String getPresentableText() {
        String fieldType = fieldMapping.getType();
        if (fieldType != null) {
          return fieldMapping.getName() + ": " + fieldType;
        }

        return fieldMapping.getName();
      }

      @Override
      public @NotNull Icon getIcon(boolean unused) {
        return JhipsterIcons.getFieldIcon();
      }
    };
  }

  public static @NotNull String getName(@NotNull JdlConfigurationOption configurationOption) {
    return configurationOption.getConfigurationOptionName().getText();
  }

  public static @NotNull JdlConfigurationOptionName getNameElement(@NotNull JdlConfigurationOption configurationOption) {
    return configurationOption.getConfigurationOptionName();
  }

  public static @NotNull ItemPresentation getPresentation(@NotNull JdlConfigurationOption configurationOption) {
    return new ItemPresentation() {
      @Override
      public @NotNull String getPresentableText() {
        return configurationOption.getName();
      }

      @Override
      public @NotNull String getLocationString() {
        JdlEntitiesList entitiesList = configurationOption.getEntitiesList();
        if (entitiesList == null) return "";

        return entitiesList.getIdList().stream()
            .map(PsiElement::getText)
            .collect(Collectors.joining(", "));
      }

      @Override
      public @NotNull Icon getIcon(boolean unused) {
        return JhipsterIcons.getConfigurationPropertyIcon();
      }
    };
  }

  public static @NotNull ItemPresentation getPresentation(@SuppressWarnings("unused") @NotNull JdlConfigBlock configBlock) {
    return new ItemPresentation() {
      @Override
      public @NotNull String getPresentableText() {
        return CONFIG_BLOCK_NAME;
      }

      @Override
      public @NotNull Icon getIcon(boolean unused) {
        return JhipsterIcons.getConfigIcon();
      }
    };
  }

  public static @NotNull String getType(@NotNull JdlRelationshipGroup relationship) {
    JdlRelationshipType relationshipType = relationship.getRelationshipType();
    if (relationshipType == null) return "<unknown>";

    return relationshipType.getText();
  }

  public static @NotNull ItemPresentation getPresentation(@NotNull JdlRelationshipGroup relationship) {
    return new ItemPresentation() {
      @Override
      public @NotNull String getPresentableText() {
        return getType(relationship);
      }

      @Override
      public @NotNull Icon getIcon(boolean unused) {
        return JhipsterIcons.getRelationshipIcon();
      }
    };
  }

  public static @NotNull String getName(@NotNull JdlEnumValue property) {
    return property.getEnumKey().getText();
  }

  public static @NotNull JdlEnumKey getNameElement(@NotNull JdlEnumValue property) {
    return property.getEnumKey();
  }

  public static @NotNull ItemPresentation getPresentation(@NotNull JdlEnumValue enumValue) {
    return new ItemPresentation() {
      @Override
      public @NotNull String getPresentableText() {
        return enumValue.getName();
      }

      @Override
      public @Nullable String getLocationString() {
        JdlExplicitEnumMapping enumMapping = enumValue.getExplicitEnumMapping();
        if (enumMapping == null) return null;

        JdlValue enumMappingValue = enumMapping.getValue();
        if (enumMappingValue == null) return null;

        return enumMappingValue.getText();
      }

      @Override
      public @NotNull Icon getIcon(boolean unused) {
        return JhipsterIcons.getFieldIcon();
      }
    };
  }

  public static @NotNull ItemPresentation getPresentation(@SuppressWarnings("unused") @NotNull JdlDeployment deploymentBlock) {
    return new ItemPresentation() {
      @Override
      public @NotNull String getPresentableText() {
        return DEPLOYMENT_BLOCK_NAME;
      }

      @Override
      public @NotNull Icon getIcon(boolean unused) {
        return JhipsterIcons.getDeployIcon();
      }
    };
  }

  public static @NotNull String getTypeName(@NotNull JdlFieldType jdlFieldType) {
    return jdlFieldType.getText();
  }
}
