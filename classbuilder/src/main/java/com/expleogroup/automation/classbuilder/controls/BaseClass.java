package com.expleogroup.automation.classbuilder.controls;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BaseClass {

	/*public void csvReader(String pathToCsv) throws IOException {
		File csvFile = new File(pathToCsv);
		if (csvFile.isFile()) {
			BufferedReader csvReader = new BufferedReader(new FileReader(pathToCsv));
			String row;
			while ((row = csvReader.readLine()) != null) {
				String[] data = row.split(",");
				// do something with the data
			}
			csvReader.close();
		}
	}
*/
	public void csvWriter(List<List<String>> rows) throws IOException {
		boolean bool = false;
		FileWriter csvWriter = null;
		File dir = new File("C:/Users/KumarN/Documents/Projects/POMClassBuilder-master/Newfolder/new.csv");
		String pathToCsv = dir.getCanonicalPath() /* + File.separator */;
		try {
			bool = dir.exists();
			if (bool == false) {
				csvWriter = new FileWriter(pathToCsv, true);
				List<String> header = new ArrayList<String>();
				header.add("Identifier");
				header.add("How");
				header.add("Alias");
				header.add("ElementType");
				header.add("CurrentPageName");
				header.add("TargetPageName");

				csvWriter.append(String.join(",", header));
				csvWriter.append("\n");

			} else {
				csvWriter = new FileWriter(pathToCsv,true);
				for (List<String> rowData : rows) {
					csvWriter.append(String.join(",", rowData));
					csvWriter.append("\n");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				csvWriter.flush();
				csvWriter.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
