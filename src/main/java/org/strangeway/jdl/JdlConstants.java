package org.strangeway.jdl;

import java.util.List;

public final class JdlConstants {
  private JdlConstants() {
  }

  public static final List<String> TOP_LEVEL_KEYWORDS = List.of(
      "application",
      "entity",
      "enum",
      "relationship",
      "dto",
      "entities",
      "service",
      "microservice",
      "paginate"
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
}
