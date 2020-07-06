package com.expleogroup.automation.classbuilder;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ReadAllUrlLinksFromPage {

  public static HashMap<String, String> UrlLinks = new HashMap<String,String>();


  /*public ReadAllUrlLinksFromPage() {
   
   // radioButton = new HashMap<String,String>();
  }*/


 HashMap<String, String> getAllPageUrlLinks(String URL) throws IOException {
     
    	
        Document document = Jsoup.connect(URL).get();
        Elements urlLinksOnPage = document.select("a[href]");
        int i = 0;
        for (Element page : urlLinksOnPage) {
        	
          if (!page.attr("href").isEmpty()||page.attr("href").contains("https") ) {
        	  String urlString = page.attr("href");
        	  System.out.println("Test   Neeraj"+UrlLinks);
        	 String pageNo="pageNo "+i;
            UrlLinks.put(pageNo,urlString);
            i =i + 1;
          
            continue;
          }
        }
     

    return UrlLinks;
  }

/* public static void main(String[] args) throws IOException {

    new ReadAllUrlLinksFromPage();
	Map<String, String> actualHashMap =ReadAllUrlLinksFromPage.getAllPageUrlLinks("https://www.expleogroup.com/");
    for (Map.Entry <String, String> entry : actualHashMap.entrySet()) {
      System.out.println("Key : " + entry.getKey() + " Value : " + entry.getValue());
    }
  }*/
}