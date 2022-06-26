package org.strangeway.jdl;

import com.intellij.openapi.fileTypes.LanguageFileType;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

public class JdlFileType extends LanguageFileType {
  public final static JdlFileType INSTANCE = new JdlFileType();

  protected JdlFileType() {
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
  public @Nullable Icon getIcon() {
    return JhipsterIcons.FILE_ICON;
  }
}
