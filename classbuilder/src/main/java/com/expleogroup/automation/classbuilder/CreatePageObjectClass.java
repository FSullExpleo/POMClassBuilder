package com.expleogroup.automation.classbuilder;
/****
 * Link
 * Edit box
 * Button
 * Image, image link, an image button
 * Text area
 * Checkbox
 * Radio button
 * Dropdown list*****/

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.omg.CosNaming.NamingContextPackage.NotFound;

import com.expleogroup.automation.classbuilder.controls.ButtonControl;
import com.expleogroup.automation.classbuilder.controls.ControlProperties;

public class CreatePageObjectClass extends ReadAllUrlLinksFromPage {
	public static ArrayList<String> radioButton = new ArrayList<String>();
	// public static ArrayList<String> classMap = new ArrayList<String>();
	public static ArrayList<String> findByMap = new ArrayList<String>();
	public static ArrayList<String> Title = new ArrayList<String>();
	public static HashMap<String, String> inputRadioButtonMap = new HashMap<String, String>();
	public static HashMap<String, String> actualHashMap = new HashMap<String, String>();
	public static HashMap<String, String> controlMap = new HashMap<String, String>();
	public static ArrayList<String> buttonMap = new ArrayList<String>();
	public static HashMap<String, String> inputButtonMap = new HashMap<String, String>();
	public static HashMap<String, ArrayList<String>> htmlElementmap = new HashMap<String, ArrayList<String>>();
	public static String mainUrl = "https://www.vhi.ie";

	public static String getTitle(Document doc) {
		Elements dev = doc.getAllElements();
		String Title = doc.title().replaceAll("[^a-zA-Z]+", "") + "Page";
		return Title;
	}

	public static void getPageUrlLink(String url) throws IOException {
		HashMap<String, String> actualHashMap = getAllPageUrlLinks(url);

		String urls = null;
		for (Map.Entry<String, String> entry : actualHashMap.entrySet()) {

			urls = entry.getValue().toString();
			System.out.println("Fetching %s..." + urls);
			if (urls.contains("https://") || urls.contains("http://")) {
				int endOfList;
				htmlElementmap.clear();
				ArrayList<String> classMap = new ArrayList<String>();
				Document doc = Jsoup.connect(urls).get();
				String pageName = getTitle(doc);
				/*
				 * Title.add(pageName); htmlElementmap.put("Title",Title);
				 */
				// System.out.println("Page title" + getTitle(doc));
				ClassBuilder classBuilder = new ClassBuilder(pageName);

				endOfList = classMap.size();
				classMap.addAll(classBuilder.getImportLib());
				endOfList = classMap.size();
				classMap.addAll(classBuilder.getClassDeclaration());
				endOfList = classMap.size();
				classMap.addAll(endOfList, classBuilder.getConstructor());
				endOfList = classMap.size();
				classMap.addAll(endOfList, createFindBY(urls));
				endOfList = classMap.size();
				classMap.addAll(endOfList, getRadioButtons(urls));
				endOfList = classMap.size();
				classMap.addAll(endOfList, createFindBY(urls));
				endOfList = classMap.size();
				classMap.add(endOfList, classBuilder.getClassTerminator());
				htmlElementmap.put("ClassAttributes", classMap);
				printClasses(htmlElementmap, pageName, "com.expleogroup.automation.classbuilder;", doc);
				continue;
			} else if(urls.startsWith("//") || urls.startsWith("/")) {
				urls=mainUrl+urls;
				int endOfList;
				htmlElementmap.clear();
				ArrayList<String> classMap = new ArrayList<String>();
				Document doc = Jsoup.connect(urls).get();
				String pageName = getTitle(doc);
				/*
				 * Title.add(pageName); htmlElementmap.put("Title",Title);
				 */
				// System.out.println("Page title" + getTitle(doc));
				ClassBuilder classBuilder = new ClassBuilder(pageName);

				endOfList = classMap.size();
				classMap.addAll(classBuilder.getImportLib());
				endOfList = classMap.size();
				classMap.addAll(classBuilder.getClassDeclaration());
				endOfList = classMap.size();
				classMap.addAll(endOfList, classBuilder.getConstructor());
				endOfList = classMap.size();
				classMap.addAll(endOfList, createFindBY(urls));
				endOfList = classMap.size();
				classMap.addAll(endOfList, getRadioButtons(urls));
				endOfList = classMap.size();
				classMap.addAll(endOfList, createFindBY(urls));
				endOfList = classMap.size();
				classMap.add(endOfList, classBuilder.getClassTerminator());
				htmlElementmap.put("ClassAttributes", classMap);
				printClasses(htmlElementmap, pageName, "com.expleogroup.automation.classbuilder;", doc);
				continue;
			} else {
				System.out.println("Malformed url");
				continue;
			}
		}

	}

