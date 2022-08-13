package org.strangeway.jdl.model;

import com.intellij.psi.PsiFile;
import org.jetbrains.annotations.NotNull;
import org.strangeway.jdl.psi.JdlEntityBlock;
import org.strangeway.jdl.psi.JdlEnumBlock;
import org.strangeway.jdl.psi.JdlVisitor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public final class JdlDeclarationsModel {
  private JdlDeclarationsModel() {
  }

  public static Collection<JdlEnumBlock> getAllEnums(PsiFile file) {
    List<JdlEnumBlock> enums = new ArrayList<>();

    file.acceptChildren(new JdlVisitor() {
      @Override
      public void visitEnumBlock(@NotNull JdlEnumBlock o) {
        super.visitEnumBlock(o);

        enums.add(o);
      }
    });

    return enums;
  }

  public static Collection<JdlEntityBlock> getAllEntities(PsiFile file) {
    List<JdlEntityBlock> entities = new ArrayList<>();

    file.acceptChildren(new JdlVisitor() {
      @Override
      public void visitEntityBlock(@NotNull JdlEntityBlock o) {
        super.visitEntityBlock(o);

        entities.add(o);
      }
    });

    return entities;
  }
}
