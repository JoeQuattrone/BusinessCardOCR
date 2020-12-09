package com.ocr;

import com.ocr.dictionaries.Dictionary;
import com.ocr.dictionaries.FirstNameDictionary;
import com.ocr.dictionaries.LastNameDictionary;
import java.util.Collections;
import java.util.Map;
import org.springframework.stereotype.Service;

@Service
public class BusinessCardParser {

  private final FirstNameDictionary firstNameDictionary;
  private final LastNameDictionary lastNameDictionary;

  public BusinessCardParser(
      FirstNameDictionary firstNameDictionary, LastNameDictionary lastNameDictionary) {
    this.firstNameDictionary = firstNameDictionary;
    this.lastNameDictionary = lastNameDictionary;
  }

  public Contact buildContact(BusinessCard card) {
    final String info = card.getInfo();
    final String name = findName(info);
    final String phoneNumber = findPhoneNumber(info);
    final String emailAddress = findPhoneNumber(info);

    return new Contact(name, phoneNumber, emailAddress);
  }

  public String findName(final String info) {
    final StringBuilder name = new StringBuilder();
    final String[] words = info.split(" ");
    final Map<Integer, String> lastNameWithIndex = findFirstOrLastName(words, lastNameDictionary);
    if (lastNameWithIndex.size() == 0) {
      return "Unknown Name";
    }

    final int lastNameIndex = lastNameWithIndex.keySet().iterator().next();
    final String lastName = lastNameWithIndex.get(lastNameIndex);

    // can throw index out of bounds exception if lastname is the first word found
    // need to check firstname first
    final String firstName = words[lastNameIndex - 1];

    name.append(firstName);
    name.append(" ");
    name.append(lastName);

    return name.toString();
  }

  // should return a map of name and index the word was found.
  private Map<Integer, String> findFirstOrLastName(
      final String[] words, final Dictionary dictionary) {
    for (int i = 0; i < words.length; i++) {
      final String word = words[i];

      if (isCapitalized(word)) {
        log("Capital words found " + word);
        final Boolean lastNameValue = LastNameDictionary.get(word);
        log("last name value: " + lastNameValue);
        if (lastNameValue != null) {
          return Collections.singletonMap(i, word);
        }
      }
    }
    return Collections.emptyMap();
  }

  private String findFirstName(final String info) {
    return "";
  }

  public String findPhoneNumber(final String info) {
    return "301-893-3334";
  }

  public String findEmailAddress(final String info) {
    return "test@test.com";
  }

  public boolean isCapitalized(final String word) {
    if (word.length() > 1) {
      final char[] chars = word.toCharArray();
      return Character.isUpperCase(chars[0]) && Character.isLowerCase(chars[1]);
    }
    return false;
  }

  public void log(final String message) {
    System.out.println(message);
  }
}
