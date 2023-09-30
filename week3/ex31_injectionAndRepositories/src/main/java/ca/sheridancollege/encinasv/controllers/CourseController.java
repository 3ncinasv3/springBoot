package ca.sheridancollege.encinasv.controllers;

import ca.sheridancollege.encinasv.beans.Course;
import ca.sheridancollege.encinasv.repositories.CourseList;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CourseController {

  public CourseController(CourseList courseList) {
    this.courseList = courseList;
  }

  private CourseList courseList;

  @GetMapping("/")
  public String index(Model Model) {
    courseList.emptyList();
    return "index";
  }
  @PostMapping("/processForm")
  public String formProcess(@RequestParam Long id, @RequestParam String prefix, @RequestParam Long code, @RequestParam String name) {
    Course course = new Course (id, prefix, code, name);
    courseList.getCourseList().add(course);
    System.out.println("***");
    for (Course c: courseList.getCourseList()) {
      System.out.println(c);
    }


    return "index";
  }


}
