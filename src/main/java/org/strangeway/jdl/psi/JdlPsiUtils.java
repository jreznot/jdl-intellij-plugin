package org.strangeway.jdl.psi;

import com.intellij.icons.AllIcons;
import com.intellij.navigation.ItemPresentation;
import com.intellij.openapi.util.Key;
import com.intellij.openapi.util.Pair;
import com.intellij.openapi.util.TextRange;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.strangeway.jdl.JdlConstants;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static org.strangeway.jdl.JdlConstants.APPLICATION_UNNAMED;

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

  public static @NotNull ItemPresentation getPresentation(@NotNull JdlApplicationBlock applicationBlock) {
    return new ItemPresentation() {
      @Override
      public String getPresentableText() {
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

      @Override
      public Icon getIcon(boolean unused) {
        return AllIcons.RunConfigurations.Applet;
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
        return AllIcons.Nodes.PropertyWrite;
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
        return AllIcons.Nodes.Field;
      }
    };
  }

  public static @Nullable JdlEnumId getNameElement(@NotNull JdlEnumBlock enumBlock) {
    return enumBlock.getEnumId();
  }

  public static @NotNull String getName(@NotNull JdlEnumBlock enumBlock) {
    JdlEnumId enumId = enumBlock.getEnumId();
    if (enumId == null) return "";

    return enumId.getText();
  }

  public static @NotNull ItemPresentation getPresentation(@NotNull JdlEnumBlock enumBlock) {
    return new ItemPresentation() {
      @Override
      public @NotNull String getPresentableText() {
        return enumBlock.getName();
      }

      @Override
      public @NotNull Icon getIcon(boolean unused) {
        return AllIcons.Nodes.Enum;
      }
    };
  }

  public static @Nullable JdlEntityId getNameElement(@NotNull JdlEntityBlock entityBlock) {
    return entityBlock.getEntityId();
  }

  public static @NotNull String getName(@NotNull JdlEntityBlock entityBlock) {
    JdlEntityId enumId = entityBlock.getEntityId();
    if (enumId == null) return "";

    return enumId.getText();
  }

  public static @NotNull ItemPresentation getPresentation(@NotNull JdlEntityBlock entityBlock) {
    return new ItemPresentation() {
      @Override
      public @NotNull String getPresentableText() {
        return entityBlock.getName();
      }

      @Override
      public @NotNull Icon getIcon(boolean unused) {
        return AllIcons.Javaee.PersistenceEntity;
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
        return AllIcons.Nodes.PropertyWriteStatic;
      }
    };
  }

  public static @NotNull ItemPresentation getPresentation(@SuppressWarnings("unused") @NotNull JdlConfigBlock configBlock) {
    return new ItemPresentation() {
      @Override
      public @NotNull String getPresentableText() {
        return "config";
      }

      @Override
      public @NotNull Icon getIcon(boolean unused) {
        return AllIcons.Json.Object;
      }
    };
  }

  public static @NotNull ItemPresentation getPresentation(@NotNull JdlRelationshipBlock relationship) {
    return new ItemPresentation() {
      @Override
      public @NotNull String getPresentableText() {
        JdlRelationshipType relationshipType = relationship.getRelationshipType();
        if (relationshipType == null) return "<unknown>";

        return relationshipType.getText();
      }

      @Override
      public @NotNull Icon getIcon(boolean unused) {
        return AllIcons.General.InheritedMethod;
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

        JdlStringLiteral enumMappingValue = enumMapping.getStringLiteral();
        if (enumMappingValue == null) return null;

        return enumMappingValue.getText();
      }

      @Override
      public @NotNull Icon getIcon(boolean unused) {
        return AllIcons.Nodes.Field;
      }
    };
  }

  public static @NotNull String getName(@NotNull JdlConstantOption constant) {
    return constant.getConstantName().getText();
  }

  public static @NotNull JdlConstantName getNameElement(@NotNull JdlConstantOption constant) {
    return constant.getConstantName();
  }

  public static @NotNull ItemPresentation getPresentation(@NotNull JdlConstantOption constant) {
    return new ItemPresentation() {
      @Override
      public @NotNull String getPresentableText() {
        return constant.getName();
      }

      @Override
      public @Nullable String getLocationString() {
        var value = constant.getValue();
        if (value == null) return null;

        return value.getText();
      }

      @Override
      public @NotNull Icon getIcon(boolean unused) {
        return AllIcons.Nodes.Constant;
      }
    };
  }

  public static @NotNull ItemPresentation getPresentation(@NotNull JdlDeploymentBlock deploymentBlock) {
    return new ItemPresentation() {
      @Override
      public @NotNull String getPresentableText() {
        return "deployment";
      }

      @Override
      public @NotNull Icon getIcon(boolean unused) {
        return AllIcons.Nodes.Deploy;
      }
    };
  }
}
