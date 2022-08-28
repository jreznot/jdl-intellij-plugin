package org.strangeway.jdl.ultimate;

import com.intellij.execution.lineMarker.ExecutorAction;
import com.intellij.execution.lineMarker.RunLineMarkerContributor;
import com.intellij.icons.AllIcons.RunConfigurations.TestState;
import com.intellij.lang.ASTNode;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.strangeway.jdl.psi.JdlTokenTypes;

import java.util.function.Function;

final class JdlRunLineMarkerContributor extends RunLineMarkerContributor {
  private static final Function<PsiElement, String> TOOLTIP_PROVIDER = psiElement -> "Run jhipster generator";

  @Override
  public @Nullable Info getInfo(@NotNull PsiElement element) {
    ASTNode node = element.getNode();
    if (node != null && node.getElementType() == JdlTokenTypes.APPLICATION_KEYWORD) {
      AnAction[] actions = ExecutorAction.getActions(Integer.MAX_VALUE);
      return new Info(TestState.Run, actions, TOOLTIP_PROVIDER);
    }
    return null;
  }
}
