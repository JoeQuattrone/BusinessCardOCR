package com.ocr;

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
    final String info = card.getInfo().replace("\n", " ");

    log(info);
    final String name = findName(info);
    final String phoneNumber = findPhoneNumber(info);
    final String emailAddress = findPhoneNumber(info);

    return new Contact(name, phoneNumber, emailAddress);
  }

  // Searches for first names first. If found return first name + the word after it.
  // If first name not found, search for last name and return last name + the word before it
  public String findName(final String info) {
    final StringBuilder name = new StringBuilder();
    final String[] words = info.split(" ");
    final Map<Integer, String> firstNameWithIndex = findNameWithIndex(words, true);

    if (!firstNameWithIndex.isEmpty()) {
      return buildFullName(true, words, firstNameWithIndex);
    }

    final Map<Integer, String> lastNameWithIndex = findNameWithIndex(words, false);
    if (!lastNameWithIndex.isEmpty()) {
      return buildFullName(false, words, lastNameWithIndex);
    }
    return UNKNOWN_NAME;
  }

  private Map<Integer, String> findNameWithIndex(
      final String[] words, final boolean findByFirstName) {
    for (int i = 0; i < words.length; i++) {
      final String word = words[i].trim();

      if (isCapitalized(word)) {
        log("Capital word found " + word);

        // null when word is not found in dictionary
        final Boolean isNameFound =
            findByFirstName ? firstNameDictionary.get(word) : lastNameDictionary.get(word);

        if (isNameFound == Boolean.TRUE) {
          log(word + " was found by first name: " + findByFirstName);
          return Collections.singletonMap(i, word);
        }
      }
    }
    return Collections.emptyMap();
  }

  private String buildFullName(
      final boolean foundByFirstName,
      final String[] words,
      final Map<Integer, String> nameWithIndex) {
    final StringBuilder fullName = new StringBuilder();
    final int nameIndex = nameWithIndex.keySet().iterator().next();
    final String name = nameWithIndex.get(nameIndex);

    if (foundByFirstName && nameIndex < words.length - 1) {
      final String lastName = words[nameIndex + 1];

      fullName.append(name);
      fullName.append(" ");
      fullName.append(lastName);

      return fullName.toString();
    } else if (!foundByFirstName && nameIndex > 0) {
      final String firstName = words[nameIndex - 1];

      fullName.append(firstName);
      fullName.append(" ");
      fullName.append(name);

      return fullName.toString();
    }
    return UNKNOWN_NAME;
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

  private static String UNKNOWN_NAME = "Unknown Name";
}
