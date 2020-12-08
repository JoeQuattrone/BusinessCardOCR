package com.ocr;

import com.ocr.services.ContactService;
import java.util.List;

import com.ocr.services.ParsingService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {

  private final ParsingService parsingService;
  private final ContactService contactService;

  public HomeController(ParsingService parsingService, ContactService contactService) {
    this.parsingService = parsingService;
    this.contactService = contactService;
  }

  @RequestMapping(value = "/", method = RequestMethod.GET)
  public String getHome(ModelMap model) {
    model.clear();

    BusinessCard card = new BusinessCard();
    model.addAttribute("businessCard", card);

    final List<Contact> existingContacts = contactService.findAll();
    model.addAttribute("existingContacts", existingContacts);
    model.addAttribute("clearContacts", new Object());
    return "home";
  }

  @RequestMapping(value = "/save", method = RequestMethod.POST)
  public String sendCard(
      final BusinessCard card, final BindingResult bindingResult, final ModelMap model) {
    if (bindingResult.hasErrors()) {
      System.out.println(bindingResult.getAllErrors().toString());
    }

    System.out.println(card.getInfo());
    final Contact contact = parsingService.buildContact(card);
    contactService.add(contact);
    model.addAttribute("contactInfo", contact);
    return "redirect:/";
  }

  @RequestMapping(value = "/clear", method = RequestMethod.POST)
  public String clearContacts(
      @ModelAttribute("clearContacts") final Object clearContacts,
      final BindingResult bindingResult,
      final ModelMap model) {
    if (bindingResult.hasErrors()) {
      System.out.println(bindingResult.getAllErrors().toString());
    }
    contactService.clearAll();
    return "redirect:/";
  }
}
