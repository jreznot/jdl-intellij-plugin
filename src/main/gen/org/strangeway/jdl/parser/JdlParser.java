// This is a generated file. Not intended for manual editing.
package org.strangeway.jdl.parser;

import com.intellij.lang.PsiBuilder;
import com.intellij.lang.PsiBuilder.Marker;
import static org.strangeway.jdl.psi.JdlTokenTypes.*;
import static com.intellij.lang.parser.GeneratedParserUtilBase.*;
import com.intellij.psi.tree.IElementType;
import com.intellij.lang.ASTNode;
import com.intellij.psi.tree.TokenSet;
import com.intellij.lang.PsiParser;
import com.intellij.lang.LightPsiParser;

@SuppressWarnings({"SimplifiableIfStatement", "UnusedAssignment"})
public class JdlParser implements PsiParser, LightPsiParser {

  public ASTNode parse(IElementType t, PsiBuilder b) {
    parseLight(t, b);
    return b.getTreeBuilt();
  }

  public void parseLight(IElementType t, PsiBuilder b) {
    boolean r;
    b = adapt_builder_(t, b, this, null);
    Marker m = enter_section_(b, 0, _COLLAPSE_, null);
    r = parse_root_(t, b);
    exit_section_(b, 0, m, t, r, true, TRUE_CONDITION);
  }

  protected boolean parse_root_(IElementType t, PsiBuilder b) {
    return parse_root_(t, b, 0);
  }

  static boolean parse_root_(IElementType t, PsiBuilder b, int l) {
    return root(b, l + 1);
  }

