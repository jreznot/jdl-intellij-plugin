package org.strangeway.jdl;

import com.intellij.lang.Language;
import com.intellij.openapi.fileTypes.LanguageFileType;

public final class JdlLanguage extends Language {

  public final static JdlLanguage INSTANCE = new JdlLanguage();

  private JdlLanguage() {
    super("JDL");
  }

  @Override
  public boolean isCaseSensitive() {
    return true;
  }

  @Override
  public LanguageFileType getAssociatedFileType() {
    return JdlFileType.INSTANCE;
  }
}
