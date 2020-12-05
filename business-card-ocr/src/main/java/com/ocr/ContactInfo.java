package com.ocr;

public class ContactInfo {
  private String name;
  private String phoneNumber;
  private String emailAddress;

  ContactInfo(final String name, final String phoneNumber, final String emailAddress) {
    this.name = name;
    this.phoneNumber = phoneNumber;
    this.emailAddress = emailAddress;
  }

  public String getName(final String text) {
    return name;
  }

  public String getPhoneNumber(final String text) {
    return phoneNumber;
  }

  public String getEmailAddress(final String text) {
    return emailAddress;
  }
}
