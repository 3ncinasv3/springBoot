package ca.sheridancollege.lec51_H2andJDBCTemplate.services;

import ca.sheridancollege.lec51_H2andJDBCTemplate.beans.Student;
import ca.sheridancollege.lec51_H2andJDBCTemplate.database.DatabaseAccess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class StudentService {
    private final DatabaseAccess databaseAccess;
    @Autowired
    public StudentService(DatabaseAccess databaseAccess) {
        this.databaseAccess = databaseAccess;
    }

    public void insertStudent(Student student) {
        databaseAccess.insertStudent(student);
    }

    public void deleteStudent(Student student) {
        databaseAccess.deleteStudent(student);
    }

    public List<Student> getStudentList() {
        return databaseAccess.getStudentList();
    }

    public List<Student> filterStudentList(Student student) {
        return databaseAccess.filterStudentList(student);
    }

}
