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

import com.intellij.ide.navigationToolbar.StructureAwareNavBarModelExtension;
import com.intellij.lang.Language;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.strangeway.jdl.psi.*;

import javax.swing.*;

import static org.strangeway.jdl.JdlConstants.CONFIG_BLOCK_NAME;
import static org.strangeway.jdl.JdlConstants.DEPLOYMENT_BLOCK_NAME;

final class JdlNavbarExtension extends StructureAwareNavBarModelExtension {
  @NotNull
  @Override
  protected Language getLanguage() {
    return JdlLanguage.INSTANCE;
  }

  @Override
  public @Nullable Icon getIcon(Object object) {
    if (object instanceof JdlEntity) {
      return JhipsterIcons.getEntityIcon();
    }
    if (object instanceof JdlEnum) {
      return JhipsterIcons.getEnumIcon();
    }
    if (object instanceof JdlApplication) {
      return JhipsterIcons.getApplicationIcon();
    }
    if (object instanceof JdlEntityFieldMapping) {
      return JhipsterIcons.getFieldIcon();
    }
    if (object instanceof JdlEnumValue) {
      return JhipsterIcons.getFieldIcon();
    }
    if (object instanceof JdlOptionNameValue) {
      return JhipsterIcons.getPropertyIcon();
    }
    if (object instanceof JdlConfigBlock) {
      return JhipsterIcons.getConfigIcon();
    }
    if (object instanceof JdlConfigurationOption) {
      return JhipsterIcons.getConfigurationPropertyIcon();
    }
    if (object instanceof JdlRelationshipGroup) {
      return JhipsterIcons.getRelationshipIcon();
    }
    if (object instanceof JdlDeployment) {
      return JhipsterIcons.getDeployIcon();
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
    if (object instanceof JdlDeployment) {
      return DEPLOYMENT_BLOCK_NAME;
    }
    return null;
  }
}
