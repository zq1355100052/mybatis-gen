package com.zq.mybatis.generator.generator;

import org.mybatis.generator.api.CommentGenerator;
import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.dom.java.*;
import org.mybatis.generator.api.dom.xml.XmlElement;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author qiang.zhao
 * @project: generator
 * @description:
 * @date 2021/11/26 15:53
 */
public class CustomizeCommentGenerator implements CommentGenerator {

    private final Collection<Annotations> annotations;
    private String author;
    /**
     * 当前时间
     */
    private String currentDateStr;


    public CustomizeCommentGenerator() {
        currentDateStr = (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(new Date());
        annotations = new LinkedHashSet<Annotations>(Annotations.values().length);
    }

    @Override
    public void addConfigurationProperties(Properties properties) {
        annotations.add(Annotations.DATA);
        for (String stringPropertyName : properties.stringPropertyNames()) {
            if (stringPropertyName.contains(".")) {
                continue;
            }
            if (!Boolean.parseBoolean(properties.getProperty(stringPropertyName))) {
                continue;
            }
            Annotations annotation = Annotations.getValueOf(stringPropertyName);
            if (annotation == null) {
                continue;
            }

            String optionsPrefix = stringPropertyName + ".";
            for (String propertyName : properties.stringPropertyNames()) {
                if (!propertyName.startsWith(optionsPrefix)) {
                    continue;
                }
                String propertyValue = properties.getProperty(propertyName);
                annotation.appendOptions(propertyName, propertyValue);
                annotations.add(annotation);
                annotations.addAll(Annotations.getDependencies(annotation));
            }
            annotations.add(annotation);
        }
        author = properties.getProperty("author");
    }

    @Override
    public void addFieldComment(Field field, IntrospectedTable introspectedTable, IntrospectedColumn introspectedColumn) {
        String remarks = introspectedColumn.getRemarks();
        field.addJavaDocLine("/**");
        field.addJavaDocLine(" * " + remarks);
        field.addJavaDocLine(" */");
    }

    @Override
    public void addFieldComment(Field field, IntrospectedTable introspectedTable) {

    }

    @Override
    public void addModelClassComment(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        String remarks = introspectedTable.getRemarks();
        topLevelClass.addJavaDocLine("/**");
        topLevelClass.addJavaDocLine(" * " + remarks);
        topLevelClass.addJavaDocLine(" * @author   " + author);
        topLevelClass.addJavaDocLine(" * @date   " + currentDateStr);
        topLevelClass.addJavaDocLine(" */");
        addClassAnnotation(topLevelClass);
    }

    private void addClassAnnotation(TopLevelClass topLevelClass) {
        for (Annotations annotation : annotations) {
            topLevelClass.addImportedType(annotation.javaType);
            topLevelClass.addAnnotation(annotation.asAnnotation());
        }
    }

    @Override
    public void addClassComment(InnerClass innerClass, IntrospectedTable introspectedTable) {

    }

    @Override
    public void addClassComment(InnerClass innerClass, IntrospectedTable introspectedTable, boolean b) {

    }

    @Override
    public void addEnumComment(InnerEnum innerEnum, IntrospectedTable introspectedTable) {

    }

    @Override
    public void addGetterComment(Method method, IntrospectedTable introspectedTable, IntrospectedColumn introspectedColumn) {

    }

    @Override
    public void addSetterComment(Method method, IntrospectedTable introspectedTable, IntrospectedColumn introspectedColumn) {

    }

    @Override
    public void addGeneralMethodComment(Method method, IntrospectedTable introspectedTable) {

    }

    @Override
    public void addJavaFileComment(CompilationUnit compilationUnit) {

    }

    @Override
    public void addComment(XmlElement xmlElement) {

    }

    @Override
    public void addRootComment(XmlElement xmlElement) {

    }

    @Override
    public void addGeneralMethodAnnotation(Method method, IntrospectedTable introspectedTable, Set<FullyQualifiedJavaType> set) {

    }

    @Override
    public void addGeneralMethodAnnotation(Method method, IntrospectedTable introspectedTable, IntrospectedColumn introspectedColumn, Set<FullyQualifiedJavaType> set) {

    }

    @Override
    public void addFieldAnnotation(Field field, IntrospectedTable introspectedTable, Set<FullyQualifiedJavaType> set) {

    }

    @Override
    public void addFieldAnnotation(Field field, IntrospectedTable introspectedTable, IntrospectedColumn introspectedColumn, Set<FullyQualifiedJavaType> set) {

    }

    @Override
    public void addClassAnnotation(InnerClass innerClass, IntrospectedTable introspectedTable, Set<FullyQualifiedJavaType> set) {

    }
}
