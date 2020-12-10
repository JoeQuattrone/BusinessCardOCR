package com.ocr.repositories;

import com.ocr.ContactInfo;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public class ContactRepository {

  private final List<ContactInfo> contactInfoList = new ArrayList<>();

  public ContactRepository() {
    super();
  }

  public List<ContactInfo> findAll() {
    return this.contactInfoList;
  }

  public void add(final ContactInfo contactInfo) {
    this.contactInfoList.add(contactInfo);
  }

  public void clearAll() {
    this.contactInfoList.clear();
  }
}
