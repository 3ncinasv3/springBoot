package ca.sheridancollege.lec111_restfulService.controllers;

import ca.sheridancollege.lec111_restfulService.beans.Student;
import ca.sheridancollege.lec111_restfulService.database.DatabaseAccess;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/students")
public class StudentController {
    private final DatabaseAccess da;

    public StudentController(DatabaseAccess da) {
        this.da = da;
    }

    @GetMapping
    public List<Student> getStudentCollection() {
        return da.findAll();
    }

//    @GetMapping(value = "/{id}")
//    public Student getIndividualStudent(@PathVariable Long id) {
//        return da.findById(id);
//    }

    @PostMapping(consumes = "application/json")
    public String postStudent(@RequestBody Student student) {
        return "http://localhost:8080/api/v1/students/" +
                da.save(student);
    }

    @PutMapping(consumes = "application/json")
    public String putStudentCollection(@RequestBody List<Student>
                                               studentList) {
        da.deleteAll();
        da.saveAll(studentList);
        return "Total Records: " + da.count();
    }
}
