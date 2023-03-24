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

package org.strangeway.jdl.uml;

import com.intellij.diagram.DiagramBuilder;
import com.intellij.diagram.DiagramBuilderFactory;
import com.intellij.openapi.Disposable;
import com.intellij.openapi.actionSystem.ActionManager;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.actionSystem.DataProvider;
import com.intellij.openapi.graph.GraphDataKeys;
import com.intellij.openapi.graph.services.GraphLayoutService;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.Disposer;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiManager;
import com.intellij.ui.JBColor;
import com.intellij.uml.UmlFileEditorImpl;
import com.intellij.uml.components.UmlGraphZoomableViewport;
import com.intellij.util.ui.JBUI;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.awt.*;

import static com.intellij.openapi.graph.services.GraphLayoutService.GraphLayoutQueryParams.FitContentOption.AFTER;
import static org.strangeway.jdl.uml.JdlUmlElementManager.getRootData;

@SuppressWarnings("UnstableApiUsage")
final class JdlDiagramPanel implements Disposable {
  @Nullable
  private DiagramBuilder builder;

  private final MyPanel chartPanel = new MyPanel();
  private final JdlUmlProvider umlProvider = new JdlUmlProvider();
  private final JdlPreviewFileEditor fileEditor;

  public JdlDiagramPanel(JdlPreviewFileEditor fileEditor) {
    this.fileEditor = fileEditor;
  }

  @Override
  public void dispose() {
  }

  JComponent getComponent() {
    return chartPanel;
  }

  public void draw() {
    if (builder == null) {
      Project project = fileEditor.getProject();
      VirtualFile virtualFile = fileEditor.getFile();

      builder = DiagramBuilderFactory.getInstance()
          .create(project, umlProvider, getRootData(project, virtualFile), null);
      Disposer.register(this, builder);
      builder.getView().setFitContentOnResize(true);
      JComponent graphView = createSimpleGraphView(builder);
      chartPanel.add(graphView, BorderLayout.CENTER);

      var actionsProvider = builder.getProvider().getExtras().getToolbarActionsProvider();
      var actionGroup = actionsProvider.createToolbarActions(builder);
      var actionToolbar = ActionManager.getInstance().createActionToolbar("JDL.UML", actionGroup, true);
      actionToolbar.setTargetComponent(graphView);
      actionToolbar.getComponent().setBorder(JBUI.Borders.customLine(JBColor.border(), 0, 0, 1, 0));

      chartPanel.add(actionToolbar.getComponent(), BorderLayout.NORTH);

      builder.queryUpdate()
          .withDataReload()
          .withPresentationUpdate()
          .withRelayout()
          .runAsync();
    }
  }

  private JComponent createSimpleGraphView(@NotNull DiagramBuilder builder) {
    builder.getPresentationModel().registerActions();

    var view = builder.getView();
    GraphDataKeys.addDataProvider(view, chartPanel);

    view.getCanvasComponent().setBackground(JBColor.GRAY);
    GraphLayoutService.getInstance().queryLayout(builder.getGraphBuilder()).withFitContent(AFTER).run();

    return new UmlGraphZoomableViewport(builder);
  }

  private class MyPanel extends JPanel implements DataProvider {
    public MyPanel() {
      super(new BorderLayout());
    }

    @Override
    public @Nullable Object getData(@NotNull @NonNls String dataId) {
      if (builder == null) return null;

      if (CommonDataKeys.VIRTUAL_FILE.getName().equals(dataId)) {
        return fileEditor.getFile();
      }
      if (CommonDataKeys.PSI_FILE.getName().equals(dataId)) {
        return PsiManager.getInstance(fileEditor.getProject()).findFile(fileEditor.getFile());
      }
      if (GraphDataKeys.GRAPH_BUILDER.getName().equals(dataId)) {
        return builder;
      }
      if (GraphDataKeys.GRAPH.getName().equals(dataId)) {
        return builder.getGraph();
      }
      return UmlFileEditorImpl.getData(dataId, builder);
    }
  }
}
