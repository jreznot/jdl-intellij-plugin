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

package org.strangeway.jdl.run;

import com.intellij.execution.configurations.RunConfigurationOptions;
import com.intellij.openapi.components.StoredProperty;

final class JdlRunConfigurationOptions extends RunConfigurationOptions {
  private final StoredProperty<String> jhipsterLocation = string("").provideDelegate(this, "jhipsterLocation");
  private final StoredProperty<String> jdlFileLocation = string("").provideDelegate(this, "jdlLocation");
  private final StoredProperty<String> outputLocation = string("").provideDelegate(this, "outputLocation");

  // todo --force parameter

  public String getJHipsterLocation() {
    return jhipsterLocation.getValue(this);
  }

  public void setJhipsterLocation(String path) {
    jhipsterLocation.setValue(this, path);
  }

  public String getJdlLocation() {
    return jdlFileLocation.getValue(this);
  }

  public void setJdlLocation(String path) {
    jdlFileLocation.setValue(this, path);
  }

  public String getOutputLocation() {
    return outputLocation.getValue(this);
  }

  public void setOutputLocation(String path) {
    outputLocation.setValue(this, path);
  }
}
