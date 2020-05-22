package com.expleogroup.automation.classbuilder.controls;

import java.util.ArrayList;
import java.util.List;

public interface IControlBuilder {

  List<String> allMethods = new ArrayList<String>();

  public abstract List<String> getAllMethods();

}
