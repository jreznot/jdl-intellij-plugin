package org.strangeway.jdl.uml;

import com.intellij.openapi.Disposable;
import com.intellij.openapi.components.Service;

@Service(Service.Level.PROJECT)
public final class JdlDiagramService implements Disposable {
  @Override
  public void dispose() {
  }
}
