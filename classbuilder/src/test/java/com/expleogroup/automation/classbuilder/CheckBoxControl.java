package com.expleogroup.automation.classbuilder;

import java.util.List;
import com.expleogroup.automation.classbuilder.controls.Control;
import com.expleogroup.automation.classbuilder.controls.ControlProperties;
import com.expleogroup.automation.classbuilder.controls.IControlBuilder;

public class CheckBoxControl extends Control implements IControlBuilder {

  protected CheckBoxControl(ControlProperties properties) {
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