	public static ArrayList<String> createFindBY(String URL) throws IOException {
		String url = URL.toString();
		Document document = Jsoup.connect(URL).get();
		Elements Inputs = document.select("input");
		inputRadioButtonMap.clear();
		System.out.println("Links********************************************Start");
		for (Element input : Inputs) {
			inputRadioButtonMap.put("alias", input.attr("name").replaceAll("[^a-zA-Z]+", ""));
			inputRadioButtonMap.put("elementType", input.attr("type").replaceAll("[^a-zA-Z]+", ""));
			inputRadioButtonMap.put("id", input.attr("id").replaceAll("[^a-zA-Z]+", ""));
			if (input.attr("type").contentEquals("radio") && input.hasText()) {
				inputRadioButtonMap.get("alias");
				inputRadioButtonMap.get("elementType");
	
				ControlProperties controlProperties = new ControlProperties(inputRadioButtonMap.get("alias"), "id",
						inputRadioButtonMap.get("id"), inputRadioButtonMap.get("elementType"), mainUrl, URL);
				//FindByBuilder findByBuilder = new FindByBuilder(controlProperties);
				//findByMap.addAll(findByBuilder.getFindByDeclaration(controlProperties));

			}
			if ((input.attr("type").contentEquals("button") || input.attr("type").contentEquals("submit"))
					&& input.hasAttr("id") && !input.attr("class").isEmpty()) {
				inputRadioButtonMap.get("alias");
				inputRadioButtonMap.get("elementType");
				ControlProperties controlProperties = new ControlProperties(inputRadioButtonMap.get("alias"), "id",
						inputRadioButtonMap.get("id"), inputRadioButtonMap.get("elementType"), mainUrl, URL);
				//FindByBuilder findByBuilder = new FindByBuilder(controlProperties);
				//findByMap.addAll(findByBuilder.getFindByDeclaration(controlProperties));

			}
			continue;
		}
		return findByMap;
	}

	// Return Of the
	public static ArrayList<String> getRadioButtons(String URL) throws IOException {
		String url = URL.toString();
		Document document = Jsoup.connect(URL).get();
		Elements Inputs = document.select("input");
		inputRadioButtonMap.clear();

		System.out.println("Links********************************************Start");
		for (Element input : Inputs) {
			inputRadioButtonMap.put("alias", input.attr("name").replaceAll("[^a-zA-Z]+", ""));
			inputRadioButtonMap.put("elementType", input.attr("type").replaceAll("[^a-zA-Z]+", ""));
			inputRadioButtonMap.put("id", input.attr("id").replaceAll("[^a-zA-Z]+", ""));
			if (input.attr("type").contentEquals("radio") && input.hasText()) {
				inputRadioButtonMap.get("alias");
				inputRadioButtonMap.get("elementType");
				ControlProperties controlProperties = new ControlProperties(inputRadioButtonMap.get("id"),
						inputRadioButtonMap.get("id"), inputRadioButtonMap.get("alias"), "Xpath",
						"https://materializecss.com/radio-buttons.html", URL);
				ButtonControl buttonControl = new ButtonControl(controlProperties);
				radioButton.addAll(buttonControl.getAllMethods());
			}
			if ((input.attr("type").contentEquals("button") || input.attr("type").contentEquals("submit"))
					&& input.hasAttr("id") && !input.attr("class").isEmpty()) {
				inputRadioButtonMap.get("alias");
				inputRadioButtonMap.get("elementType");
				ControlProperties controlProperties = new ControlProperties(inputRadioButtonMap.get("id"), "Xpath",
						inputRadioButtonMap.get("alias"), inputRadioButtonMap.get("id"),
						"https://materializecss.com/radio-buttons.html", url);
				ButtonControl buttonControl = new ButtonControl(controlProperties);
				radioButton.addAll(buttonControl.getAllMethods());

			}
			continue;
		}
		return radioButton;
	}

	public static ArrayList<String> getButtons(String URL) throws IOException {
		Document document = Jsoup.connect(URL).get();
		Elements Inputs = document.select("input");
		buttonMap.clear();
		System.out.println("Links********************************************Start");
		for (Element input : Inputs) {
			if (input.attr("type").contentEquals("button") && input.hasText()) {
				inputButtonMap.put("alias", input.attr("type"));
				inputButtonMap.put("elementType", input.attr("name"));
				
			}
		}
		return buttonMap;
	}

	public static void printClasses(HashMap<String, ArrayList<String>> hashMap, String title, String Package,
			Document doc) throws FileNotFoundException, UnsupportedEncodingException {
		PrintWriter writer = new PrintWriter(
				"C:/Users/KumarN/Documents/Projects/POMClassBuilder-master/Newfolder/" + title + ".java", "UTF-8");
		writer.println("package " + Package);
		writer.println();

		for (Entry<String, ArrayList<String>> entry : hashMap.entrySet()) {
			System.out.println(entry.getKey());
			writer.println();

			for (String classItem : entry.getValue()) {
				writer.println(classItem);
				System.out.println(classItem);
				
			}
			
		}

		writer.close();
		hashMap.clear();

	}

	public static void main(String[] args) throws IOException {
		try {
			getPageUrlLink(mainUrl);
		} catch (Exception e) {
			e.getMessage();
		}

	}

}
