package com.expleogroup.automation.classbuilder;

import java.util.ArrayList;
import java.util.List;

import org.omg.CosNaming.NamingContextPackage.NotFound;

import com.expleogroup.automation.classbuilder.controls.ControlProperties;

public class FindByBuilder extends ControlProperties {
	
	  protected ControlProperties properties;
	  public FindByBuilder(ControlProperties properties) {
	    super(properties);
	  }
public List<String> getFindByDeclaration(ControlProperties properties) {
	List<String> signature = null;
	 String Identifier= properties.getHow();
     switch (Identifier) {
      case "id":
        signature = getFindByIdDeclaration(properties);
        break;
      case "class":
        signature = getFindByClassDeclaration(properties);
        break;
      case "for":
        signature = getFindByForDeclaration(properties);
        break;
      case "Xpath":
          signature = getFindByXpathDeclaration(properties);
          break;
       default:
       System.out.println("Not Found");;

    }

    return signature;
  }

  private  List<String> getFindByClassDeclaration(ControlProperties properties) {
    return getFindBy(properties, "class");
  }

  private  List<String> getFindByIdDeclaration(ControlProperties properties) {
    return getFindBy(properties, "id");
  }

  private  List<String> getFindByForDeclaration(ControlProperties properties) {
    return getFindBy(properties, "for");
  }
  private List<String> getFindByXpathDeclaration(ControlProperties properties) {
	    return getFindBy(properties, "xpath");
	  }


/*  private  List<String> getFindBy(ControlProperties properties,String how) {
    List<String> signature = new ArrayList<String>();
    signature
        .add("\t" + "@FindBy(" + how + " = \"" + properties.getHow() + "\")");
    signature.add("\t" + "private WebElement "
        + MethodBuilder.getControlNameCamelCase(properties) + ";\n");
    return signature;
  }*/
  // Add by Neeraj
  private  List<String> getFindBy(ControlProperties properties,String how) {
	    List<String> signature = new ArrayList<String>();
	    signature
	        .add("\t" + "@FindBy(how = How."+how + ",Using = \""+properties.getElementType() +"\")\t");
	    signature.add("\t" + "private WebElement "
	        + MethodBuilder.getControlNameCamelCase(properties)+ ";\n");
	    return signature;
	  } 
  //Neeraj added to create xpath.
  public String createXpath(String htmlTag,String idText){
	 
	String xpathtring = ("\t"+".//"+htmlTag+"[@id='" + idText + "'])\t");
	return xpathtring;
  }
}
