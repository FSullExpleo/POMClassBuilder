package com.expleogroup.automation.classbuilder.controls;

import java.util.List;
import com.expleogroup.automation.classbuilder.MethodBuilder;

public class LabelControl extends Control implements IControlBuilder {

  public LabelControl(ControlProperties properties) {
    super(properties);
  }

  public List<String> getAllMethods() {
    allMethods.clear();
    allMethods.addAll(getIsDisplayedMethod());
    allMethods.addAll(getGetTextMethodContent());
    return allMethods;
  }

  public List<String> getGetTextMethodContent() {
    return MethodBuilder.buildGetTextMethodContent(properties);
  }

}
