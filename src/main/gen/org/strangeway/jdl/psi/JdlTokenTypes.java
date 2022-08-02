// This is a generated file. Not intended for manual editing.
package org.strangeway.jdl.psi;

import com.intellij.psi.tree.IElementType;
import com.intellij.psi.PsiElement;
import com.intellij.lang.ASTNode;
import org.strangeway.jdl.psi.impl.*;

public interface JdlTokenTypes {

  IElementType APPLICATION_BLOCK = new JdlElementType("APPLICATION_BLOCK");
  IElementType ARRAY_LITERAL = new JdlElementType("ARRAY_LITERAL");
  IElementType BOOLEAN_LITERAL = new JdlElementType("BOOLEAN_LITERAL");
  IElementType CONFIG_BLOCK = new JdlElementType("CONFIG_BLOCK");
  IElementType DEPLOYMENT_BLOCK = new JdlElementType("DEPLOYMENT_BLOCK");
  IElementType DTO_OPTION = new JdlElementType("DTO_OPTION");
  IElementType ENTITIES_LIST = new JdlElementType("ENTITIES_LIST");
  IElementType ENTITIES_OPTION = new JdlElementType("ENTITIES_OPTION");
  IElementType ENTITY_BLOCK = new JdlElementType("ENTITY_BLOCK");
  IElementType ENTITY_FIELD_MAPPING = new JdlElementType("ENTITY_FIELD_MAPPING");
  IElementType ENTITY_ID = new JdlElementType("ENTITY_ID");
  IElementType ENTITY_TABLE_NAME = new JdlElementType("ENTITY_TABLE_NAME");
  IElementType ENUM_BLOCK = new JdlElementType("ENUM_BLOCK");
  IElementType ENUM_KEY = new JdlElementType("ENUM_KEY");
  IElementType ENUM_VALUE = new JdlElementType("ENUM_VALUE");
  IElementType EXCEPT_ENTITIES = new JdlElementType("EXCEPT_ENTITIES");
  IElementType EXPLICIT_ENUM_MAPPING = new JdlElementType("EXPLICIT_ENUM_MAPPING");
  IElementType FIELD_CONSTRAINT = new JdlElementType("FIELD_CONSTRAINT");
  IElementType FIELD_CONSTRAINT_PARAMETERS = new JdlElementType("FIELD_CONSTRAINT_PARAMETERS");
  IElementType FIELD_NAME = new JdlElementType("FIELD_NAME");
  IElementType FIELD_TYPE = new JdlElementType("FIELD_TYPE");
  IElementType ID = new JdlElementType("ID");
  IElementType NUMBER_LITERAL = new JdlElementType("NUMBER_LITERAL");
  IElementType OPTION_NAME = new JdlElementType("OPTION_NAME");
  IElementType OPTION_NAME_VALUE = new JdlElementType("OPTION_NAME_VALUE");
  IElementType PAGINATE_OPTION = new JdlElementType("PAGINATE_OPTION");
  IElementType PATTERN_CALL = new JdlElementType("PATTERN_CALL");
  IElementType REGEX_LITERAL = new JdlElementType("REGEX_LITERAL");
  IElementType RELATIONSHIP_BLOCK = new JdlElementType("RELATIONSHIP_BLOCK");
  IElementType RELATIONSHIP_MAPPING = new JdlElementType("RELATIONSHIP_MAPPING");
  IElementType RELATIONSHIP_TYPE = new JdlElementType("RELATIONSHIP_TYPE");
  IElementType SERVICE_OPTION = new JdlElementType("SERVICE_OPTION");
  IElementType STRING_LITERAL = new JdlElementType("STRING_LITERAL");
  IElementType VALUE = new JdlElementType("VALUE");
  IElementType WILDCARD_LITERAL = new JdlElementType("WILDCARD_LITERAL");
  IElementType WITH_OPTION_VALUE = new JdlElementType("WITH_OPTION_VALUE");

  IElementType APPLICATION_KEYWORD = new JdlTokenType("application");
  IElementType BLOCK_COMMENT = new JdlTokenType("BLOCK_COMMENT");
  IElementType COLON = new JdlTokenType(":");
  IElementType COMMA = new JdlTokenType(",");
  IElementType CONFIG_KEYWORD = new JdlTokenType("config");
  IElementType DEPLOYMENT_KEYWORD = new JdlTokenType("deployment");
  IElementType DOUBLE_NUMBER = new JdlTokenType("DOUBLE_NUMBER");
  IElementType DOUBLE_QUOTED_STRING = new JdlTokenType("DOUBLE_QUOTED_STRING");
  IElementType DTO_KEYWORD = new JdlTokenType("dto");
  IElementType ENTITIES_KEYWORD = new JdlTokenType("entities");
  IElementType ENTITY_KEYWORD = new JdlTokenType("entity");
  IElementType ENUM_KEYWORD = new JdlTokenType("enum");
  IElementType EXCEPT_KEYWORD = new JdlTokenType("except");
  IElementType FALSE = new JdlTokenType("false");
  IElementType IDENTIFIER = new JdlTokenType("IDENTIFIER");
  IElementType INTEGER_NUMBER = new JdlTokenType("INTEGER_NUMBER");
  IElementType LBRACE = new JdlTokenType("{");
  IElementType LBRACKET = new JdlTokenType("[");
  IElementType LINE_COMMENT = new JdlTokenType("LINE_COMMENT");
  IElementType LPARENTH = new JdlTokenType("(");
  IElementType NEWLINE = new JdlTokenType("NEWLINE");
  IElementType PAGINATE_KEYWORD = new JdlTokenType("paginate");
  IElementType RBRACE = new JdlTokenType("}");
  IElementType RBRACKET = new JdlTokenType("]");
  IElementType REGEX_STRING = new JdlTokenType("REGEX_STRING");
  IElementType RELATIONSHIP_KEYWORD = new JdlTokenType("relationship");
  IElementType RPARENTH = new JdlTokenType(")");
  IElementType SERVICE_KEYWORD = new JdlTokenType("service");
  IElementType STRUDEL = new JdlTokenType("@");
  IElementType TO_KEYWORD = new JdlTokenType("to");
  IElementType TRUE = new JdlTokenType("true");
  IElementType WILDCARD = new JdlTokenType("*");
  IElementType WITH_KEYWORD = new JdlTokenType("with");

