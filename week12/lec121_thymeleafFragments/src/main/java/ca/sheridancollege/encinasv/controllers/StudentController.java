package ca.sheridancollege.encinasv.controllers;

import ca.sheridancollege.encinasv.beans.Student;
import ca.sheridancollege.encinasv.database.DatabaseAccess;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class StudentController {
    private final DatabaseAccess da;
    final boolean ADMIN = false;
    public StudentController(DatabaseAccess da) {
        this.da = da;
    }
    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("studentList", da.getStudentList());
        model.addAttribute("admin", ADMIN);
        model.addAttribute("student", new Student());
        return "index";
    }
    @GetMapping("/option1")
    public String option1(Model model) {
        model.addAttribute("studentList", da.getStudentList());
        model.addAttribute("admin", ADMIN);
        model.addAttribute("student", new Student());
        return "option1";
    }
    @PostMapping("/insertStudent")
    public String insertStudent(@ModelAttribute Student student, Model model) {
     da.insertStudent(student);
     model.addAttribute("student", new Student());
     model.addAttribute("studentList", da.getStudentList());
     model.addAttribute("admin", ADMIN);
     return "index";
    }
}
