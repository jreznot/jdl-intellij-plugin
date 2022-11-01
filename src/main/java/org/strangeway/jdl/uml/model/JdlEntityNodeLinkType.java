package org.strangeway.jdl.uml.model;

import org.jetbrains.annotations.Nullable;

public enum JdlEntityNodeLinkType {
  ONE_TO_MANY,
  ONE_TO_ONE,
  MANY_TO_MANY,
  MANY_TO_ONE;

  public static @Nullable JdlEntityNodeLinkType fromString(String name) {
    if ("OneToMany".equals(name)) {
      return ONE_TO_MANY;
    }
    if ("OneToOne".equals(name)) {
      return ONE_TO_ONE;
    }
    if ("ManyToMany".equals(name)) {
      return MANY_TO_MANY;
    }
    if ("ManyToOne".equals(name)) {
      return MANY_TO_ONE;
    }
    return null;
  }
}
