package com.ocr.dictionaries;

import java.io.File;
import java.net.URL;
import java.util.Scanner;
import java.util.Set;

public abstract class Dictionary {

  static void writeFileToMemory(final Set<String> names, final String fileName) {
    try {
      URL resource = Dictionary.class.getClassLoader().getResource(fileName);
      File file = new File(resource.toURI());

      final Scanner scanner = new Scanner(file);

      while (scanner.hasNext()) {
        names.add(scanner.next());
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public abstract boolean get(String name);
}
