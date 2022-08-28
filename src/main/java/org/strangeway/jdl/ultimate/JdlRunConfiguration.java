package org.strangeway.jdl.ultimate;

import com.intellij.execution.ExecutionException;
import com.intellij.execution.Executor;
import com.intellij.execution.configurations.*;
import com.intellij.execution.process.OSProcessHandler;
import com.intellij.execution.process.ProcessHandler;
import com.intellij.execution.process.ProcessHandlerFactory;
import com.intellij.execution.process.ProcessTerminatedListener;
import com.intellij.execution.runners.ExecutionEnvironment;
import com.intellij.openapi.options.SettingsEditor;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.CharsetToolkit;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.File;

final class JdlRunConfiguration extends RunConfigurationBase<JdlRunConfigurationOptions> {

  JdlRunConfiguration(@NotNull Project project, @Nullable ConfigurationFactory factory, @Nullable String name) {
    super(project, factory, name);
  }

  @Override
  protected @NotNull JdlRunConfigurationOptions getOptions() {
    return (JdlRunConfigurationOptions) super.getOptions();
  }

  @Override
  public @NotNull SettingsEditor<? extends RunConfiguration> getConfigurationEditor() {
    return new JdlRunSettingsEditor(getProject());
  }

  @Override
  public @NotNull RunProfileState getState(@NotNull Executor executor, @NotNull ExecutionEnvironment executionEnvironment) {
    return new CommandLineState(executionEnvironment) {
      @NotNull
      @Override
      protected ProcessHandler startProcess() throws ExecutionException {
        JdlRunConfigurationOptions options = getOptions();

        File jdlFile = new File(options.getJdlLocation());
        GeneralCommandLine commandLine = new GeneralCommandLine("jhipster.cmd", "jdl", jdlFile.getAbsolutePath());
        commandLine.setCharset(CharsetToolkit.getDefaultSystemCharset()); // todo if Windows 10 or 11 then set UTF-8
        commandLine.setWorkDirectory(jdlFile.getParentFile()); // todo change directory depending on options

        OSProcessHandler processHandler = ProcessHandlerFactory.getInstance().createColoredProcessHandler(commandLine);
        ProcessTerminatedListener.attach(processHandler);
        return processHandler;
      }
    };
  }
}
