package com.expleogroup.automation.classbuilder;

import java.util.ArrayList;
import java.util.List;
import com.expleogroup.automation.classbuilder.controls.Control;

public class ClassBuilder {

  private static final String END_OF_FILE_MARKER = "}";
  private String classTerminator;
  private String pageName;
  private List<String> classDeclaration = new ArrayList<String>();
  private List<String> constructor = new ArrayList<String>();
  private List<Control> controls = new ArrayList<Control>();

  public ClassBuilder() {}

  public ClassBuilder(String pageName) {
    this.pageName = pageName;
    createClassDeclaration();
    createConstructor();
    createClassTerminator();
  }

  private void createClassDeclaration() {
    classDeclaration.add("public class " + pageName + "(){");
    classDeclaration.add(" ");
  }

  private void createConstructor() {
    constructor.add(pageName + "Page(WebDriver webDriver){");
    constructor.add("this.webDriver = webDriver;");
    constructor.add("}");
  }

  private void createClassTerminator() {
    classTerminator = END_OF_FILE_MARKER;
  }

  public List<String> getClassDeclaration() {
    return classDeclaration;
  }

  public List<String> getConstructor() {
    return constructor;
  }

  public String getClassTerminator() {
    return classTerminator;
  }

  public void addControl(Control control) {
    controls.add(control);
  }

}
