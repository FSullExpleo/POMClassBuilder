package com.expleogroup.automation.classbuilder.controls;

import java.util.List;
import com.expleogroup.automation.classbuilder.MethodBuilder;

public abstract class Control {

  protected ControlProperties properties;

  protected Control(ControlProperties properties) {
    this.properties = properties;
  }

  public ControlProperties getProperties() {
    return this.properties;
  }

  public List<String> getIsDisplayedMethod() {
    return MethodBuilder.buildIsDisplayedMethod(properties);
  }

  public List<String> getIsEnabledMethod() {
    return MethodBuilder.buildIsEnabledMethod(this.properties);
  }

  public abstract List<String> getAllMethods();


}
