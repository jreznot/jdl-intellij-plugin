package org.strangeway.jdl.model;

public enum JdlIngressType implements JdlModelEnum {
  NGINX("nginx"),
  GKE("gke");

  private final String id;

  JdlIngressType(String id) {
    this.id = id;
  }

  @Override
  public String getId() {
    return id;
  }
}

