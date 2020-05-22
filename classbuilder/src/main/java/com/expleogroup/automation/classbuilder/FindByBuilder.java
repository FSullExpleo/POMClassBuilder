package com.expleogroup.automation.classbuilder;

import java.util.ArrayList;
import java.util.List;
import com.expleogroup.automation.classbuilder.controls.Control;

public class FindByBuilder {

  public static List<String> getFindByIDDeclaration(Control control) {
    List<String> signature = new ArrayList<String>();
    signature.add("\t" + "@FindBy(id = \"" + control.getControlID() + "\")");
    signature.add(
        "\t" + "private WebElement " + MethodBuilder.getControlNameCamelCase(control.getProperties()) + ";\n");
    return signature;
  }
}
