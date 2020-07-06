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

import com.expleogroup.automation.classbuilder.controls.BaseClass;
import com.expleogroup.automation.classbuilder.controls.ControlProperties;

public class CreatePageObjectClass implements ICreateData {

	public static String getTitle(Document doc) {
		Elements dev = doc.getAllElements();
		String Title = doc.title().replaceAll("[^a-zA-Z]+", "") + "Page";
		return Title;
	}

	public static void getPageUrlLink(String url) throws IOException {
		ReadAllUrlLinksFromPage readAllUrlLinksFromPage = new ReadAllUrlLinksFromPage();
		HashMap<String, String> actualHashMap = readAllUrlLinksFromPage.getAllPageUrlLinks(url);

		String urls = null;
		for (Map.Entry<String, String> entry : actualHashMap.entrySet()) {

			urls = entry.getValue().toString();
			System.out.println("Fetching %s..." + urls);
			if ((urls.contains("https://") || urls.contains("http://")) && !urls.endsWith(".pdf")
					&& !urls.endsWith(".png") && urls.contains(mainUrl)) {
				int endOfList;
				htmlElementmap.clear();
				ArrayList<String> classMap = new ArrayList<String>();
				Document doc = Jsoup.connect(urls).get();
				String pageName = getTitle(doc);

				ClassBuilder classBuilder = new ClassBuilder(pageName);
				endOfList = classMap.size();
				classMap.addAll(endOfList, classBuilder.getImportLib());
				endOfList = classMap.size();
				classMap.addAll(endOfList, classBuilder.getClassDeclaration());
				endOfList = classMap.size();
				classMap.addAll(endOfList, classBuilder.getConstructor());
				endOfList = classMap.size();
				classMap.addAll(endOfList, createFindBY(urls));
				/*
				 * endOfList = classMap.size(); classMap.addAll(endOfList,
				 * getRadioButtons(urls));
				 */
				endOfList = classMap.size();
				classMap.add(endOfList, classBuilder.getClassTerminator());
				htmlElementmap.put("ClassAttributes", classMap);
				printClasses(htmlElementmap, pageName, "com.expleogroup.automation.classbuilder;", doc);
				continue;
			} else if ((urls.startsWith("//") || urls.startsWith("/")) && !urls.endsWith(".pdf")
					&& !urls.endsWith(".png") && urls.contains(mainUrl)) {

				int endOfList;
				htmlElementmap.clear();
				ArrayList<String> classMap = new ArrayList<String>();
				urls = mainUrl + urls;
				Document doc = Jsoup.connect(urls).get();

				String pageName = getTitle(doc);
				ClassBuilder classBuilder = new ClassBuilder(pageName);

				endOfList = classMap.size();
				classMap.addAll(endOfList, classBuilder.getImportLib());
				endOfList = classMap.size();
				classMap.addAll(endOfList, classBuilder.getClassDeclaration());
				endOfList = classMap.size();
				classMap.addAll(endOfList, classBuilder.getConstructor());
				endOfList = classMap.size();
				classMap.addAll(endOfList, createFindBY(urls));
				/*
				 * endOfList = classMap.size(); classMap.addAll(endOfList,
				 * getRadioButtons(urls));
				 */
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
		// String url = URL.toString();
		Document document = Jsoup.connect(URL).get();
		Elements Button = document.select("button");
		Elements Input = document.select("input");
		Elements Forms = document.select("form");
		Elements Links = document.select("link");
		Elements Iframe = document.select("iframe");
		inputRadioButtonMap.clear();
		System.out.println("buttons********************************************Start");
		for (Element button : Button) {
			/*
			 * inputRadioButtonMap.put("alias",
			 * button.attr("name").replaceAll("[^a-zA-Z]+", ""));
			 * inputRadioButtonMap.put("elementType",
			 * button.attr("type").replaceAll("[^a-zA-Z]+", ""));
			 * inputRadioButtonMap.put("id",
			 * button.attr("id").replaceAll("[^a-zA-Z]+", ""));
			 */
			if (button.attr("type").contentEquals("radio") && button.hasText()
					&& button.parentNode().nodeName() == "form") {
				ControlProperties controlProperties = new ControlProperties("Radio Button", "id",
						button.attr("type").toString(), "radioButton", mainUrl, URL);

				FindByBuilder findByBuilder = new FindByBuilder(controlProperties);
				findByMap.addAll(findByBuilder.getFindByDeclaration(controlProperties));
				// Write to Csv file
				// getElemetForCsv("RadioButton", "Id", URL, button);

			} else if (button.hasAttr("class") && button.hasText() && !button.hasAttr("id")) {
				if ((button.ownText().isEmpty()) && button.getElementsByTag("span").hasText()) {
					button.getElementsByTag("span").text();
					// ControlProperties controlProperties = new
					// ControlProperties(identifier, how, alias, elementType,
					// currentPageName, targetPageName)
					ControlProperties controlProperties = new ControlProperties("Button", "class",
							button.getElementsByTag("span").text(), "Button", mainUrl, URL);
					FindByBuilder findByBuilder = new FindByBuilder(controlProperties);
					findByMap.addAll(findByBuilder.getFindByDeclaration(controlProperties));
					// Write to Csv file
					String inputText = button.getElementsByTag("span").text();
					getElemetForCsv("Button", "Class", URL, inputText, button.nodeName());
				} else {
					// ControlProperties controlProperties = new
					// ControlProperties(identifier, how, alias, elementType,
					// currentPageName, targetPageName)
					ControlProperties controlProperties = new ControlProperties("Button", "class", button.ownText(),
							"Button", mainUrl, URL);
					FindByBuilder findByBuilder = new FindByBuilder(controlProperties);
					findByMap.addAll(findByBuilder.getFindByDeclaration(controlProperties));
					// Write to Csv file
					String inputText = button.ownText();
					getElemetForCsv("Button", "Class", URL, inputText, button.nodeName());
				}

			}

			else if (button.attr("type").contentEquals("submit") || button.attr("role").contentEquals("button")
					|| button.attr("name").contentEquals("submit")) {
				if (button.hasAttr("id")) {

					// ControlProperties controlProperties = new
					// ControlProperties(identifier, how, alias, elementType,
					// currentPageName, targetPageName)
					ControlProperties controlProperties = new ControlProperties("Button", "id", "CloseButton",
							button.attr("name").toString(), mainUrl, URL);

					FindByBuilder findByBuilder = new FindByBuilder(controlProperties);
					findByMap.addAll(findByBuilder.getFindByDeclaration(controlProperties));
					// Write to Csv file
					// getElemetForCsv("SubmitButton", "Id", URL, button);}
				} else if (button.hasAttr("class")) {
					// ControlProperties controlProperties = new
					// ControlProperties(identifier, how, alias, elementType,
					// currentPageName, targetPageName)
					ControlProperties controlProperties = new ControlProperties("Button", "class", "SubmitButton",
							button.ownText(), mainUrl, URL);

					FindByBuilder findByBuilder = new FindByBuilder(controlProperties);
					findByMap.addAll(findByBuilder.getFindByDeclaration(controlProperties));
					// Write to Csv file
					// getElemetForCsv("SubmitButton", "Id", URL, button);

				}

			} else if ((button.attr("type").contentEquals("button") || button.attr("type").contentEquals("close"))
					&& button.hasAttr("id") && !button.attr("class").isEmpty()) {
				// ControlProperties controlProperties = new
				// ControlProperties(identifier, how, alias, elementType,
				// currentPageName, targetPageName)
				ControlProperties controlProperties = new ControlProperties("Button", "id",
						button.attr("type").toString(), "closeButton", mainUrl, URL);

				FindByBuilder findByBuilder = new FindByBuilder(controlProperties);
				findByMap.addAll(findByBuilder.getFindByDeclaration(controlProperties));
				// Write to Csv file
				// getElemetForCsv("CloseButton", "Id", URL, button);

			} else if (button.attr("type").contentEquals("button") && !button.attr("type").contentEquals("close")
					&& !button.attr("type").contentEquals("cancel") && button.hasAttr("id")) {
				// ControlProperties controlProperties = new
				// ControlProperties(identifier, how, alias, elementType,
				// currentPageName, targetPageName)
				ControlProperties controlProperties = new ControlProperties("Button", "id", "Button",
						button.attr("name").toString(), mainUrl, URL);

				FindByBuilder findByBuilder = new FindByBuilder(controlProperties);
				findByMap.addAll(findByBuilder.getFindByDeclaration(controlProperties));
				// Write to Csv file
				// getElemetForCsv("CloseButton", "Id", URL, button);

			} else if ((button.attr("type").contentEquals("button") || button.attr("type").contentEquals("cancel"))
					&& button.hasAttr("id") && !button.attr("class").isEmpty()) {
				// ControlProperties controlProperties = new
				// ControlProperties(identifier, how, alias, elementType,
				// currentPageName, targetPageName)
				ControlProperties controlProperties = new ControlProperties("Button", "id", button.ownText(),
						"cancelButton", mainUrl, URL);

				FindByBuilder findByBuilder = new FindByBuilder(controlProperties);
				findByMap.addAll(findByBuilder.getFindByDeclaration(controlProperties));
				// Write to Csv file
				// getElemetForCsv("CancelButton", "Id", URL,
				// button.nodeName());
			}
			continue;
		}
		System.out.println("buttons********************************************ends");
	
				System.out.println("Input********************************************Start");
		for (Element input : Input) {

			if (input.attr("type").contentEquals("text")||input.hasAttr("placeholder")) {
			
				// currentPageName, targetPageName)
				ControlProperties controlProperties = new ControlProperties("Input", "Input", input.ownText(),
						"Input", mainUrl, URL);

				FindByBuilder findByBuilder = new FindByBuilder(controlProperties);
				findByMap.addAll(findByBuilder.getFindByDeclaration(controlProperties));
				// Write to Csv file
				String inputText = input.text();
				 getElemetForCsv("Input", "Input", URL, inputText, input.text());
				
				 

			}

			continue;
		}
		System.out.println("Input********************************************ends");
		System.out.println("Links********************************************Start");
		for (Element links : Links) {

			if ((links.attr("type").contentEquals("image/png")||links.attr("type").contentEquals("image/png")) && links.hasAttr("href") && links.hasAttr("rel")) {
				// ControlProperties controlProperties = new
				// ControlProperties(identifier, how, alias, elementType,
				// currentPageName, targetPageName)
				ControlProperties controlProperties = new ControlProperties("partialLink", "linkText",
						links.attr("type").replaceAll("[^a-zA-Z]+", "").toString(), "imagelink", mainUrl, URL);

				FindByBuilder findByBuilder = new FindByBuilder(controlProperties);
				findByMap.addAll(findByBuilder.getFindByDeclaration(controlProperties));
				// Write to Csv file
				// getElemetForCsv("ImageLink", "linkText", URL, links);

			}

			continue;
		}
		System.out.println("Links********************************************ends");
		System.out.println("Forms********************************************Start");
		for (Element forms : Forms) {

			if ((forms.children().contains("input")) && forms.hasAttr("name") && forms.hasAttr("id")) {
				// ControlProperties controlProperties = new
				// ControlProperties(identifier, how, alias, elementType,
				// currentPageName, targetPageName)
				ControlProperties controlProperties = new ControlProperties("Input", "forms",
						forms.attr("id").toString(), "Inputs", mainUrl, URL);

				FindByBuilder findByBuilder = new FindByBuilder(controlProperties);
				findByMap.addAll(findByBuilder.getFindByDeclaration(controlProperties));
				// Write to Csv file
				// getElemetForCsv("Inputs", "Id", URL, forms);

			}

			continue;
		}
		System.out.println("Forms********************************************ends");
		System.out.println("iframe********************************************start");
		for (Element iframe : Iframe) {
			// ControlProperties controlProperties = new
			// ControlProperties(identifier, how, alias, elementType,
			// currentPageName, targetPageName)
			ControlProperties controlProperties = new ControlProperties("iframe", "iframe",
					iframe.attr("name").replaceAll("[^a-zA-Z]+", "").toString(), "iframe", mainUrl, URL);

			FindByBuilder findByBuilder = new FindByBuilder(controlProperties);
			findByMap.addAll(findByBuilder.getFindByDeclaration(controlProperties));
			// Write to Csv file
			// getElemetForCsv("iframe", "iframeText", URL, iframe);

			continue;
		}
		System.out.println("iframe********************************************ends");
		return findByMap;
	}

	private static void getElemetForCsv(String identifier, String how, String URL, String input, String name)
			throws IOException {
		BaseClass baseClass = new BaseClass();
		// new ControlProperties(identifier, how, alias, elementType,
		// currentPageName, targetPageName)
		rows.clear();
		csvArray.clear();
		csvArray.add(identifier);
		csvArray.add(how);
		csvArray.add(input);
		csvArray.add(name.replaceAll("[^a-zA-Z]+", ""));
		csvArray.add(mainUrl);
		csvArray.add(URL);
		rows.add(csvArray);
		baseClass.csvWriter(rows);
	}

	public static void printClasses(HashMap<String, ArrayList<String>> LinkedHashMap, String title, String Package,
			Document doc) throws FileNotFoundException, UnsupportedEncodingException {
		PrintWriter writer = new PrintWriter(
				"C:/Users/KumarN/Documents/Projects/POMClassBuilder-master/Newfolder/" + title + ".java", "UTF-8");
		writer.println("package " + Package);
		writer.println();

		for (Entry<String, ArrayList<String>> entry : LinkedHashMap.entrySet()) {
			System.out.println(entry.getKey());
			writer.println();

			for (String classItem : entry.getValue()) {
				writer.println(classItem);
				System.out.println(classItem);

			}

		}

		writer.close();
		LinkedHashMap.clear();

	}

	public static void main(String[] args) throws IOException {
		try {
			getPageUrlLink(mainUrl);

		} catch (Exception e) {
			e.getMessage();
		}

	}
}
