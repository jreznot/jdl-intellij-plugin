package org.strangeway.jdl.uml;

import com.intellij.diagram.DiagramBuilder;
import com.intellij.diagram.DiagramBuilderFactory;
import com.intellij.openapi.Disposable;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.graph.GraphDataKeys;
import com.intellij.openapi.graph.services.GraphLayoutService;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.Disposer;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiManager;
import com.intellij.ui.JBColor;
import com.intellij.uml.UmlFileEditorImpl;
import com.intellij.uml.components.UmlGraphZoomableViewport;
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

  private final JComponent chartPanel = new JPanel(new BorderLayout());
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
      chartPanel.add(createSimpleGraphView(builder), BorderLayout.CENTER);
    }

    builder.getDataModel().refreshDataModel();

    builder.queryUpdate()
        .withDataReload()
        .withPresentationUpdate()
        .withRelayout()
        .runAsync();
  }

  private JComponent createSimpleGraphView(@NotNull DiagramBuilder builder) {
    builder.getPresentationModel().registerActions();

    var view = builder.getView();
    GraphDataKeys.addDataProvider(view, dataId -> {
      if (CommonDataKeys.VIRTUAL_FILE.getName().equals(dataId)) {
        return fileEditor.getFile();
      }
      if (CommonDataKeys.PSI_FILE.getName().equals(dataId)) {
        return PsiManager.getInstance(fileEditor.getProject()).findFile(fileEditor.getFile());
      }
      return UmlFileEditorImpl.getData(dataId, builder);
    });

    view.getCanvasComponent().setBackground(JBColor.GRAY);
    GraphLayoutService.getInstance().queryLayout(builder.getGraphBuilder()).withFitContent(AFTER).run();

    return new UmlGraphZoomableViewport(builder);
  }
}
