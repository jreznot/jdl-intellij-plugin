package org.strangeway.jdl.psi;

import com.intellij.openapi.project.Project;
import com.intellij.psi.*;
import com.intellij.util.ArrayUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.strangeway.jdl.JdlConstants;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.intellij.psi.search.GlobalSearchScope.allScope;

public final class JdlFieldTypeReference extends PsiReferenceBase<JdlFieldType> {
  public JdlFieldTypeReference(@NotNull JdlFieldType element) {
    super(element);
  }

  @Override
  public @Nullable PsiElement resolve() {
    String typeName = myElement.getTypeName();
    String jvmTypeName = JdlConstants.FIELD_TYPES.get(typeName);

    if (jvmTypeName != null) {
      Project project = myElement.getProject();
      return JavaPsiFacade.getInstance(project).findClass(jvmTypeName, allScope(project));
    }

    PsiFile containingFile = myElement.getContainingFile();
    if (containingFile == null) return null;

    List<JdlEnum> enumRefs = new ArrayList<>();

    // todo support files in the same directory
    containingFile.processDeclarations((element, state) -> {
      if (element instanceof JdlEnum && Objects.equals(typeName, ((JdlEnum) element).getName())) {
        enumRefs.add((JdlEnum) element);
        return false;
      }
      return true;
    }, ResolveState.initial(), null, myElement);

    if (enumRefs.size() != 1) return null;

    return enumRefs.get(0);
  }

  @Override
  public Object @NotNull [] getVariants() {
    return ArrayUtil.EMPTY_OBJECT_ARRAY; // provided by completion contributor
  }
}
