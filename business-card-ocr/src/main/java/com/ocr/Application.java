package com.ocr;

import com.ocr.dictionaries.FirstNameDictionary;
import com.ocr.dictionaries.LastNameDictionary;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {

  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }

  @Bean
  public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
    return args -> {
      // final LastNameDictionary dict = new LastNameDictionary();
      // System.out.println(dict.getNames().toString());

      System.out.println(LastNameDictionary.names.toString());
      System.out.println(FirstNameDictionary.names.toString());
    };
  }
}
