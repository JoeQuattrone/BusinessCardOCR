package com.ocr;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }

  //  @Bean
  //  public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
  //    return args -> {
  //      // final LastNameDictionary dict = new LastNameDictionary();
  //      // System.out.println(dict.getNames().toString());
  //
  //      System.out.println(LastNameDictionary.names.toString());
  //      System.out.println(FirstNameDictionary.names.toString());
  //    };
  //  }
}
