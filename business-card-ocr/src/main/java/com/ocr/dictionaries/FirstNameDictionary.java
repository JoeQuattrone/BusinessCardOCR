package com.ocr.dictionaries;

import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Component;

// In-memory database of the 1,900 most common male & female first names
// source: https://namecensus.com
@Component
public class FirstNameDictionary extends Dictionary {

  // TODO: change to private before shipping
  public static final Map<String, Boolean> names = new HashMap<>();

  static {
    writeFileToMap(names, "com/firstNames.txt");
  }

  @Override
  public Boolean get(final String name) {
    return names.get(name.toUpperCase());
  }
}
