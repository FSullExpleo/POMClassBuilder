package com.expleogroup.automation.classbuilder.controls;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class WriteCSV {
	public ArrayList<String> dataLines;
/*
	public WriteCSV(ArrayList<String> findByMap) {
		this.dataLines = findByMap;
	}
	
	public String escapeSpecialCharacters(String data) {
	    String escapedData = data.replaceAll("\\R", " ");
	    if (data.contains(",") || data.contains("\"") || data.contains("'")) {
	        data = data.replace("\"", "\"\"");
	        escapedData = "\"" + data + "\"";
	    }
	    return escapedData;
	}
	public String convertToCSV(String[] data) {
	    return Stream.of(data)
	    		.map(this::escapeSpecialCharacters)
	      .collect(Collectors.joining(","));
	}


public void givenDataArray_whenConvertToCSV_thenOutputCreated() throws IOException {
    File csvOutputFile = new File("file:///C:/Users/KumarN/Documents/Projects/POMClassBuilder-master/Newfolder/new.csv");
    try (PrintWriter pw = new PrintWriter(csvOutputFile)) {
        dataLines.stream().map(this::convertToCSV).forEach(pw::println);
     
    }
    assertTrue(csvOutputFile.exists());
}*/
}