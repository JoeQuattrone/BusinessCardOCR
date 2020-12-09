package com.ocr.dictionaries;

import java.io.File;
import java.net.URL;
import java.util.Map;
import java.util.Scanner;

public abstract class Dictionary {

  static void writeFileToMap(final Map<String, Boolean> names, final String fileName) {
    try {
      URL resource =
          com.ocr.dictionaries.LastNameDictionary.class.getClassLoader().getResource(fileName);
      File file = new File(resource.toURI());

      final Scanner scanner = new Scanner(file);

      while (scanner.hasNext()) {
        names.put(scanner.next(), Boolean.TRUE);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
