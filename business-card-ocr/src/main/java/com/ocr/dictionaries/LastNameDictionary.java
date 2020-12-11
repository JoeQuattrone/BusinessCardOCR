package com.ocr.dictionaries;

import java.util.HashSet;
import java.util.Set;
import org.springframework.stereotype.Component;

// In-memory database of the 10,000 most common last names
// source: https://www2.census.gov
@Component
public class LastNameDictionary extends Dictionary {
  private static Set<String> names = new HashSet<>();

  static {
    writeFileToMap(names, "com/lastNames.txt");
  }

  @Override
  public boolean get(final String name) {
    return names.contains(name.toUpperCase());
  }
}
