package org.strangeway.jdl;

import com.intellij.openapi.fileTypes.LanguageFileType;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

public final class JdlFileType extends LanguageFileType {
  public final static JdlFileType INSTANCE = new JdlFileType();

  public JdlFileType() {
    super(JdlLanguage.INSTANCE);
  }

  @Override
  public @NonNls @NotNull String getName() {
    return "JHipster JDL";
  }

  @Override
  public @NotNull String getDescription() {
    return "JHipster-specific domain language";
  }

  @Override
  public @NotNull String getDefaultExtension() {
    return "jdl";
  }

  @Override
  public @NotNull Icon getIcon() {
    return JhipsterIcons.FILE_ICON;
  }
}
