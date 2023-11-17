package ca.sheridancollege.encinasv.controllers;

import ca.sheridancollege.encinasv.beans.Student;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class StudentController {
    @GetMapping("/test")
    public String test(Model model, HttpSession session, @ModelAttribute Student student) {
        // Check if it's a new session
        if (session.isNew()) {
            session.setAttribute("myTest", "New Session");
        } else {
            session.setAttribute("myTest", "Welcome Back");
        }
        model.addAttribute("student", student);
        session.setAttribute("sessionId", session.getId());
        return "test";
    }
    @GetMapping("/retest")
    public String retest(Model model, HttpSession session, @ModelAttribute Student student) {
        session.invalidate();
        return "index";
    }

    @GetMapping("/")
    public String index(Model model, HttpSession session) {
        model.addAttribute("student", new Student());
        return "index";
    }

    @PostMapping("/insertStudent")
    public String insertStudent(HttpSession session, Model model, @ModelAttribute Student student) {
        return "index";
    }

}
