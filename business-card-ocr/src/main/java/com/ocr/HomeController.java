package com.ocr;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {

  private final ParsingService parsingService;

  public HomeController(ParsingService parsingService) {
    this.parsingService = parsingService;
  }

  @RequestMapping(value = "/", method = RequestMethod.GET)
  public String getHome(Model model) {
    BusinessCard card = new BusinessCard();
    model.addAttribute("businessCard", card);
    return "home";
  }

  @RequestMapping(value = "/home", method = RequestMethod.POST)
  public void sendCard(final BusinessCard card) {
    System.out.println(card.getInfo());
    System.out.println(parsingService.getClass().getName());
  }

  //  @ModelAttribute("contactInfo")
  //  public ContactInfo info(ContactInfo info) {
  //    return info;
  //  }
}
