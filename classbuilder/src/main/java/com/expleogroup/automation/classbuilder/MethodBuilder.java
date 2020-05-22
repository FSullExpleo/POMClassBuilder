package com.expleogroup.automation.classbuilder;

import java.util.ArrayList;
import java.util.List;
import com.expleogroup.automation.classbuilder.controls.ControlProperties;

public class MethodBuilder {

  private static final String METHOD_TERMINATOR = "\t" + "}\n";
  private static final String PLACEHOLDER_FOR_WAIT_FOR_ELEMENT_READY = "\t\t\\\\ \"placeholder for wait for element\"";

  public static String getControlNamePascalCase(ControlProperties properties) {
    String result;
    result = properties.getAlias() + properties.getElementType().substring(0, 1).toUpperCase()
        + properties.getElementType().substring(1);
    return result.substring(0, 1).toUpperCase() + result.substring(1);
  }

  public static String getControlNameCamelCase(ControlProperties properties) {
    return properties.getAlias() + properties.getElementType().substring(0, 1).toUpperCase()
        + properties.getElementType().substring(1);
  }

  public static List<String> buildIsDisplayedMethod(ControlProperties properties) {
    List<String> signature = new ArrayList<String>();
    signature.add("\t" + "public boolean is" + getControlNamePascalCase(properties) + "Displayed(){");
    signature.add("\t\t" + "System.out.println(\"Checking is displayed for " + properties.getAlias() + " "
        + properties.getElementType() + "\");");
    signature.add(PLACEHOLDER_FOR_WAIT_FOR_ELEMENT_READY);
    signature.add("\t\t" + "return " + getControlNameCamelCase(properties) + ".isDisplayed();");
    signature.add(METHOD_TERMINATOR);
    return signature;
  }

  public static List<String> buildIsEnabledMethod(ControlProperties properties) {
    List<String> signature = new ArrayList<String>();
    signature.add("\t" + "public boolean is" + getControlNamePascalCase(properties) + "Enabled(){");
    signature.add("\t\t" + "System.out.println(\"Checking is enabled for " + properties.getAlias() + " "
        + properties.getElementType() + "\");");
    signature.add("\t\t" + "return " + getControlNameCamelCase(properties) + ".isEnabled();");
    signature.add(METHOD_TERMINATOR);
    return signature;
  }

  public static List<String> buildGetTextMethodContent(ControlProperties properties) {
    List<String> signature = new ArrayList<String>();
    signature.add("\t" + "public String get" + getControlNamePascalCase(properties) + "(){");
    signature.add("\t\t" + "System.out.println(\"Getting text of " + properties.getAlias() + " "
        + properties.getElementType() + "\");");
    signature.add("\t\t" + "return " + getControlNameCamelCase(properties) + ".isDisplayed();");
    signature.add(METHOD_TERMINATOR);
    return signature;
  }

  public static List<String> buildClickMethod(ControlProperties properties) {
    List<String> signature = new ArrayList<String>();
    signature.add(
        "\t" + "public " + properties.getTargetPageName() + " click" + getControlNamePascalCase(properties) + "(){");
    signature.add(
        "\t\t" + "System.out.println(\"Clicking " + properties.getAlias() + " " + properties.getElementType() + "\");");
    signature.add("\t\t" + getControlNameCamelCase(properties) + ".click();");
    signature.add("\t\t" + "return;");
    signature.add(METHOD_TERMINATOR);
    return signature;
  }

}