  /* ********************************************************** */
  // APPLICATION_KEYWORD LBRACE applicationContent RBRACE
  public static boolean applicationBlock(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "applicationBlock")) return false;
    if (!nextTokenIs(b, APPLICATION_KEYWORD)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, APPLICATION_BLOCK, null);
    r = consumeTokens(b, 1, APPLICATION_KEYWORD, LBRACE);
    p = r; // pin = 1
    r = r && report_error_(b, applicationContent(b, l + 1));
    r = p && consumeToken(b, RBRACE) && r;
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  /* ********************************************************** */
  // configBlock (entitiesOption| dtoOption | paginateOption | serviceOption)*
  static boolean applicationContent(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "applicationContent")) return false;
    if (!nextTokenIs(b, CONFIG_KEYWORD)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = configBlock(b, l + 1);
    r = r && applicationContent_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // (entitiesOption| dtoOption | paginateOption | serviceOption)*
  private static boolean applicationContent_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "applicationContent_1")) return false;
    while (true) {
      int c = current_position_(b);
      if (!applicationContent_1_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "applicationContent_1", c)) break;
    }
    return true;
  }

  // entitiesOption| dtoOption | paginateOption | serviceOption
  private static boolean applicationContent_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "applicationContent_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = entitiesOption(b, l + 1);
    if (!r) r = dtoOption(b, l + 1);
    if (!r) r = paginateOption(b, l + 1);
    if (!r) r = serviceOption(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // value (COMMA|&RBRACKET)
  static boolean arrayElement(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "arrayElement")) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_);
    r = value(b, l + 1);
    p = r; // pin = 1
    r = r && arrayElement_1(b, l + 1);
    exit_section_(b, l, m, r, p, JdlParser::notBracketOrNextValue);
    return r || p;
  }

  // COMMA|&RBRACKET
  private static boolean arrayElement_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "arrayElement_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, COMMA);
    if (!r) r = arrayElement_1_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // &RBRACKET
  private static boolean arrayElement_1_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "arrayElement_1_1")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _AND_);
    r = consumeToken(b, RBRACKET);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // LBRACKET arrayElement* RBRACKET
  public static boolean arrayLiteral(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "arrayLiteral")) return false;
    if (!nextTokenIs(b, LBRACKET)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, ARRAY_LITERAL, null);
    r = consumeToken(b, LBRACKET);
    p = r; // pin = 1
    r = r && report_error_(b, arrayLiteral_1(b, l + 1));
    r = p && consumeToken(b, RBRACKET) && r;
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  // arrayElement*
  private static boolean arrayLiteral_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "arrayLiteral_1")) return false;
    while (true) {
      int c = current_position_(b);
      if (!arrayElement(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "arrayLiteral_1", c)) break;
    }
    return true;
  }

  /* ********************************************************** */
  // TRUE | FALSE
  public static boolean booleanLiteral(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "booleanLiteral")) return false;
    if (!nextTokenIs(b, "<boolean literal>", FALSE, TRUE)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, BOOLEAN_LITERAL, "<boolean literal>");
    r = consumeToken(b, TRUE);
    if (!r) r = consumeToken(b, FALSE);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // CONFIG_KEYWORD LBRACE optionNameValue* RBRACE
  public static boolean configBlock(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "configBlock")) return false;
    if (!nextTokenIs(b, CONFIG_KEYWORD)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, CONFIG_BLOCK, null);
    r = consumeTokens(b, 1, CONFIG_KEYWORD, LBRACE);
    p = r; // pin = 1
    r = r && report_error_(b, configBlock_2(b, l + 1));
    r = p && consumeToken(b, RBRACE) && r;
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  // optionNameValue*
  private static boolean configBlock_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "configBlock_2")) return false;
    while (true) {
      int c = current_position_(b);
      if (!optionNameValue(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "configBlock_2", c)) break;
    }
    return true;
  }

  /* ********************************************************** */
  // DEPLOYMENT_KEYWORD LBRACE optionNameValue* RBRACE
  public static boolean deploymentBlock(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "deploymentBlock")) return false;
    if (!nextTokenIs(b, DEPLOYMENT_KEYWORD)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, DEPLOYMENT_BLOCK, null);
    r = consumeTokens(b, 1, DEPLOYMENT_KEYWORD, LBRACE);
    p = r; // pin = 1
    r = r && report_error_(b, deploymentBlock_2(b, l + 1));
    r = p && consumeToken(b, RBRACE) && r;
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  // optionNameValue*
  private static boolean deploymentBlock_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "deploymentBlock_2")) return false;
    while (true) {
      int c = current_position_(b);
      if (!optionNameValue(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "deploymentBlock_2", c)) break;
    }
    return true;
  }

  /* ********************************************************** */
  // DTO_KEYWORD WILDCARD withOption?
  public static boolean dtoOption(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "dtoOption")) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, DTO_OPTION, "<dto option>");
    r = consumeTokens(b, 1, DTO_KEYWORD, WILDCARD);
    p = r; // pin = 1
    r = r && dtoOption_2(b, l + 1);
    exit_section_(b, l, m, r, p, JdlParser::notBraceOrNextBlock);
    return r || p;
  }

  // withOption?
  private static boolean dtoOption_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "dtoOption_2")) return false;
    withOption(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // entityId (COMMA entityId)*
  public static boolean entitiesList(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "entitiesList")) return false;
    if (!nextTokenIs(b, IDENTIFIER)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = entityId(b, l + 1);
    r = r && entitiesList_1(b, l + 1);
    exit_section_(b, m, ENTITIES_LIST, r);
    return r;
  }

  // (COMMA entityId)*
  private static boolean entitiesList_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "entitiesList_1")) return false;
    while (true) {
      int c = current_position_(b);
      if (!entitiesList_1_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "entitiesList_1", c)) break;
    }
    return true;
  }

  // COMMA entityId
  private static boolean entitiesList_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "entitiesList_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, COMMA);
    r = r && entityId(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // ENTITIES_KEYWORD (wildcardLiteral | entitiesList) exceptEntities?
  public static boolean entitiesOption(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "entitiesOption")) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, ENTITIES_OPTION, "<entities option>");
    r = consumeToken(b, ENTITIES_KEYWORD);
    p = r; // pin = 1
    r = r && report_error_(b, entitiesOption_1(b, l + 1));
    r = p && entitiesOption_2(b, l + 1) && r;
    exit_section_(b, l, m, r, p, JdlParser::notBraceOrNextBlock);
    return r || p;
  }

  // wildcardLiteral | entitiesList
  private static boolean entitiesOption_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "entitiesOption_1")) return false;
    boolean r;
    r = wildcardLiteral(b, l + 1);
    if (!r) r = entitiesList(b, l + 1);
    return r;
  }

  // exceptEntities?
  private static boolean entitiesOption_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "entitiesOption_2")) return false;
    exceptEntities(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // ENTITY_KEYWORD id (LPARENTH entityTableName RPARENTH)? (LBRACE entityContent RBRACE)?
  public static boolean entityBlock(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "entityBlock")) return false;
    if (!nextTokenIs(b, ENTITY_KEYWORD)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, ENTITY_BLOCK, null);
    r = consumeToken(b, ENTITY_KEYWORD);
    p = r; // pin = 1
    r = r && report_error_(b, id(b, l + 1));
    r = p && report_error_(b, entityBlock_2(b, l + 1)) && r;
    r = p && entityBlock_3(b, l + 1) && r;
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  // (LPARENTH entityTableName RPARENTH)?
  private static boolean entityBlock_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "entityBlock_2")) return false;
    entityBlock_2_0(b, l + 1);
    return true;
  }

  // LPARENTH entityTableName RPARENTH
  private static boolean entityBlock_2_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "entityBlock_2_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, LPARENTH);
    r = r && entityTableName(b, l + 1);
    r = r && consumeToken(b, RPARENTH);
    exit_section_(b, m, null, r);
    return r;
  }

  // (LBRACE entityContent RBRACE)?
  private static boolean entityBlock_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "entityBlock_3")) return false;
    entityBlock_3_0(b, l + 1);
    return true;
  }

  // LBRACE entityContent RBRACE
  private static boolean entityBlock_3_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "entityBlock_3_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, LBRACE);
    r = r && entityContent(b, l + 1);
    r = r && consumeToken(b, RBRACE);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // entityFieldMapping*
  static boolean entityContent(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "entityContent")) return false;
    while (true) {
      int c = current_position_(b);
      if (!entityFieldMapping(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "entityContent", c)) break;
    }
    return true;
  }

  /* ********************************************************** */
  // fieldName fieldType
  public static boolean entityFieldMapping(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "entityFieldMapping")) return false;
    if (!nextTokenIs(b, IDENTIFIER)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = fieldName(b, l + 1);
    r = r && fieldType(b, l + 1);
    exit_section_(b, m, ENTITY_FIELD_MAPPING, r);
    return r;
  }

  /* ********************************************************** */
  // IDENTIFIER
  public static boolean entityId(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "entityId")) return false;
    if (!nextTokenIs(b, "<entity identifier>", IDENTIFIER)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, ENTITY_ID, "<entity identifier>");
    r = consumeToken(b, IDENTIFIER);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // IDENTIFIER
  public static boolean entityTableName(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "entityTableName")) return false;
    if (!nextTokenIs(b, "<table name>", IDENTIFIER)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, ENTITY_TABLE_NAME, "<table name>");
    r = consumeToken(b, IDENTIFIER);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // ENUM_KEYWORD id LBRACE enumContent RBRACE
  public static boolean enumBlock(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "enumBlock")) return false;
    if (!nextTokenIs(b, ENUM_KEYWORD)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, ENUM_BLOCK, null);
    r = consumeToken(b, ENUM_KEYWORD);
    p = r; // pin = 1
    r = r && report_error_(b, id(b, l + 1));
    r = p && report_error_(b, consumeToken(b, LBRACE)) && r;
    r = p && report_error_(b, enumContent(b, l + 1)) && r;
    r = p && consumeToken(b, RBRACE) && r;
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  /* ********************************************************** */
  // enumValue*
  static boolean enumContent(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "enumContent")) return false;
    while (true) {
      int c = current_position_(b);
      if (!enumValue(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "enumContent", c)) break;
    }
    return true;
  }

  /* ********************************************************** */
  // IDENTIFIER
  public static boolean enumKey(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "enumKey")) return false;
    if (!nextTokenIs(b, "<enum key>", IDENTIFIER)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, ENUM_KEY, "<enum key>");
    r = consumeToken(b, IDENTIFIER);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // enumKey
  public static boolean enumValue(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "enumValue")) return false;
    if (!nextTokenIs(b, IDENTIFIER)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = enumKey(b, l + 1);
    exit_section_(b, m, ENUM_VALUE, r);
    return r;
  }

  /* ********************************************************** */
  // EXCEPT_KEYWORD entitiesList
  public static boolean exceptEntities(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "exceptEntities")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, EXCEPT_ENTITIES, "<except entities>");
    r = consumeToken(b, EXCEPT_KEYWORD);
    r = r && entitiesList(b, l + 1);
    exit_section_(b, l, m, r, false, JdlParser::notBraceOrNextBlock);
    return r;
  }

  /* ********************************************************** */
  // IDENTIFIER
  public static boolean fieldName(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "fieldName")) return false;
    if (!nextTokenIs(b, "<field name>", IDENTIFIER)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, FIELD_NAME, "<field name>");
    r = consumeToken(b, IDENTIFIER);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // IDENTIFIER
  public static boolean fieldType(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "fieldType")) return false;
    if (!nextTokenIs(b, "<field type>", IDENTIFIER)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, FIELD_TYPE, "<field type>");
    r = consumeToken(b, IDENTIFIER);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // IDENTIFIER
  public static boolean id(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "id")) return false;
    if (!nextTokenIs(b, "<identifier>", IDENTIFIER)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, ID, "<identifier>");
    r = consumeToken(b, IDENTIFIER);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // !('}'|APPLICATION_KEYWORD|ENTITY_KEYWORD|DTO_KEYWORD|ENTITIES_KEYWORD|SERVICE_KEYWORD|PAGINATE_KEYWORD|ENUM_KEYWORD|DEPLOYMENT_KEYWORD)
  static boolean notBraceOrNextBlock(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "notBraceOrNextBlock")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NOT_);
    r = !notBraceOrNextBlock_0(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // '}'|APPLICATION_KEYWORD|ENTITY_KEYWORD|DTO_KEYWORD|ENTITIES_KEYWORD|SERVICE_KEYWORD|PAGINATE_KEYWORD|ENUM_KEYWORD|DEPLOYMENT_KEYWORD
  private static boolean notBraceOrNextBlock_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "notBraceOrNextBlock_0")) return false;
    boolean r;
    r = consumeToken(b, RBRACE);
    if (!r) r = consumeToken(b, APPLICATION_KEYWORD);
    if (!r) r = consumeToken(b, ENTITY_KEYWORD);
    if (!r) r = consumeToken(b, DTO_KEYWORD);
    if (!r) r = consumeToken(b, ENTITIES_KEYWORD);
    if (!r) r = consumeToken(b, SERVICE_KEYWORD);
    if (!r) r = consumeToken(b, PAGINATE_KEYWORD);
    if (!r) r = consumeToken(b, ENUM_KEYWORD);
    if (!r) r = consumeToken(b, DEPLOYMENT_KEYWORD);
    return r;
  }

  /* ********************************************************** */
  // !(RBRACKET|value)
  static boolean notBracketOrNextValue(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "notBracketOrNextValue")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NOT_);
    r = !notBracketOrNextValue_0(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // RBRACKET|value
  private static boolean notBracketOrNextValue_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "notBracketOrNextValue_0")) return false;
    boolean r;
    r = consumeToken(b, RBRACKET);
    if (!r) r = value(b, l + 1);
    return r;
  }

  /* ********************************************************** */
  // DOUBLE_NUMBER | INTEGER_NUMBER
  public static boolean numberLiteral(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "numberLiteral")) return false;
    if (!nextTokenIs(b, "<number literal>", DOUBLE_NUMBER, INTEGER_NUMBER)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, NUMBER_LITERAL, "<number literal>");
    r = consumeToken(b, DOUBLE_NUMBER);
    if (!r) r = consumeToken(b, INTEGER_NUMBER);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // IDENTIFIER
  public static boolean optionName(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "optionName")) return false;
    if (!nextTokenIs(b, "<option name>", IDENTIFIER)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, OPTION_NAME, "<option name>");
    r = consumeToken(b, IDENTIFIER);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // optionName value
  public static boolean optionNameValue(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "optionNameValue")) return false;
    if (!nextTokenIs(b, "<option>", IDENTIFIER)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, OPTION_NAME_VALUE, "<option>");
    r = optionName(b, l + 1);
    r = r && value(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // PAGINATE_KEYWORD (wildcardLiteral | entitiesList) withOption? exceptEntities?
  public static boolean paginateOption(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "paginateOption")) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, PAGINATE_OPTION, "<paginate option>");
    r = consumeToken(b, PAGINATE_KEYWORD);
    p = r; // pin = 1
    r = r && report_error_(b, paginateOption_1(b, l + 1));
    r = p && report_error_(b, paginateOption_2(b, l + 1)) && r;
    r = p && paginateOption_3(b, l + 1) && r;
    exit_section_(b, l, m, r, p, JdlParser::notBraceOrNextBlock);
    return r || p;
  }

  // wildcardLiteral | entitiesList
  private static boolean paginateOption_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "paginateOption_1")) return false;
    boolean r;
    r = wildcardLiteral(b, l + 1);
    if (!r) r = entitiesList(b, l + 1);
    return r;
  }

  // withOption?
  private static boolean paginateOption_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "paginateOption_2")) return false;
    withOption(b, l + 1);
    return true;
  }

  // exceptEntities?
  private static boolean paginateOption_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "paginateOption_3")) return false;
    exceptEntities(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // 'pattern' LPARENTH regexLiteral RPARENTH
  public static boolean patternCall(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "patternCall")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, PATTERN_CALL, "<pattern call>");
    r = consumeToken(b, "pattern");
    r = r && consumeToken(b, LPARENTH);
    r = r && regexLiteral(b, l + 1);
    r = r && consumeToken(b, RPARENTH);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // REGEX_STRING
  public static boolean regexLiteral(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "regexLiteral")) return false;
    if (!nextTokenIs(b, "<regex literal>", REGEX_STRING)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, REGEX_LITERAL, "<regex literal>");
    r = consumeToken(b, REGEX_STRING);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // (applicationBlock | entityBlock | enumBlock | deploymentBlock | paginateOption | dtoOption | serviceOption)*
  static boolean root(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "root")) return false;
    while (true) {
      int c = current_position_(b);
      if (!root_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "root", c)) break;
    }
    return true;
  }

  // applicationBlock | entityBlock | enumBlock | deploymentBlock | paginateOption | dtoOption | serviceOption
  private static boolean root_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "root_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = applicationBlock(b, l + 1);
    if (!r) r = entityBlock(b, l + 1);
    if (!r) r = enumBlock(b, l + 1);
    if (!r) r = deploymentBlock(b, l + 1);
    if (!r) r = paginateOption(b, l + 1);
    if (!r) r = dtoOption(b, l + 1);
    if (!r) r = serviceOption(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // SERVICE_KEYWORD (wildcardLiteral | entitiesList) withOption?
  public static boolean serviceOption(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "serviceOption")) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, SERVICE_OPTION, "<service option>");
    r = consumeToken(b, SERVICE_KEYWORD);
    p = r; // pin = 1
    r = r && report_error_(b, serviceOption_1(b, l + 1));
    r = p && serviceOption_2(b, l + 1) && r;
    exit_section_(b, l, m, r, p, JdlParser::notBraceOrNextBlock);
    return r || p;
  }

  // wildcardLiteral | entitiesList
  private static boolean serviceOption_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "serviceOption_1")) return false;
    boolean r;
    r = wildcardLiteral(b, l + 1);
    if (!r) r = entitiesList(b, l + 1);
    return r;
  }

  // withOption?
  private static boolean serviceOption_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "serviceOption_2")) return false;
    withOption(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // DOUBLE_QUOTED_STRING
  public static boolean stringLiteral(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "stringLiteral")) return false;
    if (!nextTokenIs(b, DOUBLE_QUOTED_STRING)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, DOUBLE_QUOTED_STRING);
    exit_section_(b, m, STRING_LITERAL, r);
    return r;
  }

  /* ********************************************************** */
  // id | booleanLiteral | stringLiteral | numberLiteral | arrayLiteral | patternCall
  public static boolean value(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "value")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, VALUE, "<value>");
    r = id(b, l + 1);
    if (!r) r = booleanLiteral(b, l + 1);
    if (!r) r = stringLiteral(b, l + 1);
    if (!r) r = numberLiteral(b, l + 1);
    if (!r) r = arrayLiteral(b, l + 1);
    if (!r) r = patternCall(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // WILDCARD
  public static boolean wildcardLiteral(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "wildcardLiteral")) return false;
    if (!nextTokenIs(b, "<*>", WILDCARD)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, WILDCARD_LITERAL, "<*>");
    r = consumeToken(b, WILDCARD);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // WITH_KEYWORD withOptionValue
  static boolean withOption(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "withOption")) return false;
    if (!nextTokenIs(b, WITH_KEYWORD)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, WITH_KEYWORD);
    r = r && withOptionValue(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // IDENTIFIER
  public static boolean withOptionValue(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "withOptionValue")) return false;
    if (!nextTokenIs(b, IDENTIFIER)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, IDENTIFIER);
    exit_section_(b, m, WITH_OPTION_VALUE, r);
    return r;
  }

}
