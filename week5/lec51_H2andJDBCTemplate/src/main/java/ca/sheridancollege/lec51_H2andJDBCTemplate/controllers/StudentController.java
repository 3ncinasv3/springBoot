package ca.sheridancollege.lec51_H2andJDBCTemplate.controllers;

import ca.sheridancollege.lec51_H2andJDBCTemplate.beans.Student;
import ca.sheridancollege.lec51_H2andJDBCTemplate.database.DatabaseAccess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Controller
public class StudentController {
    List<Student> studentList = new CopyOnWriteArrayList<>();

    @Autowired
    private DatabaseAccess da;
    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("student", new Student());
        model.addAttribute("studentList", studentList);
//        da.insertStudentHardCoded();
        return "index";
    }
    @PostMapping("/insertStudent")
    public String addSong(Model model, @ModelAttribute Student student) {
        model.addAttribute("student", new Student());
        model.addAttribute("studentList", studentList);
        studentList.add(student);

        da.insertStudent(student);
        return "index";
    }

}
