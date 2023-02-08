/*
 * Copyright © 2023 Yuriy Artamonov
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software
 * and associated documentation files (the “Software”), to deal in the Software without
 * restriction, including without limitation the rights to use, copy, modify, merge, publish,
 * distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the
 * Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies
 * or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED “AS IS”, WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING
 * BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM,
 * DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING
 * FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package org.strangeway.jdl.uml.model;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public final class JdlEntityNodeLink {
  private final JdlEntityNodeLinkType type;
  private final JdlEntityNodeData fromEntity;
  private final JdlEntityNodeData toEntity;

  public JdlEntityNodeLink(@NotNull JdlEntityNodeLinkType type,
                           @NotNull JdlEntityNodeData fromEntity,
                           @NotNull JdlEntityNodeData toEntity) {
    this.type = type;
    this.fromEntity = fromEntity;
    this.toEntity = toEntity;
  }

  public @NotNull JdlEntityNodeLinkType getType() {
    return type;
  }

  public @NotNull JdlEntityNodeData getFromEntity() {
    return fromEntity;
  }

  public @NotNull JdlEntityNodeData getToEntity() {
    return toEntity;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    JdlEntityNodeLink that = (JdlEntityNodeLink) o;
    return type == that.type && fromEntity.equals(that.fromEntity) && toEntity.equals(that.toEntity);
  }

  @Override
  public int hashCode() {
    return Objects.hash(type, fromEntity, toEntity);
  }
}
