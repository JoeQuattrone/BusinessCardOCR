package com.ocr;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.ResourceLoader;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class LastNameDictionary {
    public Map<String, Integer> getNames() {
        Map<String, Integer> names = new HashMap<>();

        try {
            File file = new ClassPathResource("com/names.txt").getFile();
           final Scanner scanner = new Scanner(file);

            while(scanner.hasNext()) {
                names.put(scanner.next(), 0);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return names;
    }
}