  class Factory {
    public static PsiElement createElement(ASTNode node) {
      IElementType type = node.getElementType();
      if (type == APPLICATION_BLOCK) {
        return new JdlApplicationBlockImpl(node);
      }
      else if (type == ARRAY_LITERAL) {
        return new JdlArrayLiteralImpl(node);
      }
      else if (type == BOOLEAN_LITERAL) {
        return new JdlBooleanLiteralImpl(node);
      }
      else if (type == CONFIG_BLOCK) {
        return new JdlConfigBlockImpl(node);
      }
      else if (type == DEPLOYMENT_BLOCK) {
        return new JdlDeploymentBlockImpl(node);
      }
      else if (type == DTO_OPTION) {
        return new JdlDtoOptionImpl(node);
      }
      else if (type == ENTITIES_LIST) {
        return new JdlEntitiesListImpl(node);
      }
      else if (type == ENTITIES_OPTION) {
        return new JdlEntitiesOptionImpl(node);
      }
      else if (type == ENTITY_BLOCK) {
        return new JdlEntityBlockImpl(node);
      }
      else if (type == ENTITY_FIELD_MAPPING) {
        return new JdlEntityFieldMappingImpl(node);
      }
      else if (type == ENTITY_ID) {
        return new JdlEntityIdImpl(node);
      }
      else if (type == ENTITY_TABLE_NAME) {
        return new JdlEntityTableNameImpl(node);
      }
      else if (type == ENUM_BLOCK) {
        return new JdlEnumBlockImpl(node);
      }
      else if (type == ENUM_KEY) {
        return new JdlEnumKeyImpl(node);
      }
      else if (type == ENUM_VALUE) {
        return new JdlEnumValueImpl(node);
      }
      else if (type == EXCEPT_ENTITIES) {
        return new JdlExceptEntitiesImpl(node);
      }
      else if (type == EXPLICIT_ENUM_MAPPING) {
        return new JdlExplicitEnumMappingImpl(node);
      }
      else if (type == FIELD_CONSTRAINT) {
        return new JdlFieldConstraintImpl(node);
      }
      else if (type == FIELD_CONSTRAINT_PARAMETERS) {
        return new JdlFieldConstraintParametersImpl(node);
      }
      else if (type == FIELD_NAME) {
        return new JdlFieldNameImpl(node);
      }
      else if (type == FIELD_TYPE) {
        return new JdlFieldTypeImpl(node);
      }
      else if (type == ID) {
        return new JdlIdImpl(node);
      }
      else if (type == NUMBER_LITERAL) {
        return new JdlNumberLiteralImpl(node);
      }
      else if (type == OPTION_NAME) {
        return new JdlOptionNameImpl(node);
      }
      else if (type == OPTION_NAME_VALUE) {
        return new JdlOptionNameValueImpl(node);
      }
      else if (type == PAGINATE_OPTION) {
        return new JdlPaginateOptionImpl(node);
      }
      else if (type == PATTERN_CALL) {
        return new JdlPatternCallImpl(node);
      }
      else if (type == REGEX_LITERAL) {
        return new JdlRegexLiteralImpl(node);
      }
      else if (type == RELATIONSHIP_BLOCK) {
        return new JdlRelationshipBlockImpl(node);
      }
      else if (type == RELATIONSHIP_MAPPING) {
        return new JdlRelationshipMappingImpl(node);
      }
      else if (type == RELATIONSHIP_TYPE) {
        return new JdlRelationshipTypeImpl(node);
      }
      else if (type == SERVICE_OPTION) {
        return new JdlServiceOptionImpl(node);
      }
      else if (type == STRING_LITERAL) {
        return new JdlStringLiteralImpl(node);
      }
      else if (type == VALUE) {
        return new JdlValueImpl(node);
      }
      else if (type == WILDCARD_LITERAL) {
        return new JdlWildcardLiteralImpl(node);
      }
      else if (type == WITH_OPTION_VALUE) {
        return new JdlWithOptionValueImpl(node);
      }
      throw new AssertionError("Unknown element type: " + type);
    }
  }
}
