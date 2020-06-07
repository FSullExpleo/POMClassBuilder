package com.expleogroup.automation.classbuilder;

import com.expleogroup.automation.classbuilder.controls.ButtonControl;
import com.expleogroup.automation.classbuilder.controls.CheckBoxControl;
import com.expleogroup.automation.classbuilder.controls.Control;
import com.expleogroup.automation.classbuilder.controls.ControlProperties;
import com.expleogroup.automation.classbuilder.controls.LabelControl;
import com.expleogroup.automation.classbuilder.controls.RadioControl;
import com.expleogroup.automation.classbuilder.controls.TextControl;
import sun.security.util.PendingException;

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
