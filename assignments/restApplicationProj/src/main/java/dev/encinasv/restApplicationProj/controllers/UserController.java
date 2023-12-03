package dev.encinasv.restApplicationProj.controllers;

import dev.encinasv.restApplicationProj.database.DatabaseAccess;
import org.springframework.stereotype.Controller;

@Controller
public class UserController {
    final DatabaseAccess da;

    public UserController(DatabaseAccess da) {
        this.da = da;
    }

    // Landing page logic




}
