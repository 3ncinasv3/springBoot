package ca.sheridancollege.lec61_pathVariables.controllers;

import ca.sheridancollege.lec61_pathVariables.beans.Student;
import ca.sheridancollege.lec61_pathVariables.database.DatabaseAccess;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class StudentController {
    @Autowired
    private final DatabaseAccess da;

    public StudentController(DatabaseAccess da) {
        this.da = da;
    }

    @GetMapping("/")
    public String index(@NotNull Model model) {
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
    public String filterStudent(@NotNull Model model, @ModelAttribute Student student) {
        model.addAttribute("studentList", da.filterStudentList(student));
        return "index";
    }

    @PostMapping("/deleteStudent")
    public String deleteStudent(@ModelAttribute Student student) {
        da.deleteStudent(student);
        return "redirect:/";
    }

    @GetMapping("/deleteStudentById/{id}")
    public String deleteStudentById(Model model, @PathVariable Long id) {
        da.deleteStudentById(id);
//        model.addAttribute(student)
        return "forward:/";
    }
    @GetMapping("/editStudentById/{id}")
    public String editStudentById(Model model, @PathVariable Long id) {

        Student student = da.getStudentListById(id).get(0);
        da.deleteStudentById(id);
        model.addAttribute("student", student);
//        model.addAttribute("studentList", da.getStudentList());
        da.insertStudent(student);
//        model.addAttribute("student", student);
        model.addAttribute("studentList", da.getStudentList());

        return "redirect:/";
    }

//    @GetMapping("/editStudentById/{id}")
//    public
}
