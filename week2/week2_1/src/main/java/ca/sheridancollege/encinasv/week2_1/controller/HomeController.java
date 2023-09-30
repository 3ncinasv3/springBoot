package ca.sheridancollege.encinasv.week2_1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
  @GetMapping("/")
  public String index(Model model) {
    return "index";
  }
  @GetMapping("/sayHi")
  public String sayHi() {
    return "sayHi";
  }

  @GetMapping("/sayBye")
  public String sayBye() {
    return "sayBye";
  }

  @GetMapping("/goodmorning")
  public String goodmorning() {
    return "goodmorning";
  }




}
