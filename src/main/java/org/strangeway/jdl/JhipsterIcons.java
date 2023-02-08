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
