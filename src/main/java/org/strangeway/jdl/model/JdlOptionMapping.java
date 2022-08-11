package org.strangeway.jdl.model;

public class JdlOptionMapping {
  private final String name;
  private final JdlOptionType propertyType;

  public JdlOptionMapping(String name, JdlOptionType optionType) {
    this.name = name;
    this.propertyType = optionType;
  }

  public String getName() {
    return name;
  }

  public JdlOptionType getPropertyType() {
    return propertyType;
  }
}
