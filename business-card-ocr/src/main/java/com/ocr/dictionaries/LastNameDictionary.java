package com.ocr.dictionaries;

import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Component;

// In-memory database of the 10,000 most common last names
// source: https://www2.census.gov
@Component
public class LastNameDictionary extends Dictionary {
  private static Map<String, Boolean> names = new HashMap<>();

  static {
    writeFileToMap(names, "com/lastNames.txt");
  }

  @Override
  public Boolean get(final String name) {
    return names.get(name.toUpperCase());
  }
}
