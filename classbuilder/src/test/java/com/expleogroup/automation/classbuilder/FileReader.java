package com.expleogroup.automation.classbuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import com.expleogroup.automation.classbuilder.controls.ControlProperties;

public class FileReader {

  static List<ControlProperties> readControlsFromCSV(String fileName) {
    List<ControlProperties> controlProperties = new ArrayList<>();
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

}
