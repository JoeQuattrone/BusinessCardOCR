package com.ocr.controllers;

import com.ocr.BusinessCard;
import com.ocr.BusinessCardParser;
import com.ocr.Contact;
import com.ocr.services.ContactService;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {

  private final BusinessCardParser businessCardParser;
  private final ContactService contactService;

  public HomeController(BusinessCardParser businessCardParser, ContactService contactService) {
    this.businessCardParser = businessCardParser;
    this.contactService = contactService;
  }

  @RequestMapping(value = "/", method = RequestMethod.GET)
  public String getHome(ModelMap model) {
    model.clear();

    BusinessCard card = new BusinessCard();
    model.addAttribute("businessCard", card);

    final List<Contact> existingContacts = contactService.findAll();
    model.addAttribute("existingContacts", existingContacts);
    return "home";
  }

  @RequestMapping(value = "/save", method = RequestMethod.POST)
  public String sendCard(
      final BusinessCard card, final BindingResult bindingResult, final ModelMap model) {
    if (bindingResult.hasErrors()) {
      System.out.println(bindingResult.getAllErrors().toString());
    }

    System.out.println(card.getInfo());
    final Contact contact = businessCardParser.buildContact(card);
    contactService.add(contact);
    model.addAttribute("contactInfo", contact);
    return "redirect:/";
  }

  @RequestMapping(value = "/clear", method = RequestMethod.GET)
  public String clearContacts() {
    contactService.clearAll();
    return "redirect:/";
  }
}
