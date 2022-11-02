package org.strangeway.jdl;

import com.intellij.icons.AllIcons;
import com.intellij.ui.LayeredIcon;

import javax.swing.*;

import static com.intellij.openapi.util.IconLoader.getIcon;

public final class JhipsterIcons {
  public static final Icon FILE_ICON = getIcon("/org/strangeway/jdl/icons/file.svg", JhipsterIcons.class);
  private static final Icon REQUIRED_MARK_ICON = getIcon("/org/strangeway/jdl/icons/required-mark.svg", JhipsterIcons.class);
  private static final Icon REQUIRED_FIELD = new LayeredIcon(AllIcons.Nodes.Field, REQUIRED_MARK_ICON);

  public static Icon getRelationshipIcon() {
    return AllIcons.General.InheritedMethod;
  }

  public static Icon getEntityIcon() {
    return AllIcons.Javaee.PersistenceEntity;
  }

  public static Icon getEnumIcon() {
    return AllIcons.Nodes.Enum;
  }

  public static Icon getConfigurationPropertyIcon() {
    return AllIcons.Nodes.PropertyWriteStatic;
  }

  public static Icon getPropertyIcon() {
    return AllIcons.Nodes.PropertyWrite;
  }

  public static Icon getConfigIcon() {
    return AllIcons.Json.Object;
  }

  public static Icon getApplicationIcon() {
    return AllIcons.RunConfigurations.Applet;
  }

  public static Icon getFieldIcon() {
    return AllIcons.Nodes.Field;
  }

  public static Icon getRequiredFieldIcon() {
    return REQUIRED_FIELD;
  }

  public static Icon getConstantIcon() {
    return AllIcons.Nodes.Constant;
  }

  public static Icon getDeployIcon() {
    return AllIcons.Nodes.Deploy;
  }

  public static Icon getFieldConstraintIcon() {
    return AllIcons.General.InspectionsOK;
  }

  public static Icon getFieldTypeIcon() {
    return AllIcons.Nodes.Type;
  }
}
