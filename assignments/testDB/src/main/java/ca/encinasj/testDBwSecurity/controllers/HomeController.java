package ca.encinasj.testDBwSecurity.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String index(Model model) {
        return "index";
    }

    @GetMapping("/secure")
    public String secure() {
        return "secure/index";
    }
    @GetMapping("/login")
    public String login() {
        return "login";
    }
}
