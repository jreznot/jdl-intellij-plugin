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

import com.intellij.ide.structureView.*;
import com.intellij.ide.util.treeView.smartTree.Sorter;
import com.intellij.ide.util.treeView.smartTree.TreeElement;
import com.intellij.lang.PsiStructureViewFactory;
import com.intellij.navigation.ItemPresentation;
import com.intellij.navigation.NavigationItem;
import com.intellij.openapi.editor.Editor;
import com.intellij.pom.Navigatable;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.util.PsiTreeUtil;
import com.intellij.util.containers.ContainerUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.strangeway.jdl.psi.*;

final class JdlPsiStructureViewFactory implements PsiStructureViewFactory {
  @Override
  public StructureViewBuilder getStructureViewBuilder(@NotNull PsiFile psiFile) {
    return new TreeBasedStructureViewBuilder() {
      @NotNull
      @Override
      public StructureViewModel createStructureViewModel(@Nullable Editor editor) {
        return new JdlStructureViewModel(psiFile, editor);
      }
    };
  }
}

class JdlStructureViewModel extends StructureViewModelBase implements StructureViewModel.ElementInfoProvider {
  public JdlStructureViewModel(@NotNull PsiFile psiFile, @Nullable Editor editor) {
    super(psiFile, editor, new JdlStructureViewElement(psiFile));
    withSuitableClasses(
        JdlFile.class,
        JdlApplication.class, JdlConfigBlock.class, JdlOptionNameValue.class,
        JdlConfigurationOption.class,
        JdlEntity.class, JdlEntityFieldMapping.class,
        JdlEnum.class, JdlEnumValue.class,
        JdlConstant.class,
        JdlRelationshipGroup.class, JdlDeployment.class
    );
    withSorters(Sorter.ALPHA_SORTER);
  }

  @Override
  public boolean isAlwaysShowsPlus(StructureViewTreeElement element) {
    return false;
  }

  @Override
  public boolean isAlwaysLeaf(StructureViewTreeElement element) {
    return false;
  }
}

class JdlStructureViewElement implements StructureViewTreeElement {
  private final PsiElement element;

  public JdlStructureViewElement(@NotNull PsiElement element) {
    this.element = element;
  }

  @Override
  public PsiElement getValue() {
    return element;
  }

  @Override
  public void navigate(boolean requestFocus) {
    ((Navigatable) element).navigate(requestFocus);
  }

  @Override
  public boolean canNavigate() {
    return ((Navigatable) element).canNavigate();
  }

  @Override
  public boolean canNavigateToSource() {
    return ((Navigatable) element).canNavigateToSource();
  }

  @NotNull
  @Override
  public ItemPresentation getPresentation() {
    ItemPresentation presentation = ((NavigationItem) element).getPresentation();
    assert presentation != null;
    return presentation;
  }

  @Override
  public TreeElement @NotNull [] getChildren() {
    if (element instanceof JdlFile) {
      Class<?>[] topLevelElements = {
          JdlApplication.class,
          JdlConfigurationOption.class,
          JdlEntity.class,
          JdlEnum.class,
          JdlConstant.class,
          JdlRelationshipGroup.class, JdlDeployment.class
      };
      @SuppressWarnings("unchecked")
      var elements = PsiTreeUtil.getChildrenOfAnyType(element, (Class<PsiElement>[]) topLevelElements);
      return ContainerUtil.map2Array(elements, TreeElement.class, JdlStructureViewElement::new);
    }

    if (element instanceof JdlApplication) {
      Class<?>[] appElements = {
          JdlConfigBlock.class,
          JdlConfigurationOption.class
      };
      @SuppressWarnings("unchecked")
      var elements = PsiTreeUtil.getChildrenOfAnyType(element, (Class<PsiElement>[]) appElements);
      return ContainerUtil.map2Array(elements, TreeElement.class, JdlStructureViewElement::new);
    }

    if (element instanceof JdlConfigBlock
        || element instanceof JdlDeployment) {
      var elements = PsiTreeUtil.getChildrenOfAnyType(element, JdlOptionNameValue.class);
      return ContainerUtil.map2Array(elements, TreeElement.class, JdlStructureViewElement::new);
    }

    if (element instanceof JdlEntity) {
      var elements = PsiTreeUtil.getChildrenOfAnyType(element, JdlEntityFieldMapping.class);
      return ContainerUtil.map2Array(elements, TreeElement.class, JdlStructureViewElement::new);
    }

    if (element instanceof JdlEnum) {
      var elements = PsiTreeUtil.getChildrenOfAnyType(element, JdlEnumValue.class);
      return ContainerUtil.map2Array(elements, TreeElement.class, JdlStructureViewElement::new);
    }

    return EMPTY_ARRAY;
  }
}

