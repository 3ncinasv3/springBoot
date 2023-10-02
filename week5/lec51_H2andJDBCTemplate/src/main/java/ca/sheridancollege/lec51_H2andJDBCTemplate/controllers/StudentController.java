package ca.sheridancollege.lec51_H2andJDBCTemplate.controllers;

import ca.sheridancollege.lec51_H2andJDBCTemplate.database.DatabaseAccess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class StudentController {

    @Autowired
    private DatabaseAccess da;
    @GetMapping("/")
    public String index(Model model) {
        da.insertStudentHardCoded();
        return "index";
    }
}
