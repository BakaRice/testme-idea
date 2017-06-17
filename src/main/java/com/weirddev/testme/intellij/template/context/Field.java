package com.weirddev.testme.intellij.template.context;

import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiField;
import com.intellij.psi.PsiModifier;
import com.intellij.psi.util.PsiUtil;
import com.weirddev.testme.intellij.utils.ClassNameUtils;

/**
 * Date: 24/10/2016
 * @author Yaron Yamin
 */
public class Field {
    private final Type type;
    private final boolean overridden;
    private final boolean finalType;
    private final boolean isFinal;
    private final boolean isStatic;
    private final String ownerClassCanonicalName;
    private String name;

    public Field(PsiField psiField, PsiClass srcClass) {
        this.name = psiField.getName();
        type = new Type(psiField.getType(), null, 0);
        finalType = isFinal(PsiUtil.resolveClassInType(psiField.getType()));
        String canonicalText = srcClass.getQualifiedName();
        ownerClassCanonicalName = ClassNameUtils.stripArrayVarargsDesignator(canonicalText);
        overridden = isOverriddenInChild(psiField, srcClass);
        isFinal = psiField.getModifierList() != null && psiField.getModifierList().hasExplicitModifier(PsiModifier.FINAL);
        isStatic = psiField.getModifierList() != null && psiField.getModifierList().hasExplicitModifier(PsiModifier.STATIC);
    }
    private boolean isOverriddenInChild(PsiField psiField, PsiClass srcClass) {
        String srcQualifiedName = srcClass.getQualifiedName();
        String fieldClsQualifiedName = psiField.getContainingClass()==null?null:psiField.getContainingClass().getQualifiedName();
        return (srcQualifiedName!=null && fieldClsQualifiedName!=null &&  !srcQualifiedName.equals(fieldClsQualifiedName)) && srcClass.findFieldByName(psiField.getName(), false)!=null;
    }
    private boolean isFinal(PsiClass aClass) {
        return aClass != null &&  aClass.getModifierList()!=null && aClass.getModifierList().hasExplicitModifier(PsiModifier.FINAL);
    }

    public boolean isOverridden() {
        return overridden;
    }

    public String getName() {
        return name;
    }
    public Type getType() {
        return type;
    }

    public boolean isFinalType() {
        return finalType;
    }

    public boolean isFinal() {
        return isFinal;
    }

    public boolean isStatic() {
        return isStatic;
    }

    public String getOwnerClassCanonicalName() {
        return ownerClassCanonicalName;
    }
}
