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

import com.intellij.util.containers.ContainerUtil;
import org.jetbrains.annotations.NotNull;
import org.strangeway.jdl.JhipsterIcons;

import javax.swing.*;
import java.util.List;
import java.util.Objects;

public final class JdlEnumNodeData implements JdlNodeData {
  private final String name;
  private final List<JdlEnumNodeItem> options;

  public JdlEnumNodeData(@NotNull String name, @NotNull List<@NotNull String> options) {
    this.name = name;
    this.options = ContainerUtil.map(options, JdlEnumNodeItem::new);
  }

  @Override
  public @NotNull String getName() {
    return name;
  }

  @Override
  public Icon getIcon() {
    return JhipsterIcons.getEnumIcon();
  }

  public @NotNull List<@NotNull JdlEnumNodeItem> getOptions() {
    return options;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    JdlEnumNodeData that = (JdlEnumNodeData) o;
    return name.equals(that.name) && options.equals(that.options);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, options);
  }
}
