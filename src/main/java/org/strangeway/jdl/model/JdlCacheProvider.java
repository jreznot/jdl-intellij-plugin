package org.strangeway.jdl.model;

public enum JdlCacheProvider implements JdlEnum {
  NO("no"),
  CAFFEINE("caffeine"),
  EHCACHE("ehcache"),
  HAZELCAST("hazelcast"),
  INFINISPAN("infinispan"),
  MEMCACHED("memcached"),
  REDIS("redis");

  private final String id;

  JdlCacheProvider(String id) {
    this.id = id;
  }

  @Override
  public String getId() {
    return id;
  }
}
