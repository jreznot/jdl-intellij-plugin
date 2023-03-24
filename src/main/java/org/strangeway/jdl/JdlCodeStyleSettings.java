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

import com.intellij.psi.codeStyle.CodeStyleSettings;
import com.intellij.psi.codeStyle.CommonCodeStyleSettings;
import com.intellij.psi.codeStyle.CustomCodeStyleSettings;
import org.intellij.lang.annotations.MagicConstant;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.PropertyKey;

public final class JdlCodeStyleSettings extends CustomCodeStyleSettings {

  public static final int ALIGN_PROPERTY_ON_VALUE = PropertyAlignment.ALIGN_ON_VALUE.getId();

  public boolean SPACE_BEFORE_LBRACE = true;

  @MagicConstant(flags = {
      CommonCodeStyleSettings.DO_NOT_WRAP,
      CommonCodeStyleSettings.WRAP_ALWAYS
  })
  @CommonCodeStyleSettings.WrapConstant
  public int BLOCK_WRAPPING = CommonCodeStyleSettings.WRAP_ALWAYS;

  public int PROPERTY_ALIGNMENT = PropertyAlignment.DO_NOT_ALIGN.getId();
  public int FIELD_ALIGNMENT = PropertyAlignment.DO_NOT_ALIGN.getId();

  public JdlCodeStyleSettings(CodeStyleSettings container) {
    super(JdlLanguage.INSTANCE.getID(), container);
  }

  public enum PropertyAlignment {
    DO_NOT_ALIGN(0, "formatter.align.properties.none"),
    ALIGN_ON_VALUE(1, "formatter.align.properties.allign");

    @PropertyKey(resourceBundle = JdlBundle.BUNDLE)
    private final String myKey;
    private final int myId;

    PropertyAlignment(int id, @NotNull @PropertyKey(resourceBundle = JdlBundle.BUNDLE) String key) {
      myKey = key;
      myId = id;
    }

    @NotNull
    public String getDescription() {
      return JdlBundle.message(myKey);
    }

    public int getId() {
      return myId;
    }
  }
}
