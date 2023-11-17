package ca.sheridancollege.encinasv.controllers;

import ca.sheridancollege.encinasv.beans.Phone;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class PhoneController {
    @GetMapping("/")
    public String index(Model model, HttpSession session) {
        // Check if it's a new session
        if (session.isNew()) {
            session.setAttribute("myTest", "New Session");
        } else {
            session.setAttribute("myTest", "Welcome Back");
            session.setAttribute("phones", new ArrayList<Phone>());
        }
        model.addAttribute("phone", new Phone());
        return "index";
    }

    @PostMapping("/insertPhone")
    public String insert(Model model, HttpSession session, @ModelAttribute Phone phone) {
        List<Phone> phones = (List<Phone>) session.getAttribute("phones");

        if (phones == null) {
            phones = new ArrayList<>();
        }

        phones.add(phone);
        session.setAttribute("phones", phones);

        model.addAttribute("phone", new Phone());

        return "index";

    }

    @GetMapping ("/test")
    public String test(Model model, HttpSession session, @ModelAttribute Phone phone) {
        session.invalidate();
        return "index";
    }

}
