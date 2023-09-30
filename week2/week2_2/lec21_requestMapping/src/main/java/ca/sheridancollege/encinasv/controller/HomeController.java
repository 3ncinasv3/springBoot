package ca.sheridancollege.encinasv.controller;

import ca.sheridancollege.encinasv.beans.Admin;
import ca.sheridancollege.encinasv.beans.Student;
import ca.sheridancollege.encinasv.beans.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;

@Controller
public class HomeController {
  @Autowired
  private Admin admin;
  @Autowired
  private Student student;
  @Autowired
  private Teacher teacher;
  @GetMapping("/")
  public String index() {
    return "index";
  }
  @GetMapping("/working")
  public String working() {
    return "working";
  }

  @GetMapping("/admin")
  public String admin() {
    return "admin";
  }
  @GetMapping("/student")
  public String student() {
    return "student";
  }
  @GetMapping("/teacher")
    public String teacher() {
      return "teacher";
    }

  @PostMapping("/formStudent")
  public String formPost(@RequestParam String firstName, @RequestParam String lastName, @RequestParam String email, @RequestParam Long studentNumber, @RequestParam LocalDate birthday) {
    student.setFirstName(firstName);
    student.setLastName(lastName);
    student.setEmail(email);
    student.setStudentNumber(studentNumber);
    student.setBirthday(birthday);
    System.out.println(student);
    return "working";
  }
  @PostMapping("/formAdmin")
  public String formAdmin(@RequestParam String firstName, @RequestParam String lastName, @RequestParam String email, @RequestParam Long adminNumber, @RequestParam LocalDate birthday) {
    admin.setFirstName(firstName);
    admin.setLastName(lastName);
    admin.setEmail(email);
    admin.setAdminNumber(adminNumber);
    admin.setBirthday(birthday);
    System.out.println(admin);
    return "working";
  }
  @PostMapping("/formTeacher")
  public String formTeacher(@RequestParam String firstName, @RequestParam String lastName, @RequestParam String email, @RequestParam Long teacherNumber, @RequestParam LocalDate birthday) {
    teacher.setFirstName(firstName);
    teacher.setLastName(lastName);
    teacher.setEmail(email);
    teacher.setTeacherNumber(teacherNumber);
    teacher.setBirthday(birthday);
    System.out.println(teacher);
    return "working";
  }

}
