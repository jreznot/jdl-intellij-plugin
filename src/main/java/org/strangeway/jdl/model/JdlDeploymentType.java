package org.strangeway.jdl.model;

public enum JdlDeploymentType implements JdlModelEnum {
  DOCKER_COMPOSE("docker-compose"),
  OPENSHIFT("openshift"),
  KUBERNETES("kubernetes");

  private final String id;

  JdlDeploymentType(String id) {
    this.id = id;
  }

  @Override
  public String getId() {
    return id;
  }
}
