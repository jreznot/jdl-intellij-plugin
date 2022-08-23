package org.strangeway.jdl;

import com.intellij.icons.AllIcons;
import com.intellij.ide.navigationToolbar.StructureAwareNavBarModelExtension;
import com.intellij.lang.Language;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.strangeway.jdl.psi.*;

import javax.swing.*;

import static org.strangeway.jdl.JdlConstants.CONFIG_BLOCK_NAME;

final class JdlNavbarExtension extends StructureAwareNavBarModelExtension {
  @NotNull
  @Override
  protected Language getLanguage() {
    return JdlLanguage.INSTANCE;
  }

  @Override
  public @Nullable Icon getIcon(Object object) {
    if (object instanceof JdlEntity) {
      return AllIcons.Javaee.PersistenceEntity;
    }
    if (object instanceof JdlEnum) {
      return AllIcons.Nodes.Enum;
    }
    if (object instanceof JdlApplication) {
      return AllIcons.RunConfigurations.Applet;
    }
    if (object instanceof JdlEntityFieldMapping) {
      return AllIcons.Nodes.Field;
    }
    if (object instanceof JdlEnumValue) {
      return AllIcons.Nodes.Field;
    }
    if (object instanceof JdlOptionNameValue) {
      return AllIcons.Nodes.PropertyWrite;
    }
    if (object instanceof JdlConfigBlock) {
      return AllIcons.Json.Object;
    }
    if (object instanceof JdlConfigurationOption) {
      return AllIcons.Nodes.PropertyWriteStatic;
    }
    if (object instanceof JdlRelationshipGroup) {
      return AllIcons.General.InheritedMethod;
    }
    return null;
  }

  @Override
  public @Nullable String getPresentableText(Object object) {
    if (object instanceof JdlEntity) {
      return ((JdlEntity) object).getName();
    }
    if (object instanceof JdlEnum) {
      return ((JdlEnum) object).getName();
    }
    if (object instanceof JdlApplication) {
      return ((JdlApplication) object).getName();
    }
    if (object instanceof JdlEntityFieldMapping) {
      return ((JdlEntityFieldMapping) object).getName();
    }
    if (object instanceof JdlEnumValue) {
      return ((JdlEnumValue) object).getName();
    }
    if (object instanceof JdlConfigBlock) {
      return CONFIG_BLOCK_NAME;
    }
    if (object instanceof JdlOptionNameValue) {
      return ((JdlOptionNameValue) object).getName();
    }
    if (object instanceof JdlConfigurationOption) {
      return ((JdlConfigurationOption) object).getName();
    }
    if (object instanceof JdlRelationshipGroup) {
      return ((JdlRelationshipGroup) object).getType();
    }
    return null;
  }
}
