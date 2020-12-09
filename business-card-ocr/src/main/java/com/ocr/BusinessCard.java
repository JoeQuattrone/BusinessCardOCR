package com.ocr;

public class BusinessCard {
  private String info;

  public BusinessCard(final String info) {
    this.info = replaceNewLines(info);
  }

  public BusinessCard() {}

  public String getInfo() {
    return info;
  }

  public void setInfo(final String info) {
    this.info = info;
  }

  private String replaceNewLines(final String info) {
    return info.replace("\n", " ");
  }
}
