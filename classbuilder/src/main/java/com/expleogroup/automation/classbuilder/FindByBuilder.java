package com.expleogroup.automation.classbuilder;

import java.util.ArrayList;
import java.util.List;
import com.expleogroup.automation.classbuilder.controls.Control;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class FindByBuilder {

  public static List<String> getFindByDeclaration(Control control) {

    List<String> signature;
    switch (control.getProperties().getHow()) {
      case "id":
        signature = getFindByIdDeclaration(control);
        break;
      case "class":
        signature = getFindByClassDeclaration(control);
        break;
      case "for":
        signature = getFindByForDeclaration(control);
        break;
      default:
        throw new NotImplementedException();
    }

    return signature;
  }

  private static List<String> getFindByClassDeclaration(Control control) {
    return getFindBy(control, "class");
  }

  private static List<String> getFindByIdDeclaration(Control control) {
    return getFindBy(control, "id");
  }

  private static List<String> getFindByForDeclaration(Control control) {
    return getFindBy(control, "for");
  }

  private static List<String> getFindBy(Control control, String how) {
    List<String> signature = new ArrayList<String>();
    signature
        .add("\t" + "@FindBy(" + how + " = \"" + control.getProperties().getIdentifier() + "\")");
    signature.add("\t" + "private WebElement "
        + MethodBuilder.getControlNameCamelCase(control.getProperties()) + ";\n");
    return signature;
  }
}
