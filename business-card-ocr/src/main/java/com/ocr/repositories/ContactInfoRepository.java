package com.ocr.repositories;

import com.ocr.ContactInfo;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public class ContactInfoRepository {

  private final List<ContactInfo> contactInfoList = new ArrayList<>();

  public ContactInfoRepository() {
    super();
  }

  public List<ContactInfo> finaAll() {
    return this.contactInfoList;
  }

  public void add(final ContactInfo contactInfo) {
    this.contactInfoList.add(contactInfo);
  }
}
