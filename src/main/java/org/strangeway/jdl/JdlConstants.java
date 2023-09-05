/*
 * Copyright © 2023 Yuriy Artamonov
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software
 * and associated documentation files (the "Software"), to deal in the Software without
 * restriction, including without limitation the rights to use, copy, modify, merge, publish,
 * distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the
 * Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies
 * or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING
 * BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM,
 * DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING
 * FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

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
      "deployment",
      "entities",
      "service",
      "microservice",
      "paginate",
      "search",
      "use"
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

  public static final List<String> RELATIONSHIP_OPTIONS = List.of(
      "Id",
      "OnDelete",
      "OnUpdate"
  );

  public static final List<String> RELATIONSHIP_OPTION_VALUES = List.of(
      "NO ACTION",
      "RESTRICT",
      "CASCADE",
      "SET NULL",
      "SET DEFAULT"
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
      Map.entry("TextBlob", "java.sql.Clob"),
      Map.entry("Date", "java.util.Date")
  );

  public static final String REQUIRED_FIELD_CONSTRAINT = "required";

  public static final List<String> FIELD_VALIDATIONS = List.of(
      REQUIRED_FIELD_CONSTRAINT,
      "unique",
      "minlength()",
      "maxlength()",
      "pattern()",
      "min()",
      "max()",
      "minbytes()",
      "maxbytes()"
  );

  public static final String USER_ENTITY_NAME = "User";

  public static final List<String> PREDEFINED_ENTITIES = List.of(
      USER_ENTITY_NAME,
      "Authority"
  );
}
