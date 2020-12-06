package com.ocr;

import com.ocr.repositories.ContactRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class HomeController {

  private final ParsingService parsingService;
  private final ContactRepository contactRepository;

  public HomeController(ParsingService parsingService, ContactRepository contactRepository) {
    this.parsingService = parsingService;
    this.contactRepository = contactRepository;
  }

  @RequestMapping(value = "/", method = RequestMethod.GET)
  public String getHome(Model model) {
    BusinessCard card = new BusinessCard();
    model.addAttribute("businessCard", card);

    final List<Contact> existingContacts = contactRepository.finaAll();
    model.addAttribute("existingContacts", existingContacts);
    return "home";
  }

  @RequestMapping(value = "/save", method = RequestMethod.POST)
  public String sendCard(final BusinessCard card,  final BindingResult bindingResult, final ModelMap model) {
    if (bindingResult.hasErrors()) {
      System.out.println(bindingResult.getAllErrors().toString());
    }

    System.out.println(card.getInfo());
    final Contact contact = new Contact("john smith", "301-893-3334", "test@test.com" );
    contactRepository.add(contact);
    model.addAttribute("contactInfo", contact);
    return "redirect:/";
  }

  //  @ModelAttribute("contactInfo")
  //  public ContactInfo info(ContactInfo info) {
  //    return info;
  //  }
}
