package org.strangeway.jdl;

import com.intellij.codeInsight.template.TemplateActionContext;
import com.intellij.codeInsight.template.TemplateContextType;
import org.jetbrains.annotations.NotNull;
import org.strangeway.jdl.psi.JdlFile;

final class JdlLiveTemplateContextType extends TemplateContextType {
  public JdlLiveTemplateContextType() {
    super("JDL", "JDL");
  }

  @Override
  public boolean isInContext(@NotNull TemplateActionContext templateActionContext) {
    return templateActionContext.getFile() instanceof JdlFile;
  }
}
