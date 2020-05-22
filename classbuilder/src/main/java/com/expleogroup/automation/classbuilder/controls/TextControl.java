package com.expleogroup.automation.classbuilder.controls;

import java.util.List;
import com.expleogroup.automation.classbuilder.MethodBuilder;

public class TextControl extends Control implements IControlBuilder {

  public TextControl(ControlProperties properties) {
    super(properties);
  }

  public List<String> getAllMethods() {
    allMethods.clear();
    allMethods.addAll(getIsDisplayedMethod());
    allMethods.addAll(getIsEnabledMethod());
    allMethods.addAll(getGetTextMethodContent());
    return allMethods;
  }

  public List<String> getGetTextMethodContent() {
    return MethodBuilder.buildGetTextMethodContent(properties);
  }

}
