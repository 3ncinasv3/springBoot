package ca.sheridancollege.fahadjan.controllers;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import ca.sheridancollege.fahadjan.beans.Student;

@Controller
public class StudentController {

	List<Student> studentList = new CopyOnWriteArrayList<Student>();
	
	@GetMapping("/")
	public String index(Model model) {
		LocalTime myDateObj = LocalTime.now();
		System.out.println("Before formatting: " + myDateObj);
		DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("HH:mm:ss");

		String formattedDate = myDateObj.format(myFormatObj);
		System.out.println("After formatting: " + formattedDate);

		model.addAttribute("serverDate", LocalDate.now());
		model.addAttribute("serverTime", formattedDate);
		
		return "index";
	}
	
	@GetMapping("/getStudent")
	public String getStudent(Model model) {
		
		//model.addAttribute("ID", 5);
		model.addAttribute("name", "Fahad");
		model.addAttribute("id", "1");
		model.addAttribute("marks", "90");
		
//		Student student = new Student(Long.valueOf(5), "Frank", 9);
//		model.addAttribute("student", student);
	  return "getStudent";
		
	}

	@GetMapping("/getStudentList")
	public String getStudentList(Model model) {
		
		studentList.add(new Student(Long.valueOf(1), "Fahad", 100, 95, 25));
		studentList.add(new Student(Long.valueOf(2), "Jan", 100, 90, 25));
		studentList.add(new Student(Long.valueOf(3), "Frank", 50, 88, 25));
		studentList.add(new Student(Long.valueOf(4), "Jim", 66, 88, 25));
		studentList.add(new Student(Long.valueOf(5), "Bill", 76, 75, 25));
		studentList.add(new Student(Long.valueOf(6), "Laura", 82, 82, 25));

		
		model.addAttribute("studentList", studentList);
		
		return "getStudentList";
	}
}
