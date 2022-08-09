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
  // APPLICATION_KEYWORD NEWLINE* LBRACE NEWLINE* applicationContent NEWLINE* RBRACE
  public static boolean applicationBlock(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "applicationBlock")) return false;
    if (!nextTokenIs(b, APPLICATION_KEYWORD)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, APPLICATION_BLOCK, null);
    r = consumeToken(b, APPLICATION_KEYWORD);
    p = r; // pin = 1
    r = r && report_error_(b, applicationBlock_1(b, l + 1));
    r = p && report_error_(b, consumeToken(b, LBRACE)) && r;
    r = p && report_error_(b, applicationBlock_3(b, l + 1)) && r;
    r = p && report_error_(b, applicationContent(b, l + 1)) && r;
    r = p && report_error_(b, applicationBlock_5(b, l + 1)) && r;
    r = p && consumeToken(b, RBRACE) && r;
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  // NEWLINE*
  private static boolean applicationBlock_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "applicationBlock_1")) return false;
    while (true) {
      int c = current_position_(b);
      if (!consumeToken(b, NEWLINE)) break;
      if (!empty_element_parsed_guard_(b, "applicationBlock_1", c)) break;
    }
    return true;
  }

  // NEWLINE*
  private static boolean applicationBlock_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "applicationBlock_3")) return false;
    while (true) {
      int c = current_position_(b);
      if (!consumeToken(b, NEWLINE)) break;
      if (!empty_element_parsed_guard_(b, "applicationBlock_3", c)) break;
    }
    return true;
  }

  // NEWLINE*
  private static boolean applicationBlock_5(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "applicationBlock_5")) return false;
    while (true) {
      int c = current_position_(b);
      if (!consumeToken(b, NEWLINE)) break;
      if (!empty_element_parsed_guard_(b, "applicationBlock_5", c)) break;
    }
    return true;
  }

  /* ********************************************************** */
  // configBlock (NEWLINE | configurationOption)*
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

  // (NEWLINE | configurationOption)*
  private static boolean applicationContent_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "applicationContent_1")) return false;
    while (true) {
      int c = current_position_(b);
      if (!applicationContent_1_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "applicationContent_1", c)) break;
    }
    return true;
  }

  // NEWLINE | configurationOption
  private static boolean applicationContent_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "applicationContent_1_0")) return false;
    boolean r;
    r = consumeToken(b, NEWLINE);
    if (!r) r = configurationOption(b, l + 1);
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
  // LBRACKET (arrayElement | NEWLINE)* RBRACKET
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

  // (arrayElement | NEWLINE)*
  private static boolean arrayLiteral_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "arrayLiteral_1")) return false;
    while (true) {
      int c = current_position_(b);
      if (!arrayLiteral_1_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "arrayLiteral_1", c)) break;
    }
    return true;
  }

  // arrayElement | NEWLINE
  private static boolean arrayLiteral_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "arrayLiteral_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = arrayElement(b, l + 1);
    if (!r) r = consumeToken(b, NEWLINE);
    exit_section_(b, m, null, r);
    return r;
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
  // CONFIG_KEYWORD NEWLINE* LBRACE (NEWLINE | optionNameValue)* RBRACE
  public static boolean configBlock(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "configBlock")) return false;
    if (!nextTokenIs(b, CONFIG_KEYWORD)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, CONFIG_BLOCK, null);
    r = consumeToken(b, CONFIG_KEYWORD);
    p = r; // pin = 1
    r = r && report_error_(b, configBlock_1(b, l + 1));
    r = p && report_error_(b, consumeToken(b, LBRACE)) && r;
    r = p && report_error_(b, configBlock_3(b, l + 1)) && r;
    r = p && consumeToken(b, RBRACE) && r;
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  // NEWLINE*
  private static boolean configBlock_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "configBlock_1")) return false;
    while (true) {
      int c = current_position_(b);
      if (!consumeToken(b, NEWLINE)) break;
      if (!empty_element_parsed_guard_(b, "configBlock_1", c)) break;
    }
    return true;
  }

  // (NEWLINE | optionNameValue)*
  private static boolean configBlock_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "configBlock_3")) return false;
    while (true) {
      int c = current_position_(b);
      if (!configBlock_3_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "configBlock_3", c)) break;
    }
    return true;
  }

  // NEWLINE | optionNameValue
  private static boolean configBlock_3_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "configBlock_3_0")) return false;
    boolean r;
    r = consumeToken(b, NEWLINE);
    if (!r) r = optionNameValue(b, l + 1);
    return r;
  }

  /* ********************************************************** */
  // configurationOptionName (wildcardLiteral | entitiesList) withOption? exceptEntities?
  public static boolean configurationOption(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "configurationOption")) return false;
    if (!nextTokenIs(b, IDENTIFIER)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, CONFIGURATION_OPTION, null);
    r = configurationOptionName(b, l + 1);
    p = r; // pin = 1
    r = r && report_error_(b, configurationOption_1(b, l + 1));
    r = p && report_error_(b, configurationOption_2(b, l + 1)) && r;
    r = p && configurationOption_3(b, l + 1) && r;
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  // wildcardLiteral | entitiesList
  private static boolean configurationOption_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "configurationOption_1")) return false;
    boolean r;
    r = wildcardLiteral(b, l + 1);
    if (!r) r = entitiesList(b, l + 1);
    return r;
  }

  // withOption?
  private static boolean configurationOption_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "configurationOption_2")) return false;
    withOption(b, l + 1);
    return true;
  }

  // exceptEntities?
  private static boolean configurationOption_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "configurationOption_3")) return false;
    exceptEntities(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // IDENTIFIER
  public static boolean configurationOptionName(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "configurationOptionName")) return false;
    if (!nextTokenIs(b, "<configuration option>", IDENTIFIER)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, CONFIGURATION_OPTION_NAME, "<configuration option>");
    r = consumeToken(b, IDENTIFIER);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // IDENTIFIER ASSIGN value
  public static boolean constantOption(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "constantOption")) return false;
    if (!nextTokenIs(b, IDENTIFIER)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, CONSTANT_OPTION, null);
    r = consumeTokens(b, 2, IDENTIFIER, ASSIGN);
    p = r; // pin = 2
    r = r && value(b, l + 1);
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  /* ********************************************************** */
  // DEPLOYMENT_KEYWORD NEWLINE* LBRACE (optionNameValue | NEWLINE)* RBRACE
  public static boolean deploymentBlock(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "deploymentBlock")) return false;
    if (!nextTokenIs(b, DEPLOYMENT_KEYWORD)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, DEPLOYMENT_BLOCK, null);
    r = consumeToken(b, DEPLOYMENT_KEYWORD);
    p = r; // pin = 1
    r = r && report_error_(b, deploymentBlock_1(b, l + 1));
    r = p && report_error_(b, consumeToken(b, LBRACE)) && r;
    r = p && report_error_(b, deploymentBlock_3(b, l + 1)) && r;
    r = p && consumeToken(b, RBRACE) && r;
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  // NEWLINE*
  private static boolean deploymentBlock_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "deploymentBlock_1")) return false;
    while (true) {
      int c = current_position_(b);
      if (!consumeToken(b, NEWLINE)) break;
      if (!empty_element_parsed_guard_(b, "deploymentBlock_1", c)) break;
    }
    return true;
  }

  // (optionNameValue | NEWLINE)*
  private static boolean deploymentBlock_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "deploymentBlock_3")) return false;
    while (true) {
      int c = current_position_(b);
      if (!deploymentBlock_3_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "deploymentBlock_3", c)) break;
    }
    return true;
  }

  // optionNameValue | NEWLINE
  private static boolean deploymentBlock_3_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "deploymentBlock_3_0")) return false;
    boolean r;
    r = optionNameValue(b, l + 1);
    if (!r) r = consumeToken(b, NEWLINE);
    return r;
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
  // ENTITY_KEYWORD NEWLINE* id NEWLINE* (LPARENTH NEWLINE* entityTableName NEWLINE* RPARENTH)? entityFields?
  public static boolean entityBlock(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "entityBlock")) return false;
    if (!nextTokenIs(b, ENTITY_KEYWORD)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, ENTITY_BLOCK, null);
    r = consumeToken(b, ENTITY_KEYWORD);
    p = r; // pin = 1
    r = r && report_error_(b, entityBlock_1(b, l + 1));
    r = p && report_error_(b, id(b, l + 1)) && r;
    r = p && report_error_(b, entityBlock_3(b, l + 1)) && r;
    r = p && report_error_(b, entityBlock_4(b, l + 1)) && r;
    r = p && entityBlock_5(b, l + 1) && r;
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  // NEWLINE*
  private static boolean entityBlock_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "entityBlock_1")) return false;
    while (true) {
      int c = current_position_(b);
      if (!consumeToken(b, NEWLINE)) break;
      if (!empty_element_parsed_guard_(b, "entityBlock_1", c)) break;
    }
    return true;
  }

  // NEWLINE*
  private static boolean entityBlock_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "entityBlock_3")) return false;
    while (true) {
      int c = current_position_(b);
      if (!consumeToken(b, NEWLINE)) break;
      if (!empty_element_parsed_guard_(b, "entityBlock_3", c)) break;
    }
    return true;
  }

  // (LPARENTH NEWLINE* entityTableName NEWLINE* RPARENTH)?
  private static boolean entityBlock_4(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "entityBlock_4")) return false;
    entityBlock_4_0(b, l + 1);
    return true;
  }

  // LPARENTH NEWLINE* entityTableName NEWLINE* RPARENTH
  private static boolean entityBlock_4_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "entityBlock_4_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, LPARENTH);
    r = r && entityBlock_4_0_1(b, l + 1);
    r = r && entityTableName(b, l + 1);
    r = r && entityBlock_4_0_3(b, l + 1);
    r = r && consumeToken(b, RPARENTH);
    exit_section_(b, m, null, r);
    return r;
  }

  // NEWLINE*
  private static boolean entityBlock_4_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "entityBlock_4_0_1")) return false;
    while (true) {
      int c = current_position_(b);
      if (!consumeToken(b, NEWLINE)) break;
      if (!empty_element_parsed_guard_(b, "entityBlock_4_0_1", c)) break;
    }
    return true;
  }

  // NEWLINE*
  private static boolean entityBlock_4_0_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "entityBlock_4_0_3")) return false;
    while (true) {
      int c = current_position_(b);
      if (!consumeToken(b, NEWLINE)) break;
      if (!empty_element_parsed_guard_(b, "entityBlock_4_0_3", c)) break;
    }
    return true;
  }

  // entityFields?
  private static boolean entityBlock_5(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "entityBlock_5")) return false;
    entityFields(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // fieldName fieldType fieldConstraint* (COMMA | NEWLINE)
  public static boolean entityFieldMapping(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "entityFieldMapping")) return false;
    if (!nextTokenIs(b, IDENTIFIER)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, ENTITY_FIELD_MAPPING, null);
    r = fieldName(b, l + 1);
    p = r; // pin = 1
    r = r && report_error_(b, fieldType(b, l + 1));
    r = p && report_error_(b, entityFieldMapping_2(b, l + 1)) && r;
    r = p && entityFieldMapping_3(b, l + 1) && r;
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  // fieldConstraint*
  private static boolean entityFieldMapping_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "entityFieldMapping_2")) return false;
    while (true) {
      int c = current_position_(b);
      if (!fieldConstraint(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "entityFieldMapping_2", c)) break;
    }
    return true;
  }

  // COMMA | NEWLINE
  private static boolean entityFieldMapping_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "entityFieldMapping_3")) return false;
    boolean r;
    r = consumeToken(b, COMMA);
    if (!r) r = consumeToken(b, NEWLINE);
    return r;
  }

  /* ********************************************************** */
  // LBRACE (entityFieldMapping | NEWLINE)* RBRACE
  static boolean entityFields(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "entityFields")) return false;
    if (!nextTokenIs(b, LBRACE)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_);
    r = consumeToken(b, LBRACE);
    p = r; // pin = 1
    r = r && report_error_(b, entityFields_1(b, l + 1));
    r = p && consumeToken(b, RBRACE) && r;
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  // (entityFieldMapping | NEWLINE)*
  private static boolean entityFields_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "entityFields_1")) return false;
    while (true) {
      int c = current_position_(b);
      if (!entityFields_1_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "entityFields_1", c)) break;
    }
    return true;
  }

  // entityFieldMapping | NEWLINE
  private static boolean entityFields_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "entityFields_1_0")) return false;
    boolean r;
    r = entityFieldMapping(b, l + 1);
    if (!r) r = consumeToken(b, NEWLINE);
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
  // ENUM_KEYWORD NEWLINE* id NEWLINE* LBRACE (enumValue | NEWLINE)* RBRACE
  public static boolean enumBlock(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "enumBlock")) return false;
    if (!nextTokenIs(b, ENUM_KEYWORD)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, ENUM_BLOCK, null);
    r = consumeToken(b, ENUM_KEYWORD);
    p = r; // pin = 1
    r = r && report_error_(b, enumBlock_1(b, l + 1));
    r = p && report_error_(b, id(b, l + 1)) && r;
    r = p && report_error_(b, enumBlock_3(b, l + 1)) && r;
    r = p && report_error_(b, consumeToken(b, LBRACE)) && r;
    r = p && report_error_(b, enumBlock_5(b, l + 1)) && r;
    r = p && consumeToken(b, RBRACE) && r;
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  // NEWLINE*
  private static boolean enumBlock_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "enumBlock_1")) return false;
    while (true) {
      int c = current_position_(b);
      if (!consumeToken(b, NEWLINE)) break;
      if (!empty_element_parsed_guard_(b, "enumBlock_1", c)) break;
    }
    return true;
  }

  // NEWLINE*
  private static boolean enumBlock_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "enumBlock_3")) return false;
    while (true) {
      int c = current_position_(b);
      if (!consumeToken(b, NEWLINE)) break;
      if (!empty_element_parsed_guard_(b, "enumBlock_3", c)) break;
    }
    return true;
  }

  // (enumValue | NEWLINE)*
  private static boolean enumBlock_5(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "enumBlock_5")) return false;
    while (true) {
      int c = current_position_(b);
      if (!enumBlock_5_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "enumBlock_5", c)) break;
    }
    return true;
  }

  // enumValue | NEWLINE
  private static boolean enumBlock_5_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "enumBlock_5_0")) return false;
    boolean r;
    r = enumValue(b, l + 1);
    if (!r) r = consumeToken(b, NEWLINE);
    return r;
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
  // enumKey explicitEnumMapping? (COMMA | NEWLINE)
  public static boolean enumValue(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "enumValue")) return false;
    if (!nextTokenIs(b, IDENTIFIER)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, ENUM_VALUE, null);
    r = enumKey(b, l + 1);
    p = r; // pin = 1
    r = r && report_error_(b, enumValue_1(b, l + 1));
    r = p && enumValue_2(b, l + 1) && r;
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  // explicitEnumMapping?
  private static boolean enumValue_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "enumValue_1")) return false;
    explicitEnumMapping(b, l + 1);
    return true;
  }

  // COMMA | NEWLINE
  private static boolean enumValue_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "enumValue_2")) return false;
    boolean r;
    r = consumeToken(b, COMMA);
    if (!r) r = consumeToken(b, NEWLINE);
    return r;
  }

  /* ********************************************************** */
  // EXCEPT_KEYWORD entitiesList
  public static boolean exceptEntities(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "exceptEntities")) return false;
    if (!nextTokenIs(b, EXCEPT_KEYWORD)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, EXCEPT_ENTITIES, null);
    r = consumeToken(b, EXCEPT_KEYWORD);
    p = r; // pin = 1
    r = r && entitiesList(b, l + 1);
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  /* ********************************************************** */
  // LPARENTH (IDENTIFIER | stringLiteral) RPARENTH
  public static boolean explicitEnumMapping(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "explicitEnumMapping")) return false;
    if (!nextTokenIs(b, LPARENTH)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, EXPLICIT_ENUM_MAPPING, null);
    r = consumeToken(b, LPARENTH);
    p = r; // pin = 1
    r = r && report_error_(b, explicitEnumMapping_1(b, l + 1));
    r = p && consumeToken(b, RPARENTH) && r;
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  // IDENTIFIER | stringLiteral
  private static boolean explicitEnumMapping_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "explicitEnumMapping_1")) return false;
    boolean r;
    r = consumeToken(b, IDENTIFIER);
    if (!r) r = stringLiteral(b, l + 1);
    return r;
  }

  /* ********************************************************** */
  // IDENTIFIER fieldConstraintParameters?
  public static boolean fieldConstraint(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "fieldConstraint")) return false;
    if (!nextTokenIs(b, IDENTIFIER)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, IDENTIFIER);
    r = r && fieldConstraint_1(b, l + 1);
    exit_section_(b, m, FIELD_CONSTRAINT, r);
    return r;
  }

  // fieldConstraintParameters?
  private static boolean fieldConstraint_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "fieldConstraint_1")) return false;
    fieldConstraintParameters(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // value (COMMA|&RPARENTH)
  static boolean fieldConstraintParameterValue(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "fieldConstraintParameterValue")) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_);
    r = value(b, l + 1);
    p = r; // pin = 1
    r = r && fieldConstraintParameterValue_1(b, l + 1);
    exit_section_(b, l, m, r, p, JdlParser::notRParenthOrNextValue);
    return r || p;
  }

  // COMMA|&RPARENTH
  private static boolean fieldConstraintParameterValue_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "fieldConstraintParameterValue_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, COMMA);
    if (!r) r = fieldConstraintParameterValue_1_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // &RPARENTH
  private static boolean fieldConstraintParameterValue_1_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "fieldConstraintParameterValue_1_1")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _AND_);
    r = consumeToken(b, RPARENTH);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // LPARENTH fieldConstraintParameterValue* RPARENTH
  public static boolean fieldConstraintParameters(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "fieldConstraintParameters")) return false;
    if (!nextTokenIs(b, LPARENTH)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, FIELD_CONSTRAINT_PARAMETERS, null);
    r = consumeToken(b, LPARENTH);
    p = r; // pin = 1
    r = r && report_error_(b, fieldConstraintParameters_1(b, l + 1));
    r = p && consumeToken(b, RPARENTH) && r;
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  // fieldConstraintParameterValue*
  private static boolean fieldConstraintParameters_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "fieldConstraintParameters_1")) return false;
    while (true) {
      int c = current_position_(b);
      if (!fieldConstraintParameterValue(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "fieldConstraintParameters_1", c)) break;
    }
    return true;
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
  // !(RPARENTH|value)
  static boolean notRParenthOrNextValue(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "notRParenthOrNextValue")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NOT_);
    r = !notRParenthOrNextValue_0(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // RPARENTH|value
  private static boolean notRParenthOrNextValue_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "notRParenthOrNextValue_0")) return false;
    boolean r;
    r = consumeToken(b, RPARENTH);
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
  // optionName value? (COMMA | NEWLINE)
  public static boolean optionNameValue(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "optionNameValue")) return false;
    if (!nextTokenIs(b, "<option>", IDENTIFIER)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, OPTION_NAME_VALUE, "<option>");
    r = optionName(b, l + 1);
    p = r; // pin = 1
    r = r && report_error_(b, optionNameValue_1(b, l + 1));
    r = p && optionNameValue_2(b, l + 1) && r;
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  // value?
  private static boolean optionNameValue_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "optionNameValue_1")) return false;
    value(b, l + 1);
    return true;
  }

  // COMMA | NEWLINE
  private static boolean optionNameValue_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "optionNameValue_2")) return false;
    boolean r;
    r = consumeToken(b, COMMA);
    if (!r) r = consumeToken(b, NEWLINE);
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
  // RELATIONSHIP_KEYWORD relationshipType NEWLINE* LBRACE (NEWLINE | relationshipMapping)* RBRACE
  public static boolean relationshipBlock(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "relationshipBlock")) return false;
    if (!nextTokenIs(b, RELATIONSHIP_KEYWORD)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, RELATIONSHIP_BLOCK, null);
    r = consumeToken(b, RELATIONSHIP_KEYWORD);
    p = r; // pin = 1
    r = r && report_error_(b, relationshipType(b, l + 1));
    r = p && report_error_(b, relationshipBlock_2(b, l + 1)) && r;
    r = p && report_error_(b, consumeToken(b, LBRACE)) && r;
    r = p && report_error_(b, relationshipBlock_4(b, l + 1)) && r;
    r = p && consumeToken(b, RBRACE) && r;
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  // NEWLINE*
  private static boolean relationshipBlock_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "relationshipBlock_2")) return false;
    while (true) {
      int c = current_position_(b);
      if (!consumeToken(b, NEWLINE)) break;
      if (!empty_element_parsed_guard_(b, "relationshipBlock_2", c)) break;
    }
    return true;
  }

  // (NEWLINE | relationshipMapping)*
  private static boolean relationshipBlock_4(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "relationshipBlock_4")) return false;
    while (true) {
      int c = current_position_(b);
      if (!relationshipBlock_4_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "relationshipBlock_4", c)) break;
    }
    return true;
  }

  // NEWLINE | relationshipMapping
  private static boolean relationshipBlock_4_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "relationshipBlock_4_0")) return false;
    boolean r;
    r = consumeToken(b, NEWLINE);
    if (!r) r = relationshipMapping(b, l + 1);
    return r;
  }

  /* ********************************************************** */
  // LBRACE fieldName (LPARENTH fieldName RPARENTH)? fieldConstraint? RBRACE
  public static boolean relationshipDetails(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "relationshipDetails")) return false;
    if (!nextTokenIs(b, LBRACE)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, LBRACE);
    r = r && fieldName(b, l + 1);
    r = r && relationshipDetails_2(b, l + 1);
    r = r && relationshipDetails_3(b, l + 1);
    r = r && consumeToken(b, RBRACE);
    exit_section_(b, m, RELATIONSHIP_DETAILS, r);
    return r;
  }

  // (LPARENTH fieldName RPARENTH)?
  private static boolean relationshipDetails_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "relationshipDetails_2")) return false;
    relationshipDetails_2_0(b, l + 1);
    return true;
  }

  // LPARENTH fieldName RPARENTH
  private static boolean relationshipDetails_2_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "relationshipDetails_2_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, LPARENTH);
    r = r && fieldName(b, l + 1);
    r = r && consumeToken(b, RPARENTH);
    exit_section_(b, m, null, r);
    return r;
  }

  // fieldConstraint?
  private static boolean relationshipDetails_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "relationshipDetails_3")) return false;
    fieldConstraint(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // entityId relationshipDetails?
  public static boolean relationshipEntity(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "relationshipEntity")) return false;
    if (!nextTokenIs(b, IDENTIFIER)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = entityId(b, l + 1);
    r = r && relationshipEntity_1(b, l + 1);
    exit_section_(b, m, RELATIONSHIP_ENTITY, r);
    return r;
  }

  // relationshipDetails?
  private static boolean relationshipEntity_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "relationshipEntity_1")) return false;
    relationshipDetails(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // relationshipEntity TO_KEYWORD relationshipEntity withOption? (COMMA | NEWLINE)
  public static boolean relationshipMapping(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "relationshipMapping")) return false;
    if (!nextTokenIs(b, IDENTIFIER)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = relationshipEntity(b, l + 1);
    r = r && consumeToken(b, TO_KEYWORD);
    r = r && relationshipEntity(b, l + 1);
    r = r && relationshipMapping_3(b, l + 1);
    r = r && relationshipMapping_4(b, l + 1);
    exit_section_(b, m, RELATIONSHIP_MAPPING, r);
    return r;
  }

  // withOption?
  private static boolean relationshipMapping_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "relationshipMapping_3")) return false;
    withOption(b, l + 1);
    return true;
  }

  // COMMA | NEWLINE
  private static boolean relationshipMapping_4(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "relationshipMapping_4")) return false;
    boolean r;
    r = consumeToken(b, COMMA);
    if (!r) r = consumeToken(b, NEWLINE);
    return r;
  }

  /* ********************************************************** */
  // IDENTIFIER
  public static boolean relationshipType(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "relationshipType")) return false;
    if (!nextTokenIs(b, IDENTIFIER)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, IDENTIFIER);
    exit_section_(b, m, RELATIONSHIP_TYPE, r);
    return r;
  }

  /* ********************************************************** */
  // (NEWLINE | applicationBlock | entityBlock | enumBlock | relationshipBlock
  //           | deploymentBlock | constantOption | configurationOption)*
  static boolean root(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "root")) return false;
    while (true) {
      int c = current_position_(b);
      if (!root_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "root", c)) break;
    }
    return true;
  }

  // NEWLINE | applicationBlock | entityBlock | enumBlock | relationshipBlock
  //           | deploymentBlock | constantOption | configurationOption
  private static boolean root_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "root_0")) return false;
    boolean r;
    r = consumeToken(b, NEWLINE);
    if (!r) r = applicationBlock(b, l + 1);
    if (!r) r = entityBlock(b, l + 1);
    if (!r) r = enumBlock(b, l + 1);
    if (!r) r = relationshipBlock(b, l + 1);
    if (!r) r = deploymentBlock(b, l + 1);
    if (!r) r = constantOption(b, l + 1);
    if (!r) r = configurationOption(b, l + 1);
    return r;
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
  // id | booleanLiteral | stringLiteral | numberLiteral | arrayLiteral | regexLiteral
  public static boolean value(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "value")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, VALUE, "<value>");
    r = id(b, l + 1);
    if (!r) r = booleanLiteral(b, l + 1);
    if (!r) r = stringLiteral(b, l + 1);
    if (!r) r = numberLiteral(b, l + 1);
    if (!r) r = arrayLiteral(b, l + 1);
    if (!r) r = regexLiteral(b, l + 1);
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
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_);
    r = consumeToken(b, WITH_KEYWORD);
    p = r; // pin = 1
    r = r && withOptionValue(b, l + 1);
    exit_section_(b, l, m, r, p, null);
    return r || p;
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
