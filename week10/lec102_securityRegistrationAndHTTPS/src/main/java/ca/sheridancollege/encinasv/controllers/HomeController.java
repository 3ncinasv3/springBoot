package ca.sheridancollege.encinasv.controllers;

import ca.sheridancollege.encinasv.database.DatabaseAccess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {
    private final DatabaseAccess da;

    public HomeController(DatabaseAccess da) {
        this.da = da;
    }

    @GetMapping("/register")
    public String getRegister() {
        return "register";
    }

    @PostMapping("/register")
    public String postRegister(@RequestParam String username, @RequestParam String password) {
        da.addUser(username, password);
        Long userId = da.findUserAccount(username).getUserId();
        da.addRole(userId, Long.valueOf(1));
        return "/index";
    }


    @GetMapping("/")
    public String index() {
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

    @GetMapping("/permission-denied")
    public String permissionDenied() {
        return "/error/permission-denied";
    }

}
