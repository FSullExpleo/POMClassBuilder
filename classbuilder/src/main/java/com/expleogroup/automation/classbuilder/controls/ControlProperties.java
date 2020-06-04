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
    this.identifier = identifier.trim();
    this.how = how.trim();
    this.alias = alias.trim();
    this.elementType = elementType.trim();
    this.currentPageName = currentPageName.trim();
    this.targetPageName = targetPageName.trim();
  }

  public ControlProperties(ControlProperties properties) {
  
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
