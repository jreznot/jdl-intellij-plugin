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

package org.strangeway.jdl.model;

import com.intellij.psi.PsiFile;
import org.jetbrains.annotations.NotNull;
import org.strangeway.jdl.psi.JdlConstant;
import org.strangeway.jdl.psi.JdlEntity;
import org.strangeway.jdl.psi.JdlEnum;
import org.strangeway.jdl.psi.JdlVisitor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public final class JdlDeclarationsModel {
  private JdlDeclarationsModel() {
  }

  public static Collection<JdlEnum> findAllEnums(PsiFile file) {
    List<JdlEnum> enums = new ArrayList<>();

    file.acceptChildren(new JdlVisitor() {
      @Override
      public void visitEnum(@NotNull JdlEnum o) {
        super.visitEnum(o);

        enums.add(o);
      }
    });

    return enums;
  }

  public static Collection<JdlEntity> findAllEntities(PsiFile file) {
    List<JdlEntity> entities = new ArrayList<>();

    file.acceptChildren(new JdlVisitor() {
      @Override
      public void visitEntity(@NotNull JdlEntity o) {
        super.visitEntity(o);

        entities.add(o);
      }
    });

    return entities;
  }

  public static Collection<JdlConstant> findAllJdlConstants(PsiFile file) {
    List<JdlConstant> entities = new ArrayList<>();

    file.acceptChildren(new JdlVisitor() {
      @Override
      public void visitConstant(@NotNull JdlConstant o) {
        super.visitConstant(o);

        entities.add(o);
      }
    });

    return entities;
  }
}
