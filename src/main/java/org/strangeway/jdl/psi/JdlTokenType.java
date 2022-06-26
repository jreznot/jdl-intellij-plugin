// Copyright 2000-2020 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.
package org.strangeway.jdl.psi;

import com.intellij.psi.tree.IElementType;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import org.strangeway.jdl.JdlLanguage;

public final class JdlTokenType extends IElementType {
  JdlTokenType(@NotNull @NonNls String debugName) {
    super(debugName, JdlLanguage.INSTANCE);
  }
}
