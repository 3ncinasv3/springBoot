package ca.sheridancollege.ex41_thymeleaf.controllers;

import ca.sheridancollege.ex41_thymeleaf.beans.Course;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Controller
public class CourseController {
  List<Course> courseList = new CopyOnWriteArrayList<Course>();

  @GetMapping("/")
  public String index(Model model) {
    return "index";
  }
  @PostMapping("/addCourse")
  public String formPost(Model model, @RequestParam String prefix,
  @RequestParam String code,
  @RequestParam String name,
  @RequestParam Long id) {
    Course course = new Course(prefix, code, name, id);
    courseList.add(course);
    model.addAttribute("courseList", courseList);
    return "index";
  }
}
