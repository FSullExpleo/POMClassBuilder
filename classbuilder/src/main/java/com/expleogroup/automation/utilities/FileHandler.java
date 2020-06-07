package com.expleogroup.automation.utilities;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import com.expleogroup.automation.classbuilder.controls.ControlProperties;

public class FileHandler {

  public static List<ControlProperties> readControlsFromCSV(String fileName) {
    List<ControlProperties> controlProperties = new ArrayList<ControlProperties>();
    Path pathToFile = Paths.get(fileName);

    try (BufferedReader br = Files.newBufferedReader(pathToFile, StandardCharsets.US_ASCII)) {
      String columns = br.readLine();
      String line = br.readLine();
      while (line != null) {
        String[] properties = line.split(",");
        controlProperties.add(new ControlProperties(properties));
        line = br.readLine();
      }

    } catch (IOException ioe) {
      ioe.printStackTrace();
    }

    return controlProperties;
  }


  public static void writeStringToCSV(String fileName, String output) {
    Path pathToFile = Paths.get(fileName);

    File file = new File(fileName);
    FileWriter fr;
    try {
      fr = new FileWriter(file, true);
      BufferedWriter bw = new BufferedWriter(fr);
      bw.append(output + "\n");
      System.out.println("Printing " + output);
      bw.close();
      fr.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public static void writeControlsToCSV(String fileName, List<ControlProperties> controlProperties) {
    Path pathToFile = Paths.get(fileName);

    File file = new File(fileName);
    FileWriter fr;
    try {
      fr = new FileWriter(file, true);
      BufferedWriter bw = new BufferedWriter(fr);
      for (ControlProperties cp : controlProperties) {
        bw.append(cp.getPropertiesAsString());
        System.out.println("Printing " + cp.getPropertiesAsString());
      }
      bw.append("\n");
      bw.close();
      fr.close();

    } catch (IOException e) {
      e.printStackTrace();
    }
  }

}
