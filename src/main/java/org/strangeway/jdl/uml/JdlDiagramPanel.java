package org.strangeway.jdl.uml;

import com.intellij.openapi.Disposable;

import javax.swing.*;

final class JdlDiagramPanel implements Disposable {

  private JComponent chartPanel = new JPanel();

  @Override
  public void dispose() {

  }

  JComponent getComponent() {
    return chartPanel;
  }
}
