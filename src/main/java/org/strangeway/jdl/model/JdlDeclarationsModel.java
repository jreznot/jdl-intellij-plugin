package org.strangeway.jdl.model;

import com.intellij.psi.PsiFile;
import org.jetbrains.annotations.NotNull;
import org.strangeway.jdl.psi.JdlEntity;
import org.strangeway.jdl.psi.JdlEnum;
import org.strangeway.jdl.psi.JdlVisitor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public final class JdlDeclarationsModel {
  private JdlDeclarationsModel() {
  }

  public static Collection<JdlEnum> getAllEnums(PsiFile file) {
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

  public static Collection<JdlEntity> getAllEntities(PsiFile file) {
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
}
