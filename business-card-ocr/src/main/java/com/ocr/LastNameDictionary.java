package com.ocr;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.ResourceLoader;

import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * In-memory database of the 10,000 most common last names
 * source https://www2.census.gov/
 * */
public class LastNameDictionary {
    public static Map<String, Integer> names = new HashMap<>();

//    public Map<String, Integer> getNames() {
//        Map<String, Integer> names = new HashMap<>();
//
//        try {
//            File file = new ClassPathResource("com/names.txt").getFile();
//           final Scanner scanner = new Scanner(file);
//
//            while(scanner.hasNext()) {
//                names.put(scanner.next(), 0);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return names;
//    }

    public static void populateNames(Map<String, Integer> names) {

        try {
            URL resource = LastNameDictionary.class.getClassLoader().getResource("com/names.txt");
            File file = new File(resource.toURI());

            final Scanner scanner = new Scanner(file);

            while(scanner.hasNext()) {
                names.put(scanner.next(), 0);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static {
        populateNames(names);
    }
}
