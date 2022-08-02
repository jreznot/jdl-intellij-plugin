package org.strangeway.jdl;

import com.intellij.testFramework.ParsingTestCase;
import org.strangeway.jdl.psi.JdlParserDefinition;

public class JdlParserTest extends ParsingTestCase {
    public JdlParserTest() {
        super("", "jdl", new JdlParserDefinition());
    }

    @Override
    protected String getTestDataPath() {
        return "src/test/resources/org/strangeway/jdl";
    }

    public void testApplication() {
        doTest(true);
    }
}
