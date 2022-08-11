package org.strangeway.jdl.model;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public final class JdlOptionModel {
  public static final JdlOptionModel INSTANCE = new JdlOptionModel();

  public static final String BASE_NAME_ATTRIBUTE_NAME = "baseName";

  private final Map<String, JdlOptionMapping> applicationConfigOptions;

  private JdlOptionModel() {
    List<JdlOptionMapping> applicationConfigOptions = List.of(
        new JdlEnumMapping("applicationType", JdlApplicationType.class),
        new JdlEnumMapping("buildTool", JdlBuildTool.class),
        new JdlEnumMapping("authenticationType", JdlAuthenticationType.class),
        new JdlEnumMapping("cacheProvider", JdlCacheProvider.class),
        new JdlEnumMapping("clientFramework", JdlClientFramework.class)
    );

    this.applicationConfigOptions = applicationConfigOptions.stream()
        .collect(Collectors.toMap(JdlOptionMapping::getName, Function.identity()));
  }

  public Map<String, JdlOptionMapping> getApplicationConfigOptions() {
    return applicationConfigOptions;
  }
}
