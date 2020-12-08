package com.ocr;

import org.springframework.stereotype.Service;

@Service
public class BusinessCardParser {

  public Contact buildContact(BusinessCard card) {
    final String info = card.getInfo();
    final String name = findName(info);
    final String phoneNumber = findPhoneNumber(info);
    final String emailAddress = findPhoneNumber(info);

    final Contact contact = new Contact(name, phoneNumber, emailAddress);

    return contact;
  }

  public String findName(final String info) {
    return findNameByLastName(info);
  }

  public String findNameByLastName(final String info) {
    // sort through text. find capitalized letters. Look up in Last name diction. if lookup != null.
    // Return lookup + word before it
    return "";
  }

  public String findNameByFirstName(final String info) {
    return "";
  }

  public String findPhoneNumber(final String info) {
    return "301-893-3334";
  }

  public String findEmailAddress(final String info) {
    return "test@test.com";
  }
}
