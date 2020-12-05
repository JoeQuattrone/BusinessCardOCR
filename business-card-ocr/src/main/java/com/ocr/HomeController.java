package com.ocr;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {

  @RequestMapping(value = "/", method = RequestMethod.GET)
  public String getHome() {
    return "home";
  }

  @RequestMapping(value = "/", method = RequestMethod.POST)
  public void sendCard(final String info) {
    System.out.println(info);
  }
}
