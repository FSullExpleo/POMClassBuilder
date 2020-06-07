package com.expleogroup.automation.classbuilder.controls;

import sun.security.util.PendingException;

/****
 * [22/05 14:44] Neeraj Kumar
 * Edit box
 * Link
 * Button
 * Image, image link, an image button
 * Text area
 * Checkbox
 * Radio button
 * Dropdown list*****/

public class ControlFactory {

  public static Control getControl(ControlProperties properties) {
    switch (properties.getElementType().toLowerCase()) {
      case "text":
        return new TextControl(properties);
      case "button":
        return new ButtonControl(properties);
      case "radio":
        return new RadioControl(properties);
      case "checkbox":
        return new CheckBoxControl(properties);
      case "label":
        return new LabelControl(properties);
      default:
        throw new PendingException();
    }
  }

}
