package com.ocr;

import com.ocr.dictionaries.FirstNameDictionary;
import com.ocr.dictionaries.LastNameDictionary;
import java.util.Collections;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
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

  public ContactInfo buildContact(BusinessCard card) {
    final String info = card.getInfo();
    final String name = findName(info);
    final String phoneNumber = findPhoneNumber(info);
    final String emailAddress = findEmailAddress(info);

    return new ContactInfo(name, phoneNumber, emailAddress);
  }

  //  Searches for first names using a dictionary of common first names.
  //  If the search finds a first name, return the name with the word after it.
  //  Otherwise search for last names using a dictionary of common last names.
  //  If last name is found return the last name with the word before it.
  public String findName(final String info) {
    final String[] words = info.split(" ");
    final Map<Integer, String> firstNameWithIndex = findNameWithIndex(words, true);

    if (!firstNameWithIndex.isEmpty()) {
      return buildFullName(true, words, firstNameWithIndex);
    }

    final Map<Integer, String> lastNameWithIndex = findNameWithIndex(words, false);
    if (!lastNameWithIndex.isEmpty()) {
      return buildFullName(false, words, lastNameWithIndex);
    }
    log("Name not found for string: " + info);
    return UNKNOWN_NAME;
  }

  private Map<Integer, String> findNameWithIndex(
      final String[] words, final boolean isFindByFirstName) {
    for (int i = 0; i < words.length; i++) {
      final String word = words[i].trim();

      if (isCapitalized(word)) {
        final Boolean isNameFound =
            isFindByFirstName ? firstNameDictionary.get(word) : lastNameDictionary.get(word);

        if (isNameFound) {
          log(word + " was found by first name: " + isFindByFirstName);
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

  //  Searches for numeric words larger then 6 digits and makes sure the word is not preceded by
  // "fax"
  //  then checks to see if digits are in the previous word, if so concat the previous word with the
  //  current word to form the complete phone number.
  //  Otherwise return the current word.
  //  Also prepend the area code if found.
  public String findPhoneNumber(final String info) {
    final String[] words = info.split(" ");
    final Pattern phonePattern = Pattern.compile(NUMERIC_STRING_PATTERN);
    StringBuilder phoneNumber = new StringBuilder();
    boolean isFaxNumber = false;

    int j = 0;
    for (int i = 1; i < words.length - 1; i++) {
      final String word = words[i];
      final String previousWord = words[j].toLowerCase();
      final Matcher matcher = phonePattern.matcher(word);

      if (word.toLowerCase().contains("fax")) {
        isFaxNumber = true;
      }
      if (word.contains("+") && word.length() > 1) {
        phoneNumber.append(word.substring(1));
      }
      if (matcher.find() && word.length() > 6) {
        final Matcher previousWordMatcher = phonePattern.matcher(previousWord);
        if (previousWordMatcher.find()) {
          phoneNumber.append(previousWord.concat(word));
        } else {
          phoneNumber.append(word);
        }
        if (!isFaxNumber) {
          return phoneNumber.toString();
        }
        phoneNumber = new StringBuilder();
        isFaxNumber = false;
      }
      j++;
    }
    log("Phone number not found for string: " + info);
    return UNKNOWN_PHONE_NUMBER;
  }

  public String findEmailAddress(final String info) {
    final String[] words = info.split(" ");

    for (final String word : words) {
      if (word.contains("@")) {
        return word;
      }
    }
    log("Email not found for string: " + info);
    return UNKNOWN_EMAIL;
  }

  private boolean isCapitalized(final String word) {
    if (word.length() > 1) {
      final char[] chars = word.toCharArray();
      return Character.isUpperCase(chars[0]) && Character.isLowerCase(chars[1]);
    }
    return false;
  }

  public void log(final String message) {
    System.out.print(message + "\n");
  }

  public static String UNKNOWN_NAME = "Unknown name";
  public static String UNKNOWN_PHONE_NUMBER = "Unknown phone number";
  public static String UNKNOWN_EMAIL = "Unknown email";

  public static String NUMERIC_STRING_PATTERN = "-?\\d+(\\.\\d+)?";
}
