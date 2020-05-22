package com.expleogroup.automation.classbuilder;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import com.expleogroup.automation.classbuilder.controls.ButtonControl;
import com.expleogroup.automation.classbuilder.controls.Control;
import com.expleogroup.automation.classbuilder.controls.ControlProperties;
import com.expleogroup.automation.classbuilder.controls.LabelControl;
import com.expleogroup.automation.classbuilder.controls.TextControl;

/**
 * Unit tests for page object builder
 */
public class ClassBuilderTests {

  String pageName;
  ClassBuilder classBuilder;

  @Before
  public void setUp() {
    pageName = "Login";
    classBuilder = new ClassBuilder(pageName);
  }

  @Test
  public void testCanGenerateHeader() {

    List<String> expected = new ArrayList<String>();
    expected.add("public class " + pageName + "(){");
    expected.add(" ");

    List<String> actual = classBuilder.getClassDeclaration();
    assertThat(actual, is(expected));
  }

  @Test
  public void testCanGenerateContructor() {

    List<String> expected = new ArrayList<String>();
    expected.add(pageName + "Page(WebDriver webDriver){");
    expected.add("this.webDriver = webDriver;");
    expected.add("}");

    List<String> actual = classBuilder.getConstructor();
    assertThat(actual, is(expected));
  }

  @Test
  public void testCanGenerateClassFooter() {
    String actual = classBuilder.getClassTerminator();
    Assert.assertEquals("Did not return correct class termininator: ", "}", actual);
  }

  @Test
  public void testCanDeclareControlFindByID() {

    ControlProperties properties =
        new ControlProperties("txt_username", "id", "username", "text", "LoginPage", "LoginPage");
    Control control = new TextControl(properties);
    classBuilder.addControl(control);
    List<String> expected = new ArrayList<String>();

    expected.add("\t" + "@FindBy(id = \"" + control.getProperties().getIdentifier() + "\")");
    expected.add(
        "\t" + "private WebElement " + MethodBuilder.getControlNameCamelCase(properties) + ";\n");

    List<String> actual = FindByBuilder.getFindByDeclaration(control);
    Assert.assertEquals("Did not declare variable with FindBy(id): ", expected.toString(),
        actual.toString());
  }

  @Test
  public void testCanCreateIsDisplayedMethod() {

    ControlProperties properties =
        new ControlProperties("txt_username", "id", "username", "text", "LoginPage", "LoginPage");
    Control control = new TextControl(properties);
    List<String> expected = new ArrayList<String>();

    expected.add("\t" + "public boolean is" + MethodBuilder.getControlNamePascalCase(properties)
        + "Displayed(){");
    expected.add("\t\t" + "System.out.println(\"Checking is displayed for " + "username" + " "
        + "text" + "\");");
    expected.add("\t\t\\\\ \"placeholder for wait for element\"");
    expected.add(
        "\t\t" + "return " + MethodBuilder.getControlNameCamelCase(properties) + ".isDisplayed();");
    expected.add("\t" + "}\n");

    Assert.assertEquals("Did not create isDisplayed method: ", expected.toString(),
        control.getIsDisplayedMethod().toString());
  }

  @Test
  public void testCanCreateIsEnabledMethod() {

    ControlProperties properties =
        new ControlProperties("txt_username", "id", "username", "text", "LoginPage", "LoginPage");
    Control control = new TextControl(properties);
    classBuilder.addControl(control);
    List<String> expected = new ArrayList<String>();

    expected.add("\t" + "public boolean is" + MethodBuilder.getControlNamePascalCase(properties)
        + "Enabled(){");
    expected.add("\t\t" + "System.out.println(\"Checking is enabled for " + "username" + " "
        + "text" + "\");");
    expected.add("\t\t\\\\ \"placeholder for wait for element\"");
    expected.add(
        "\t\t" + "return " + MethodBuilder.getControlNameCamelCase(properties) + ".isEnabled();");
    expected.add("\t" + "}\n");

    Assert.assertEquals("Did not create isEnabled method: ", expected.toString(),
        control.getIsEnabledMethod().toString());
  }


  @Test
  public void createProject() {

    List<Control> controls = new ArrayList<Control>();
    controls.add(new TextControl(new ControlProperties("txt_username", "class", "username", "text",
        "LoginPage", "LoginPage")));
    controls.add(new TextControl(
        new ControlProperties("txt_password", "id", "password", "text", "LoginPage", "LoginPage")));
    controls.add(new ButtonControl(
        new ControlProperties("btn_login", "id", "login", "button", "LoginPage", "LandingPage")));
    controls.add(new ButtonControl(
        new ControlProperties("btn_logout", "id", "logout", "button", "LandingPage", "LoginPage")));
    controls.add(new ButtonControl(new ControlProperties("btn_checkout", "id", "checkout", "button",
        "LandingPage", "CheckoutPage")));
    controls.add(new LabelControl(
        new ControlProperties("btn_login", "for", "login", "label", "LoginPage", "LoginPage")));

    Map<String, List<Control>> classBuilders = new HashMap<String, List<Control>>();

    for (Control control : controls) {
      // System.out.println("Control: " + control.getProperties().getAlias());
      String key = control.getProperties().getCurrentPageName();
      if (classBuilders.containsKey(key)) {
        List<Control> existingControls = classBuilders.get(key);
        existingControls.add(control);
        classBuilders.remove(key);
        classBuilders.put(key, existingControls);
      } else {
        classBuilders.put(key, new ArrayList<Control>(Arrays.asList(control)));
      }
    }

    for (Control control : controls) {
      String key = control.getProperties().getTargetPageName();
      if (!classBuilders.containsKey(key)) {
        classBuilders.put(key, new ArrayList<Control>());
      }
    }

    for (Map.Entry<String, List<Control>> classBuilder : classBuilders.entrySet()) {
      // System.out.println("Entry: " + classBuilder.getKey());
      ClassBuilder outputClass = new ClassBuilder(classBuilder.getKey());
      List<String> outputContent = outputClass.getClassDeclaration();

      for (Control control : classBuilder.getValue()) {
        outputContent.addAll(FindByBuilder.getFindByDeclaration(control));
      }

      for (Control control : classBuilder.getValue()) {
        outputContent.addAll(control.getAllMethods());
      }

      outputContent.add(outputClass.getClassTerminator());
      for (String row : outputContent) {
        System.out.println(row);
      }

    }

  }

}
