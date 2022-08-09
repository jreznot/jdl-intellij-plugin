package org.strangeway.jdl.model;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public final class JdlOptionModel {
  public static final JdlOptionModel INSTANCE = new JdlOptionModel();

  private final Map<String, JdlOptionMapping> applicationConfigOptions;

  private JdlOptionModel() {
    List<JdlOptionMapping> applicationConfigOptions = List.of(
        new JdlOptionMapping("applicationType", new JdlEnumType<>("applicationTypes", JdlApplicationType.class))
    );

    this.applicationConfigOptions = applicationConfigOptions.stream()
        .collect(Collectors.toMap(JdlOptionMapping::getName, Function.identity()));
  }

  public Map<String, JdlOptionMapping> getApplicationConfigOptions() {
    return applicationConfigOptions;
  }
}
