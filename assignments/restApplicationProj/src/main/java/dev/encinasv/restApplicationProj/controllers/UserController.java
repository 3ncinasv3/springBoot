package dev.encinasv.restApplicationProj.controllers;

import dev.encinasv.restApplicationProj.database.DatabaseAccess;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {
    final DatabaseAccess da;

    public UserController(DatabaseAccess da) {
        this.da = da;
    }
    @GetMapping("/secure/user/account")
    public String account() {
        return "/secure/user/account";
    }

}
