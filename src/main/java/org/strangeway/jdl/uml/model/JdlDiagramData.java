/*
 * Copyright © 2023 Yuriy Artamonov
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software
 * and associated documentation files (the "Software"), to deal in the Software without
 * restriction, including without limitation the rights to use, copy, modify, merge, publish,
 * distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the
 * Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies
 * or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING
 * BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM,
 * DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING
 * FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package org.strangeway.jdl.uml.model;

import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.Objects;

public final class JdlDiagramData {

  private final Collection<JdlEntityNodeData> entities;
  private final Collection<JdlEnumNodeData> enums;
  private final Collection<JdlEntityNodeLink> entityLinks;
  private final Collection<JdlEnumNodeLink> enumLinks;

  public JdlDiagramData(@NotNull Collection<JdlEntityNodeData> entities,
                        @NotNull Collection<JdlEnumNodeData> enums,
                        @NotNull Collection<JdlEntityNodeLink> entityLinks,
                        @NotNull Collection<JdlEnumNodeLink> enumLinks) {
    this.entities = entities;
    this.enums = enums;
    this.entityLinks = entityLinks;
    this.enumLinks = enumLinks;
  }

  public @NotNull Collection<JdlEntityNodeData> getEntities() {
    return entities;
  }

  public @NotNull Collection<JdlEnumNodeData> getEnums() {
    return enums;
  }

  public @NotNull Collection<JdlEntityNodeLink> getEntityLinks() {
    return entityLinks;
  }

  public @NotNull Collection<JdlEnumNodeLink> getEnumLinks() {
    return enumLinks;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    JdlDiagramData that = (JdlDiagramData) o;
    return entities.equals(that.entities) && enums.equals(that.enums) && entityLinks.equals(that.entityLinks) && enumLinks.equals(that.enumLinks);
  }

  @Override
  public int hashCode() {
    return Objects.hash(entities, enums, entityLinks, enumLinks);
  }
}
