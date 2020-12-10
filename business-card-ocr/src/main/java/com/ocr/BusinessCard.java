package com.ocr;

public class BusinessCard {
  private String info;

  public BusinessCard(final String info) {
    this.info = replaceChars(info);
  }

  public BusinessCard() {}

  public String getInfo() {
    return info;
  }

  public void setInfo(final String info) {
    this.info = replaceChars(info);
  }

  // Removes parenthesis and dashes
  private String replaceChars(final String info) {
    final String withSpaces = replaceNewLinesWithSpaces(info);
    return withSpaces.replaceAll("[\\-()]", "");
  }

  private String replaceNewLinesWithSpaces(final String info) {
    return info.replace("\n", " ");
  }
}
