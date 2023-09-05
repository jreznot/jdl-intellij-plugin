// This is a generated file. Not intended for manual editing.
package org.strangeway.jdl.psi;

import com.intellij.navigation.ItemPresentation;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public interface JdlConfigBlock extends PsiElement {

  @NotNull
  JdlConfigKeyword getConfigKeyword();

  @NotNull
  List<JdlOptionNameValue> getOptionNameValueList();

  @NotNull ItemPresentation getPresentation();

}
