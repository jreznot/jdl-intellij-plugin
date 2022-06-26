package org.strangeway.jdl.model;

import java.util.List;

public final class JdlOptionModel {
  public static final JdlOptionModel INSTANCE = new JdlOptionModel();

  private final List<JdlOptionMapping> applicationConfigOptions;

  private JdlOptionModel() {
    applicationConfigOptions = List.of(
        /* todo fill model */
    );
  }

  public List<JdlOptionMapping> getApplicationConfigOptions() {
    return applicationConfigOptions;
  }
}
