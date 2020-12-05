package com.ocr;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;

public class LastNameDictionary {

    @Autowired
    ResourceLoader resourceLoader;

    public static Map<String, Integer> names;

    public Map<String, Integer> populateNamesMap() {
        Scanner scanner = null;
        Map<String, Integer> names = new HashMap<>();


        try {
            File file = new ClassPathResource("com/names.txt").getFile();
            scanner = new Scanner(file);
            scanner.useDelimiter("\n");

            while(scanner.hasNext()) {
                names.put(scanner.next(), 0);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return names;
    }
}
