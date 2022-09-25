package org.strangeway.jdl.uml;

import com.intellij.diagram.*;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import org.intellij.lang.annotations.Pattern;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.strangeway.jdl.uml.model.JdlDiagramNode;

final class JdlUmlProvider extends DiagramProvider<JdlDiagramNode> {

  private final DiagramVfsResolver<JdlDiagramNode> vfsResolver = new JdlUmlVfsResolver();
  private final DiagramElementManager<JdlDiagramNode> elementManager = new JdlUmlElementManager();
  private final DiagramRelationshipManager<JdlDiagramNode> relationshipManager = new JdlUmlRelationshipManager();

  @Pattern("[a-zA-Z0-9_-]*")
  @Override
  public @NotNull String getID() {
    return "JhipsterJDL";
  }

  @SuppressWarnings("DialogTitleCapitalization")
  @Override
  public @NotNull String getPresentableName() {
    return "JHipster Entities";
  }

  @Override
  public @NotNull DiagramDataModel<JdlDiagramNode> createDataModel(@NotNull Project project,
                                                                   @Nullable JdlDiagramNode JdlDiagramNode,
                                                                   @Nullable VirtualFile virtualFile,
                                                                   @NotNull DiagramPresentationModel diagramPresentationModel) {
    return new JdlUmlDataModel(project, this); // todo
  }

  @Override
  public @NotNull DiagramVisibilityManager createVisibilityManager() {
    return EmptyDiagramVisibilityManager.INSTANCE;
  }

  @Override
  public @NotNull DiagramElementManager<JdlDiagramNode> getElementManager() {
    return elementManager;
  }

  @Override
  public @NotNull DiagramVfsResolver<JdlDiagramNode> getVfsResolver() {
    return vfsResolver;
  }

  @Override
  public @NotNull DiagramRelationshipManager<JdlDiagramNode> getRelationshipManager() {
    return relationshipManager;
  }

  @Override
  public @NotNull DiagramNodeContentManager createNodeContentManager() {
    return new JdlUmlCategoryManager();
  }
}
