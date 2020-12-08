package com.ocr.services;

import com.ocr.BusinessCard;
import com.ocr.Contact;
import org.springframework.stereotype.Service;

@Service
public class ParsingService {

    public Contact buildContact(BusinessCard card) {
        final Contact contact = new Contact("john smith", "301-893-3334", "test@test.com");

        return contact;
    }
}
