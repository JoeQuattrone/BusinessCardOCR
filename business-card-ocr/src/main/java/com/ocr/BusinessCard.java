package com.ocr;

public class BusinessCard {
  private String info;

  public BusinessCard(final String info) {
    this.info = info;
  }

  public BusinessCard() {}

  public String getInfo() {
    return info;
  }

  public void setInfo(final String info) {
    this.info = info;
  }
}
