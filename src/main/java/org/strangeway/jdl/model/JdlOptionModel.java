package org.strangeway.jdl.model;

import java.util.*;

public final class JdlOptionModel {
  public static final JdlOptionModel INSTANCE = new JdlOptionModel();

  public static final String BASE_NAME_ATTRIBUTE_NAME = "baseName";

  private final Map<String, JdlOptionMapping> applicationConfigOptions;

  private JdlOptionModel() {
    List<JdlOptionMapping> applicationConfigOptions = new ArrayList<>(List.of(
        new JdlEnumMapping("applicationType", JdlApplicationType.class, JdlApplicationType.MONOLITH),
        new JdlEnumMapping("authenticationType", JdlAuthenticationType.class, JdlAuthenticationType.JWT),
        new JdlOptionMapping("baseName", JdlPrimitiveType.STRING_TYPE, "jhipster"),
        new JdlEnumMapping("buildTool", JdlBuildTool.class, JdlBuildTool.MAVEN),
        new JdlEnumMapping("cacheProvider", JdlCacheProvider.class),
        new JdlOptionMapping("enableHibernateCache", JdlPrimitiveType.BOOLEAN_TYPE, "true"),
        new JdlEnumMapping("clientFramework", JdlClientFramework.class, JdlClientFramework.ANGULARX)
    ));

    applicationConfigOptions.sort(Comparator.comparing(JdlOptionMapping::getName));

    Map<String, JdlOptionMapping> orderedOptions = new LinkedHashMap<>();
    for (JdlOptionMapping option : applicationConfigOptions) {
      orderedOptions.put(option.getName(), option);
    }

    this.applicationConfigOptions = orderedOptions;
  }

  public Map<String, JdlOptionMapping> getApplicationConfigOptions() {
    return applicationConfigOptions;
  }

  public Map<String, JdlOptionMapping> getDeploymentOptions() {
    return Map.of();
  }
}
