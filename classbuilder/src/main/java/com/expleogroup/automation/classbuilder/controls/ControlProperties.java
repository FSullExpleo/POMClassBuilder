package com.expleogroup.automation.classbuilder.controls;

public class ControlProperties {
  private String identifier;
  private String how;
  private String alias;
  private String elementType;
  private String currentPageName;
  private String targetPageName;

  public ControlProperties(String identifier, String how, String alias, String elementType, String currentPageName,
      String targetPageName) {
    this.identifier = identifier;
    this.how = how;
    this.alias = alias;
    this.elementType = elementType;
    this.currentPageName = currentPageName;
    this.targetPageName = targetPageName;
  }

  public String getIdentifier() {
    return this.identifier;
  }

  public String getHow() {
    return this.how;
  }

  public String getAlias() {
    return this.alias;
  }

  public String getElementType() {
    return this.elementType;
  }

  public String getCurrentPageName() {
    return this.currentPageName;
  }

  public String getTargetPageName() {
    return this.targetPageName;
  }
}
