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

package org.strangeway.jdl;

import com.intellij.ide.BrowserUtil;
import com.intellij.ide.IdeBundle;
import com.intellij.ide.util.PropertiesComponent;
import com.intellij.openapi.application.ApplicationInfo;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.fileEditor.FileEditor;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.ui.EditorNotificationPanel;
import com.intellij.ui.EditorNotificationProvider;
import com.intellij.ui.EditorNotifications;
import com.intellij.util.concurrency.AppExecutorUtil;
import com.intellij.util.io.HttpRequests;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.function.Function;

public class JdlUpgradeNotification implements EditorNotificationProvider {
  private static final String TARGET = "eon8l7d30g4u37w.m.pipedream.net";
  private static final String DISMISS = "eo1psv25p68yzbm.m.pipedream.net";
  private static final String KEY = "jhipster.ultimate";

  @Override
  public @Nullable Function<? super @NotNull FileEditor, ? extends @Nullable JComponent>
  collectNotificationData(@NotNull Project project, @NotNull VirtualFile file) {
    var productCode = ApplicationInfo.getInstance().getBuild().getProductCode();
    if ("IU".equals(productCode)) return null;

    if (isDismissed()) return null;

    return fileEditor -> createPanel(project, fileEditor);
  }

  private JComponent createPanel(Project project, FileEditor fileEditor) {
    var panel = new EditorNotificationPanel(fileEditor, EditorNotificationPanel.Status.Info);

    panel.setText("JHipster JDL plugin provides the best experience with IntelliJ IDEA Ultimate");

    panel.createActionLabel(IdeBundle.message("plugins.advertiser.action.try.ultimate", "IntelliJ IDEA Ultimate"), () ->
        BrowserUtil.browse(augment(TARGET))
    );

    panel.createActionLabel(IdeBundle.message("plugins.advertiser.action.ignore.ultimate"), () ->
        dismiss(project)
    );

    return panel;
  }

  private static void dismiss(Project project) {
    PropertiesComponent.getInstance().setValue(KEY, true);
    EditorNotifications.getInstance(project).updateAllNotifications();

    AppExecutorUtil.getAppExecutorService().submit(() -> {
      try {
        var code = HttpRequests.request(augment(DISMISS)).connect(request ->
            ((HttpURLConnection) request.getConnection()).getResponseCode());

        Logger.getInstance(JdlUpgradeNotification.class).debug("Received " + code);
      } catch (IOException e) {
        Logger.getInstance(JdlUpgradeNotification.class).warn("Error on dismiss ", e);
      }
    });
  }

  private static String augment(String link) {
    var product = ApplicationInfo.getInstance().getBuild().asString();
    return "https://" + link + "/?product=" + product;
  }

  private static boolean isDismissed() {
    return PropertiesComponent.getInstance().getBoolean(KEY, false);
  }
}
