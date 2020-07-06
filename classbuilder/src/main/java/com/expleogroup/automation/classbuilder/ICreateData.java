package com.expleogroup.automation.classbuilder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

public interface ICreateData {
	public static ArrayList<String> radioButton = new ArrayList<String>();
	public static ArrayList<String> csvArray = new ArrayList<String>();
	public static List<List<String>> rows=new ArrayList<>();;
	public static ArrayList<String> findByMap = new ArrayList<String>();
	public static ArrayList<String> Title = new ArrayList<String>();
	public static HashMap<String, String> inputRadioButtonMap = new HashMap<String, String>();
	public static HashMap<String, String> actualHashMap = new HashMap<String, String>();
	public static HashMap<String, String> controlMap = new HashMap<String, String>();
	public static ArrayList<String> buttonMap = new ArrayList<String>();
	public static HashMap<String, String> inputButtonMap = new HashMap<String, String>();
	public static LinkedHashMap<String, ArrayList<String>> htmlElementmap = new LinkedHashMap<String, ArrayList<String>>();
	public static String mainUrl = "https://aib.ie";
}