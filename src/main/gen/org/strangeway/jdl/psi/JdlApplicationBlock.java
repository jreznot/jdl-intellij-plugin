// This is a generated file. Not intended for manual editing.
package org.strangeway.jdl.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;
import com.intellij.navigation.ItemPresentation;

public interface JdlApplicationBlock extends PsiElement {

  @NotNull
  List<JdlConfigBlock> getConfigBlockList();

  @NotNull
  List<JdlConfigurationOption> getConfigurationOptionList();

  @NotNull ItemPresentation getPresentation();

}
