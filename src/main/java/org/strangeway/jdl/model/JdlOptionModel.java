package org.strangeway.jdl.model;

import java.util.*;

public final class JdlOptionModel {
  public static final JdlOptionModel INSTANCE = new JdlOptionModel();

  public static final String BASE_NAME_ATTRIBUTE_NAME = "baseName";

  private final Map<String, JdlOptionMapping> applicationConfigOptions;
  private final Map<String, JdlOptionMapping> deploymentOptions;

  private JdlOptionModel() {
    List<JdlOptionMapping> applicationConfigOptions = new ArrayList<>(List.of(
        new JdlEnumMapping("applicationType", JdlApplicationType.class, JdlApplicationType.MONOLITH),
        new JdlEnumMapping("authenticationType", JdlAuthenticationType.class, JdlAuthenticationType.JWT),
        new JdlOptionMapping("baseName", JdlPrimitiveType.STRING_TYPE, "jhipster"),
        new JdlOptionMapping("blueprint", JdlPrimitiveType.STRING_TYPE),
        new JdlOptionMapping("blueprints", JdlPrimitiveType.STRING_ARRAY_TYPE),
        new JdlEnumMapping("buildTool", JdlBuildTool.class, JdlBuildTool.MAVEN),
        new JdlEnumMapping("cacheProvider", JdlCacheProvider.class),
        new JdlOptionMapping("enableHibernateCache", JdlPrimitiveType.BOOLEAN_TYPE, "true"),
        new JdlEnumMapping("clientFramework", JdlClientFramework.class, JdlClientFramework.ANGULARX),
        new JdlEnumMapping("clientPackageManager", JdlClientPackageManager.class, JdlClientPackageManager.NPM),
        new JdlOptionMapping("clientTheme", JdlPrimitiveType.STRING_TYPE),
        new JdlOptionMapping("clientThemeVariant", JdlPrimitiveType.STRING_TYPE),
        new JdlEnumMapping("databaseType", JdlDatabaseType.class, JdlDatabaseType.SQL),
        new JdlEnumMapping("devDatabaseType", JdlDevDatabaseType.class, JdlDevDatabaseType.H2DISK),
        new JdlOptionMapping("dtoSuffix", JdlPrimitiveType.STRING_TYPE, "DTO"),
        new JdlOptionMapping("enableHibernateCache", JdlPrimitiveType.BOOLEAN_TYPE, "true"),
        new JdlOptionMapping("enableSwaggerCodegen", JdlPrimitiveType.BOOLEAN_TYPE, "false"),
        new JdlOptionMapping("enableTranslation", JdlPrimitiveType.BOOLEAN_TYPE, "true"),
        new JdlOptionMapping("entitySuffix", JdlPrimitiveType.STRING_TYPE),
        new JdlOptionMapping("jhiPrefix", JdlPrimitiveType.STRING_TYPE, "jhi"),
        new JdlEnumListMapping("languages", JdlLanguage.class),
        new JdlEnumMapping("messageBroker", JdlMessageBroker.class, JdlMessageBroker.FALSE),
        new JdlEnumMapping("nativeLanguage", JdlLanguage.class, JdlLanguage.ENGLISH),
        new JdlOptionMapping("packageName", JdlPrimitiveType.STRING_TYPE),
        new JdlEnumMapping("prodDatabaseType", JdlProdDatabaseType.class, JdlProdDatabaseType.MSSQL),
        new JdlOptionMapping("reactive", JdlPrimitiveType.BOOLEAN_TYPE),
        new JdlEnumMapping("searchEngine", JdlSearchEngine.class, JdlSearchEngine.FALSE),
        new JdlOptionMapping("serverPort", JdlPrimitiveType.INTEGER_TYPE, "8080, 8081 or 9999"),
        new JdlEnumMapping("serviceDiscoveryType", JdlServiceDiscoveryType.class, JdlServiceDiscoveryType.FALSE),
        new JdlOptionMapping("skipClient", JdlPrimitiveType.BOOLEAN_TYPE, "false"),
        new JdlOptionMapping("skipServer", JdlPrimitiveType.BOOLEAN_TYPE, "false"),
        new JdlOptionMapping("skipUserManagement", JdlPrimitiveType.BOOLEAN_TYPE, "false"),
        new JdlEnumListMapping("testFrameworks", JdlTestFramework.class),
        new JdlEnumMapping("websocket", JdlWebsocket.class)
    ));

    applicationConfigOptions.sort(Comparator.comparing(JdlOptionMapping::getName));

    Map<String, JdlOptionMapping> orderedOptions = new LinkedHashMap<>();
    for (JdlOptionMapping option : applicationConfigOptions) {
      orderedOptions.put(option.getName(), option);
    }

    this.applicationConfigOptions = orderedOptions;
    this.deploymentOptions = Map.of();
  }

  public Map<String, JdlOptionMapping> getApplicationConfigOptions() {
    return applicationConfigOptions;
  }

  public Map<String, JdlOptionMapping> getDeploymentOptions() {
    return deploymentOptions;
  }
}
