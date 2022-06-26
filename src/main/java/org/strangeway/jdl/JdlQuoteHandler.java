// Copyright 2000-2020 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.
package org.strangeway.jdl;

import com.intellij.codeInsight.editorActions.SimpleTokenSetQuoteHandler;
import org.strangeway.jdl.psi.JdlTokenTypes;

public final class JdlQuoteHandler extends SimpleTokenSetQuoteHandler {
  public JdlQuoteHandler() {
    super(JdlTokenTypes.DOUBLE_QUOTED_STRING);
  }
}
