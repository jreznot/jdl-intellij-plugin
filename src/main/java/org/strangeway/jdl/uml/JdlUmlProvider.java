package org.strangeway.jdl.uml;

import com.intellij.diagram.*;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import org.intellij.lang.annotations.Pattern;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.strangeway.jdl.uml.model.JdlNodeData;

final class JdlUmlProvider extends DiagramProvider<JdlNodeData> {

  private final DiagramVfsResolver<JdlNodeData> vfsResolver = new JdlUmlVfsResolver();
  private final DiagramElementManager<JdlNodeData> elementManager = new JdlUmlElementManager();
  private final DiagramRelationshipManager<JdlNodeData> relationshipManager = new JdlUmlRelationshipManager();

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

  public JdlUmlProvider() {
    this.elementManager.setUmlProvider(this);
  }

  @Override
  public @NotNull DiagramDataModel<JdlNodeData> createDataModel(@NotNull Project project,
                                                                @Nullable JdlNodeData seedData,
                                                                @Nullable VirtualFile umlVirtualFile,
                                                                @NotNull DiagramPresentationModel diagramPresentationModel) {
    return new JdlUmlDataModel(project, this);
  }

  @Override
  public @NotNull DiagramVisibilityManager createVisibilityManager() {
    return EmptyDiagramVisibilityManager.INSTANCE;
  }

  @Override
  public @NotNull DiagramElementManager<JdlNodeData> getElementManager() {
    return elementManager;
  }

  @Override
  public @NotNull DiagramVfsResolver<JdlNodeData> getVfsResolver() {
    return vfsResolver;
  }

  @Override
  public @NotNull DiagramRelationshipManager<JdlNodeData> getRelationshipManager() {
    return relationshipManager;
  }

  @Override
  public @NotNull DiagramNodeContentManager createNodeContentManager() {
    return new JdlUmlCategoryManager();
  }
}
