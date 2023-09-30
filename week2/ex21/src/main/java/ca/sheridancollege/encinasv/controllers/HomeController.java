package ca.sheridancollege.encinasv.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
  @GetMapping("/")
  public String index() {
    return "index";
  }
  @GetMapping("/hairColour")
  public String hairColour() {
    return "hairColour";
  }
  @GetMapping("/height")
  public String height() {
    return "height";
  }
  @GetMapping("name")
  public String name() {
    return "name";
  }
}
