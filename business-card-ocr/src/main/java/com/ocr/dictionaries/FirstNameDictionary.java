package com.ocr.dictionaries;

import java.util.HashSet;
import java.util.Set;
import org.springframework.stereotype.Component;

// In-memory database of the 1,900 most common male & female first names
// source: https://namecensus.com
@Component
public class FirstNameDictionary extends Dictionary {
  private static final Set<String> names = new HashSet<>();

  static {
    writeFileToMemory(names, "com/firstNames.txt");
  }

  @Override
  public boolean get(final String name) {
    return names.contains(name.toUpperCase());
  }
}
