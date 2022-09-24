package org.strangeway.jdl.psi;

import com.intellij.codeInsight.lookup.LookupElement;
import com.intellij.codeInsight.lookup.LookupElementBuilder;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiReferenceBase;
import com.intellij.psi.ResolveState;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.strangeway.jdl.JhipsterIcons;
import org.strangeway.jdl.model.JdlDeclarationsModel;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

import static org.apache.commons.lang.ArrayUtils.EMPTY_OBJECT_ARRAY;

final class JdlConstantNameReference extends PsiReferenceBase<JdlId> {
  public JdlConstantNameReference(@NotNull JdlId element) {
    super(element);
  }

  @Override
  public @Nullable PsiElement resolve() {
    PsiFile containingFile = myElement.getContainingFile();
    if (containingFile == null) return null;

    List<JdlConstant> constantRefs = new ArrayList<>();

    String myId = myElement.getText(); // todo move ID to mixin

    // todo support files in the same directory
    containingFile.processDeclarations((element, state) -> {
      if (element instanceof JdlConstant && Objects.equals(myId, ((JdlConstant) element).getName())) {
        constantRefs.add((JdlConstant) element);
        return false;
      }
      return true;
    }, ResolveState.initial(), null, myElement);

    if (constantRefs.size() != 1) return null;

    return constantRefs.get(0);
  }

  @Override
  public Object @NotNull [] getVariants() {
    PsiFile containingFile = myElement.getContainingFile();
    if (containingFile == null) return EMPTY_OBJECT_ARRAY;

    Collection<JdlConstant> constants = JdlDeclarationsModel.findAllJdlConstants(containingFile);
    List<LookupElement> items = new ArrayList<>();

    for (JdlConstant entity : constants) {
      items.add(LookupElementBuilder.create(entity)
          .withIcon(JhipsterIcons.getConstantIcon()));
    }

    return items.toArray();
  }
}
