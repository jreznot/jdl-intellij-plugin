package org.strangeway.jdl.model;

public enum JdlKubernetesServiceType implements JdlEnum {
  LOAD_BALANCER("LoadBalancer"),
  NODE_PORT("NodePort"),
  INGRESS("Ingress");

  private final String id;

  JdlKubernetesServiceType(String id) {
    this.id = id;
  }

  @Override
  public String getId() {
    return id;
  }
}
