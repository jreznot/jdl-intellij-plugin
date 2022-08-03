package org.strangeway.jdl.lexer;

import com.intellij.lexer.FlexLexer;
import com.intellij.psi.tree.IElementType;
import static com.intellij.psi.TokenType.BAD_CHARACTER;
import static com.intellij.psi.TokenType.WHITE_SPACE;
import static org.strangeway.jdl.psi.JdlTokenTypes.*;
%%

%{
  public _JdlLexer() {
    this((java.io.Reader)null);
  }
%}

%public
%class _JdlLexer
%implements FlexLexer
%function advance
%type IElementType
%unicode

IDENTIFIER=([:letter:]|[_])([:letter:]|[._\-0-9])*

ESCAPE_SEQUENCE=\\[^\r\n]
DOUBLE_QUOTED_STRING=\"([^\\\"\r\n]|{ESCAPE_SEQUENCE})*(\"|\\)?

ESCAPED_SLASH=\\\/
REGEX_STRING=\/([^/]|{ESCAPED_SLASH})*(\/)?[a-zA-Z]*

INTEGER_LITERAL=0|[1-9][0-9]*

DIGIT=[0-9]
DOUBLE_LITERAL=(({FLOATING_POINT_LITERAL1})|({FLOATING_POINT_LITERAL2})|({FLOATING_POINT_LITERAL3}))
FLOATING_POINT_LITERAL1=({DIGIT})+"."({DIGIT})*({EXPONENT_PART})?
FLOATING_POINT_LITERAL2="."({DIGIT})+({EXPONENT_PART})?
FLOATING_POINT_LITERAL3=({DIGIT})+({EXPONENT_PART})
EXPONENT_PART=[Ee]["+""-"]?({DIGIT})*

LINE_COMMENT="//".*
BLOCK_COMMENT="/"\*([^*]|\*+[^*/])*(\*+"/")?

WHITE_SPACE = [\t ]+
NEWLINE = \r\n|[\r\n]

%state INSIDE_BLOCK

%%

<YYINITIAL, INSIDE_BLOCK> {
    ","                                  { return COMMA; }
    ":"                                  { return COLON; }
    "*"                                  { return WILDCARD; }
    "true"                               { return TRUE; }
    "false"                              { return FALSE; }
    "["                                  { return LBRACKET; }
    "]"                                  { return RBRACKET; }
    "("                                  { return LPARENTH; }
    ")"                                  { return RPARENTH; }
    "{"                                  { return LBRACE; }
    "}"                                  { return RBRACE; }
    "@"                                  { return STRUDEL; }

    "application"                        { return APPLICATION_KEYWORD; }
    "config"                             { return CONFIG_KEYWORD; }
    "entities"                           { return ENTITIES_KEYWORD; }
    "dto"                                { return DTO_KEYWORD; }
    "entity"                             { return ENTITY_KEYWORD; }
    "enum"                               { return ENUM_KEYWORD; }
    "paginate"                           { return PAGINATE_KEYWORD; }
    "service"                            { return SERVICE_KEYWORD; }
    "with"                               { return WITH_KEYWORD; }
    "except"                             { return EXCEPT_KEYWORD; }
    "deployment"                         { return DEPLOYMENT_KEYWORD; }
    "relationship"                       { return RELATIONSHIP_KEYWORD; }
    "to"                                 { return TO_KEYWORD; }

    {IDENTIFIER}                         { return IDENTIFIER; }
    {INTEGER_LITERAL}                    { return INTEGER_NUMBER; }
    {DOUBLE_LITERAL}                     { return DOUBLE_NUMBER; }
    {DOUBLE_QUOTED_STRING}               { return DOUBLE_QUOTED_STRING; }
    {LINE_COMMENT}                       { return LINE_COMMENT; }
    {BLOCK_COMMENT}                      { return BLOCK_COMMENT; }
    {NEWLINE}                            { return NEWLINE; }
    {WHITE_SPACE}                        { return WHITE_SPACE; }
    {REGEX_STRING}                       { return REGEX_STRING; }
}

[^] { return BAD_CHARACTER; }