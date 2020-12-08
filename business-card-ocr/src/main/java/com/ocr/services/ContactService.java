package com.ocr.services;

import com.ocr.Contact;
import com.ocr.repositories.ContactRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class ContactService {

  private final ContactRepository contactRepository;

  public ContactService(ContactRepository contactRepository) {
    this.contactRepository = contactRepository;
  }

  public List<Contact> findAll() {
    return contactRepository.findAll();
  }

  public void add(final Contact contact) {
    contactRepository.add(contact);
  }

  public void clearAll() {
    contactRepository.clearAll();
  }
}
