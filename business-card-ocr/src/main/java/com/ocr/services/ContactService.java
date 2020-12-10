package com.ocr.services;

import com.ocr.ContactInfo;
import com.ocr.repositories.ContactRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class ContactService {

  private final ContactRepository contactRepository;

  public ContactService(ContactRepository contactRepository) {
    this.contactRepository = contactRepository;
  }

  public List<ContactInfo> findAll() {
    return contactRepository.findAll();
  }

  public void add(final ContactInfo contactInfo) {
    contactRepository.add(contactInfo);
  }

  public void clearAll() {
    contactRepository.clearAll();
  }
}
