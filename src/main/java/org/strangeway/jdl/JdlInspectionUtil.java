package org.strangeway.jdl;

import com.intellij.openapi.util.Ref;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiNamedElement;
import com.intellij.psi.PsiReference;
import com.intellij.psi.util.CachedValueProvider.Result;
import com.intellij.psi.util.PsiModificationTracker;
import org.jetbrains.annotations.NotNull;
import org.strangeway.jdl.model.JdlDeclarationsModel;
import org.strangeway.jdl.psi.*;

import java.util.*;
import java.util.stream.Collectors;

import static com.intellij.psi.util.CachedValuesManager.getCachedValue;

public final class JdlInspectionUtil {
  private JdlInspectionUtil() {
  }

  public static @NotNull Map<String, List<@NotNull JdlEntity>> getAllEntities(@NotNull PsiFile file) {
    return getCachedValue(file, () -> {
      Collection<JdlEntity> allEntities = JdlDeclarationsModel.findAllEntities(file);
      Map<String, List<JdlEntity>> map = allEntities.stream()
          .collect(Collectors.groupingBy(PsiNamedElement::getName));
      return Result.create(map, PsiModificationTracker.MODIFICATION_COUNT);
    });
  }

  public static @NotNull Map<String, List<@NotNull JdlEnum>> getAllEnums(@NotNull PsiFile file) {
    return getCachedValue(file, () -> {
      Collection<JdlEnum> allEnums = JdlDeclarationsModel.findAllEnums(file);
      Map<String, List<JdlEnum>> map = allEnums.stream()
          .collect(Collectors.groupingBy(PsiNamedElement::getName));
      return Result.create(map, PsiModificationTracker.MODIFICATION_COUNT);
    });
  }

  public static @NotNull Collection<@NotNull JdlEntity> getUsedEntities(@NotNull PsiFile file) {
    return getCachedValue(file, () -> Result.create(findUsedEntities(file), PsiModificationTracker.MODIFICATION_COUNT));
  }

  private static @NotNull Set<@NotNull JdlEntity> findUsedEntities(@NotNull PsiFile file) {
    Set<JdlEntity> usedEntities = new HashSet<>();

    Ref<Boolean> allUsed = new Ref<>(false);

    file.accept(new JdlRecursiveElementVisitor() {
      @Override
      public void visitId(@NotNull JdlId o) {
        super.visitId(o);

        if (isUnderEntitiesSelector(o)) {
          PsiReference reference = o.getReference();
          if (reference != null) {
            PsiElement element = reference.resolve();
            if (element instanceof JdlEntity) {
              usedEntities.add((JdlEntity) element);
            }
          }
        }
      }

      @Override
      public void visitWildcardLiteral(@NotNull JdlWildcardLiteral o) {
        super.visitWildcardLiteral(o);

        if (o.getParent() instanceof JdlConfigurationOption) {
          JdlConfigurationOption configurationOption = (JdlConfigurationOption) o.getParent();
          if ("entities".equals(configurationOption.getName())) {
            allUsed.set(true);
          }
        }
      }

      private boolean isUnderEntitiesSelector(@NotNull PsiElement o) {
        if (o.getParent() instanceof JdlEntitiesList
            && o.getParent().getParent() instanceof JdlConfigurationOption) {
          JdlConfigurationOption configurationOption = (JdlConfigurationOption) o.getParent().getParent();
          return "entities".equals(configurationOption.getName());
        }
        return false;
      }
    });

    if (allUsed.get()) {
      usedEntities.addAll(JdlDeclarationsModel.findAllEntities(file));
    }
    return Set.copyOf(usedEntities);
  }

  public static Collection<@NotNull JdlEnum> getUsedEnums(@NotNull PsiFile file) {
    return getCachedValue(file, () -> Result.create(findUsedEnums(file), PsiModificationTracker.MODIFICATION_COUNT));
  }

  private static Collection<@NotNull JdlEnum> findUsedEnums(@NotNull PsiFile file) {
    Set<JdlEnum> usedEnums = new HashSet<>();

    file.accept(new JdlRecursiveElementVisitor() {
      @Override
      public void visitFieldType(@NotNull JdlFieldType o) {
        super.visitFieldType(o);

        PsiReference reference = o.getReference();
        if (reference != null) {
          PsiElement resolved = reference.resolve();
          if (resolved instanceof JdlEnum) {
            usedEnums.add((JdlEnum) resolved);
          }
        }
      }
    });

    return Set.copyOf(usedEnums);
  }
}
