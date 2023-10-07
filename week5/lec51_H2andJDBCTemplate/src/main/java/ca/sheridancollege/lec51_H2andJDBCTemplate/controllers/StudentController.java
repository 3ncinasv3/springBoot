package ca.sheridancollege.lec51_H2andJDBCTemplate.controllers;

import ca.sheridancollege.lec51_H2andJDBCTemplate.beans.Student;
import ca.sheridancollege.lec51_H2andJDBCTemplate.database.DatabaseAccess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class StudentController {
    @Autowired
    private DatabaseAccess da;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("student", new Student());
        model.addAttribute("studentList", da.getStudentList());
//        da.insertStudentHardCoded();
        return "index";
    }

    @PostMapping("/insertStudent")
    public String addSong(@ModelAttribute Student student) {
        da.insertStudent(student);
        return "redirect:/";
    }

    @PostMapping("/filterStudent")
    public String filterStudent(Model model, @ModelAttribute Student student) {
        model.addAttribute("studentList", da.filterStudentList(student));
        return "index";
    }

    @PostMapping("/deleteStudent")
    public String deleteStudent(Model model, @ModelAttribute Student student) {
        da.deleteStudent(student);
        model.addAttribute("studentList", da.getStudentList());
        return "redirect:/";
    }
}
