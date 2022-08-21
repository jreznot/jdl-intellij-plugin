package org.strangeway.jdl;

import java.util.List;

public final class JdlConstants {
  private JdlConstants() {
  }

  public static final String APPLICATION_BASE_NAME = "baseName";
  public static final String APPLICATION_UNNAMED = "<unnamed application>";

  public static final String TRUE = "true";
  public static final String FALSE = "false";

  public static final List<String> TOP_LEVEL_KEYWORDS = List.of(
      "application",
      "entity",
      "enum",
      "relationship",
      "dto",
      "entities",
      "service",
      "microservice",
      "paginate",
      "search"
  );

  public static final List<String> APPLICATION_NESTED_KEYWORDS = List.of(
      "config",
      "entities",
      "paginate",
      "dto",
      "with",
      "except"
  );

  public static final List<String> RELATIONSHIP_NESTED_KEYWORDS = List.of(
      "to"
  );

  public static final List<String> RELATIONSHIP_TYPES = List.of(
      "OneToMany",
      "ManyToOne",
      "OneToOne",
      "ManyToMany"
  );

  public static final List<String> FIELD_TYPES = List.of(
      "String",
      "Integer",
      "Long",
      "Boolean",
      "LocalDate",
      "ZonedDateTime",
      "BigDecimal",
      "Float",
      "Double",
      "Instant",
      "Duration",
      "UUID",
      "Blob",
      "AnyBlob",
      "ImageBlob",
      "TextBlob"
  );

  public static final List<String> FIELD_VALIDATIONS = List.of(
      "required",
      "unique",
      "minlength()",
      "maxlength()",
      "pattern()",
      "min()",
      "max()",
      "minbytes()",
      "maxbytes()"
  );
}
