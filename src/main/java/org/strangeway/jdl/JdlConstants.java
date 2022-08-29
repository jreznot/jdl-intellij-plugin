package org.strangeway.jdl;

import java.util.List;
import java.util.Map;

public final class JdlConstants {
  private JdlConstants() {
  }

  public static final String APPLICATION_BASE_NAME = "baseName";
  public static final String APPLICATION_UNNAMED = "<unnamed>";

  public static final String CONFIG_BLOCK_NAME = "config";
  public static final String DEPLOYMENT_BLOCK_NAME = "deployment";

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
      "dto"
  );

  public static final List<String> CONFIGURATION_OPTION_NESTED_KEYWORDS = List.of(
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

  public static final Map<String, String> FIELD_TYPES = Map.ofEntries(
      Map.entry("String", "java.lang.String"),
      Map.entry("Integer", "java.lang.Integer"),
      Map.entry("Long", "java.lang.Long"),
      Map.entry("Boolean", "java.lang.Boolean"),
      Map.entry("LocalDate", "java.time.LocalDate"),
      Map.entry("ZonedDateTime", "java.time.ZonedDateTime"),
      Map.entry("BigDecimal", "java.math.BigDecimal"),
      Map.entry("Float", "java.lang.Float"),
      Map.entry("Double", "java.lang.Double"),
      Map.entry("Instant", "java.time.Instant"),
      Map.entry("Duration", "java.time.Duration"),
      Map.entry("UUID", "java.util.UUID"),
      Map.entry("Blob", "java.sql.Blob"),
      Map.entry("AnyBlob", "java.sql.Blob"),
      Map.entry("ImageBlob", "java.sql.Blob"),
      Map.entry("TextBlob", "java.sql.Clob")
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
