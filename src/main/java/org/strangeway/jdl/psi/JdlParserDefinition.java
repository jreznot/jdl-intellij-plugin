// Copyright 2000-2020 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.
package org.strangeway.jdl.psi;

import com.intellij.lang.ASTNode;
import com.intellij.lang.ParserDefinition;
import com.intellij.lang.PsiParser;
import com.intellij.lexer.Lexer;
import com.intellij.openapi.project.Project;
import com.intellij.psi.FileViewProvider;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.tree.IFileElementType;
import com.intellij.psi.tree.TokenSet;
import org.jetbrains.annotations.NotNull;
import org.strangeway.jdl.JdlLanguage;
import org.strangeway.jdl.JdlLexer;
import org.strangeway.jdl.parser.JdlParser;

public final class JdlParserDefinition implements ParserDefinition {
  public static final IFileElementType FILE = new IFileElementType(JdlLanguage.INSTANCE);

  @Override
  public @NotNull Lexer createLexer(Project project) {
    return new JdlLexer();
  }

  @Override
  public @NotNull PsiParser createParser(Project project) {
    return new JdlParser();
  }

  @Override
  public @NotNull PsiFile createFile(@NotNull FileViewProvider viewProvider) {
    return new JdlFile(viewProvider);
  }

  @Override
  public @NotNull IFileElementType getFileNodeType() {
    return FILE;
  }

  @Override
  public @NotNull TokenSet getWhitespaceTokens() {
    return TokenSet.WHITE_SPACE;
  }

  @Override
  public @NotNull TokenSet getCommentTokens() {
    return JdlTokenSets.COMMENTS;
  }

  @Override
  public @NotNull TokenSet getStringLiteralElements() {
    return JdlTokenSets.STRINGS;
  }

  @Override
  public @NotNull PsiElement createElement(ASTNode node) {
    return JdlTokenTypes.Factory.createElement(node);
  }
}
