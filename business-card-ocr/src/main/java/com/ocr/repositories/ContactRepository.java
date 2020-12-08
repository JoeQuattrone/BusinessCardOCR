package com.ocr.repositories;

import com.ocr.Contact;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public class ContactRepository {

  private final List<Contact> contactList = new ArrayList<>();

  public ContactRepository() {
    super();
  }

  public List<Contact> findAll() {
    return this.contactList;
  }

  public void add(final Contact contact) {
    this.contactList.add(contact);
  }

  public void clearAll() {
    this.contactList.clear();
  }
}
