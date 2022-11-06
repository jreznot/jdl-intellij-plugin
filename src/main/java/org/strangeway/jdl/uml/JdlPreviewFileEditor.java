package org.strangeway.jdl.uml;

import com.intellij.openapi.application.ModalityState;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.event.DocumentEvent;
import com.intellij.openapi.editor.event.DocumentListener;
import com.intellij.openapi.fileEditor.FileDocumentManager;
import com.intellij.openapi.fileEditor.FileEditor;
import com.intellij.openapi.fileEditor.FileEditorState;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.Disposer;
import com.intellij.openapi.util.UserDataHolderBase;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.util.Alarm;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.beans.PropertyChangeListener;

final class JdlPreviewFileEditor extends UserDataHolderBase implements FileEditor {
  private static final long PARSING_CALL_TIMEOUT_MS = 500L;
  private static final long RENDERING_DELAY_MS = 200L;

  private final Project myProject;
  private final VirtualFile myFile;
  private final @Nullable Document myDocument;

  private volatile boolean isDisposed = false;

  private final JPanel myHtmlPanelWrapper;
  private @Nullable JdlDiagramPanel myPanel;
  private final Alarm myPooledAlarm = new Alarm(Alarm.ThreadToUse.POOLED_THREAD, this);
  private final Alarm mySwingAlarm = new Alarm(Alarm.ThreadToUse.SWING_THREAD, this);

  private Editor mainEditor;

  public JdlPreviewFileEditor(@NotNull Project project, @NotNull VirtualFile file) {
    myProject = project;
    myFile = file;
    myDocument = FileDocumentManager.getInstance().getDocument(myFile);

    if (myDocument != null) {
      myDocument.addDocumentListener(new DocumentListener() {
        @Override
        public void beforeDocumentChange(@NotNull DocumentEvent e) {
          myPooledAlarm.cancelAllRequests();
        }

        @Override
        public void documentChanged(@NotNull DocumentEvent e) {
          myPooledAlarm.addRequest(() -> updateHtml(), PARSING_CALL_TIMEOUT_MS);
        }
      }, this);
    }

    myHtmlPanelWrapper = new JPanel(new BorderLayout());

    myHtmlPanelWrapper.addComponentListener(new ComponentAdapter() {
      @Override
      public void componentShown(ComponentEvent e) {
        mySwingAlarm.addRequest(() -> {
          if (myPanel == null) {
            attachHtmlPanel();
          }
        }, 0, ModalityState.stateForComponent(getComponent()));
      }

      @Override
      public void componentHidden(ComponentEvent e) {
        mySwingAlarm.addRequest(() -> {
          if (myPanel != null) {
            detachHtmlPanel();
          }
        }, 0, ModalityState.stateForComponent(getComponent()));
      }
    });

    if (isPreviewShown(project, file)) {
      attachHtmlPanel();
    }
  }

  private void detachHtmlPanel() {

  }

  private boolean isPreviewShown(Project project, VirtualFile file) {
    return false;
  }

  private void attachHtmlPanel() {

  }

  @Override
  public @NotNull JComponent getComponent() {
    return myHtmlPanelWrapper;
  }

  @Override
  public @Nullable JComponent getPreferredFocusedComponent() {
    return myPanel != null ? myPanel.getComponent() : null;
  }

  @Override
  public @NotNull String getName() {
    return "JHipster JDL Preview";
  }

  @Override
  public void setState(@NotNull FileEditorState state) { }

  @Override
  public boolean isModified() {
    return false;
  }

  @Override
  public boolean isValid() {
    return true;
  }

  @Override
  public void selectNotify() {
    if (myPanel != null) {
      updateHtmlPooled();
    }
  }

  private void updateHtmlPooled() {

  }

  @Override
  public void addPropertyChangeListener(@NotNull PropertyChangeListener listener) { }

  @Override
  public void removePropertyChangeListener(@NotNull PropertyChangeListener listener) { }

  @Override
  public @NotNull VirtualFile getFile() {
    return myFile;
  }

  @Override
  public void dispose() {
    if (myPanel != null) {
      Disposer.dispose(myPanel);
      this.isDisposed = true;
    }
  }

  private void updateHtml() {
    if (myPanel == null || myDocument == null || !myFile.isValid() || isDisposed) {
      return; // todo
    }
  }

  public void setMainEditor(Editor mainEditor) {
    this.mainEditor = mainEditor;
  }
}