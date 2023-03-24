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
