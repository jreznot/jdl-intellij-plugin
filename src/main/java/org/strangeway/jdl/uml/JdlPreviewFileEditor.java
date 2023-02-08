/*
 * Copyright © 2023 Yuriy Artamonov
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software
 * and associated documentation files (the “Software”), to deal in the Software without
 * restriction, including without limitation the rights to use, copy, modify, merge, publish,
 * distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the
 * Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies
 * or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED “AS IS”, WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING
 * BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM,
 * DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING
 * FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package org.strangeway.jdl.uml;

import com.intellij.openapi.application.ApplicationManager;
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
import com.intellij.util.ui.update.MergingUpdateQueue;
import com.intellij.util.ui.update.Update;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.beans.PropertyChangeListener;

final class JdlPreviewFileEditor extends UserDataHolderBase implements FileEditor {
  private static final int RENDERING_DELAY_MS = 1000;

  private final Project myProject;
  private final VirtualFile myFile;
  private final @Nullable Document myDocument;

  private volatile boolean isDisposed = false;

  private final JPanel myUmlPanelWrapper;
  private @Nullable JdlDiagramPanel myPanel;

  private final MergingUpdateQueue mergingUpdateQueue = new MergingUpdateQueue("JDL", RENDERING_DELAY_MS, true, null, this);
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
        }

        @Override
        public void documentChanged(@NotNull DocumentEvent e) {
          updateUml();
        }
      }, this);
    }

    myUmlPanelWrapper = new JPanel(new BorderLayout());

    myUmlPanelWrapper.addComponentListener(new ComponentAdapter() {
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

  private void attachHtmlPanel() {
    myPanel = new JdlDiagramPanel(this);
    myUmlPanelWrapper.add(myPanel.getComponent(), BorderLayout.CENTER);
    Disposer.register(this, myPanel);

    if (myUmlPanelWrapper.isShowing()) myUmlPanelWrapper.validate();
    myUmlPanelWrapper.repaint();

    myPanel.draw();
  }

  private void detachHtmlPanel() {
    if (myPanel != null) {
      myUmlPanelWrapper.remove(myPanel.getComponent());
      Disposer.dispose(myPanel);
      myPanel = null;
    }
  }

  private boolean isPreviewShown(Project project, VirtualFile file) {
    return true;
  }

  @Override
  public @NotNull JComponent getComponent() {
    return myUmlPanelWrapper;
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
  public void setState(@NotNull FileEditorState state) {
  }

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
      updateUml();
    }
  }

  @Override
  public void addPropertyChangeListener(@NotNull PropertyChangeListener listener) {
  }

  @Override
  public void removePropertyChangeListener(@NotNull PropertyChangeListener listener) {
  }

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

  public Project getProject() {
    return myProject;
  }

  private void updateUml() {
    if (myPanel == null || myDocument == null || !myFile.isValid() || isDisposed) {
      return;
    }

    mergingUpdateQueue.queue(new Update("JDL.REDRAW") {
      @Override
      public void run() {
        ApplicationManager.getApplication().invokeLater(() -> {
          if (myPanel == null || !myFile.isValid() || isDisposed) {
            return;
          }

          myPanel.draw();
        });
      }
    });
  }

  public void setMainEditor(Editor mainEditor) {
    this.mainEditor = mainEditor;
  }
}