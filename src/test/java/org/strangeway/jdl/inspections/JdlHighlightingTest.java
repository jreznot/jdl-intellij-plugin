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

package org.strangeway.jdl.inspections;

import com.intellij.testFramework.fixtures.BasePlatformTestCase;

import java.util.List;

public class JdlHighlightingTest extends BasePlatformTestCase {
  @Override
  protected String getTestDataPath() {
    return "src/test/resources/org/strangeway/jdl/highlighting";
  }

  public void testNorthwind() {
    doTest("Northwind.jdl");
  }

  public void testMicroservices() {
    doTest("Microservices.jdl");
  }

  public void testSpace() {
    doTest("Space.jdl");
  }

  private void doTest(String file) {
    myFixture.enableInspections(List.of(
        JdlIncorrectOptionTypeInspection.class,
        JdlUnknownOptionInspection.class,
        JdlDuplicatedDeclarationInspection.class
    ));

    myFixture.configureByFile(file);
    myFixture.checkHighlighting(true, false, true);
  }
}
