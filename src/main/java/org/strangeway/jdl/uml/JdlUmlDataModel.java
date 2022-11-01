package org.strangeway.jdl.uml;

import com.intellij.diagram.DiagramDataModel;
import com.intellij.diagram.DiagramEdge;
import com.intellij.diagram.DiagramNode;
import com.intellij.diagram.DiagramProvider;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.ModificationTracker;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiManager;
import com.intellij.psi.util.PsiModificationTracker;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.strangeway.jdl.JdlLanguage;
import org.strangeway.jdl.psi.JdlEntity;
import org.strangeway.jdl.psi.JdlEnum;
import org.strangeway.jdl.psi.JdlFile;
import org.strangeway.jdl.uml.model.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

final class JdlUmlDataModel extends DiagramDataModel<JdlNodeData> {
  private final List<JdlDiagramNode> nodes = new ArrayList<>();

  public JdlUmlDataModel(@NotNull Project project,
                         @NotNull DiagramProvider<JdlNodeData> provider) {
    super(project, provider);
  }

  @Override
  public @NotNull ModificationTracker getModificationTracker() {
    var psiModificationTracker = PsiModificationTracker.getInstance(getProject());
    return psiModificationTracker.forLanguage(JdlLanguage.INSTANCE);
  }

  @Override
  public @NotNull Collection<? extends DiagramNode<JdlNodeData>> getNodes() {
    return nodes;
  }

  @Override
  public @NotNull String getNodeName(@NotNull DiagramNode<JdlNodeData> diagramNode) {
    return diagramNode.getIdentifyingElement().getName();
  }

  @Override
  public @Nullable DiagramNode<JdlNodeData> addElement(@Nullable JdlNodeData data) {
    if (data == null) return null;

    if (data instanceof JdlDiagramRootData) {
      var diagramData = JdlUmlDataModel.extractData(getProject(), ((JdlDiagramRootData) data).getVirtualFile());
      for (var entity : diagramData.getEntities()) {
        addElement(entity);
      }
      for (var enumeration : diagramData.getEnums()) {
        addElement(enumeration);
      }

      return null;
    }

    var node = new JdlDiagramNode(data, getProvider());
    this.nodes.add(node);
    return node;
  }

  @Override
  public @NotNull Collection<? extends DiagramEdge<JdlNodeData>> getEdges() {
    return Collections.emptyList(); // todo
  }

  @Override
  public void dispose() {
  }

  public static @NotNull JdlDiagramData extractData(@NotNull Project project, @Nullable VirtualFile file) {
    if (file == null) return new JdlDiagramData(List.of(), List.of(), List.of(), List.of());

    var psiFile = PsiManager.getInstance(project).findFile(file);
    if (!(psiFile instanceof JdlFile)) return new JdlDiagramData(List.of(), List.of(), List.of(), List.of());

    return extractData((JdlFile) psiFile);
  }

  public static @NotNull JdlDiagramData extractData(@NotNull JdlFile file) {
    List<JdlEntityNodeData> entities = new ArrayList<>();
    List<JdlEnumNodeData> enums = new ArrayList<>();

    for (var declaration : file.getDeclarations()) {
      if (declaration instanceof JdlEnum) {
        JdlEnum enumeration = (JdlEnum) declaration;
        String name = enumeration.getName();
        enums.add(new JdlEnumNodeData(name != null ? name : "", List.of()));
      } else if (declaration instanceof JdlEntity) {
        JdlEntity entity = (JdlEntity) declaration;
        String name = entity.getName();
        entities.add(new JdlEntityNodeData(name != null ? name : "", List.of()));
      }
    }

    // todo
    return new JdlDiagramData(entities, enums, List.of(), List.of());
  }
}
