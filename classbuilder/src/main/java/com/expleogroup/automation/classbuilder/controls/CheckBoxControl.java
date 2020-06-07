package com.expleogroup.automation.classbuilder.controls;

import java.util.List;
import com.expleogroup.automation.classbuilder.MethodBuilder;

public class CheckBoxControl extends Control implements IControlBuilder {

  public CheckBoxControl(ControlProperties properties) {
    super(properties);
  }

  public List<String> getAllMethods() {
    allMethods.clear();
    allMethods.addAll(getIsDisplayedMethod());
    allMethods.addAll(getIsEnabledMethod());
    allMethods.addAll(getClickMethod());
    return allMethods;
  }

  private List<String> getClickMethod() {
    return MethodBuilder.buildClickMethod(this.properties);
  }
}
