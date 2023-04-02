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

public class JdlInspectionsTest extends BasePlatformTestCase {
  @Override
  protected String getTestDataPath() {
    return "src/test/resources/org/strangeway/jdl/inspections";
  }

  public void testUnusedEntities() {
    myFixture.enableInspections(JdlUnusedDeclarationInspection.class);
    myFixture.configureByFile("UnusedEntities.jdl");
    myFixture.checkHighlighting();
  }

  public void testUnusedEnums() {
    myFixture.enableInspections(JdlUnusedDeclarationInspection.class);
    myFixture.configureByFile("UnusedEnums.jdl");
    myFixture.checkHighlighting();
  }

  public void testDuplicatedEntity() {
    myFixture.enableInspections(JdlDuplicatedDeclarationInspection.class);
    myFixture.configureByFile("DuplicatedEntity.jdl");
    myFixture.checkHighlighting();
  }

  public void testDuplicatedEnum() {
    myFixture.enableInspections(JdlDuplicatedDeclarationInspection.class);
    myFixture.configureByFile("DuplicatedEnum.jdl");
    myFixture.checkHighlighting();
  }

  public void testUnknownOption() {
    myFixture.enableInspections(JdlUnknownOptionInspection.class);
    myFixture.configureByFile("UnknownOptions.jdl");
    myFixture.checkHighlighting();
  }

  public void testIncorrectOptionType() {
    myFixture.enableInspections(JdlIncorrectOptionTypeInspection.class);
    myFixture.configureByFile("IncorrectOptionTypes.jdl");
    myFixture.checkHighlighting();
  }
}
