package com.ocr;

public class ContactInfo {
  private String name;
  private String phoneNumber;
  private String emailAddress;

  public ContactInfo(final String name, final String phoneNumber, final String emailAddress) {
    this.name = name;
    this.phoneNumber = phoneNumber;
    this.emailAddress = emailAddress;
  }

  public String getName() {
    return name;
  }

  public String getPhoneNumber() {
    return phoneNumber;
  }

  public String getEmailAddress() {
    return emailAddress;
  }
}
