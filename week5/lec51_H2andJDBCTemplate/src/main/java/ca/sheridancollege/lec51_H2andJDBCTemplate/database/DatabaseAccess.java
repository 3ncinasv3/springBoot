package ca.sheridancollege.lec51_H2andJDBCTemplate.database;

import ca.sheridancollege.lec51_H2andJDBCTemplate.beans.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class DatabaseAccess {
    @Autowired
    protected NamedParameterJdbcTemplate jdbc;

    public void insertStudentHardCoded() {
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        String query = "INSERT INTO student(name) VALUES ('FRANK')";
        int rowsAffected = jdbc.update(query, namedParameters);
        if (rowsAffected > 0)
            System.out.println("Hard coded student inserted into database");
    }

    public void insertStudent(Student student) {

        MapSqlParameterSource namedParameters = new MapSqlParameterSource();

        namedParameters.addValue("studentName", student.getName()); // Adding a named parameter
        namedParameters.addValue("studentAddress", student.getAddress());
        namedParameters.addValue("studentPhoneNumber", student.getPhoneNumber());
        namedParameters.addValue("studentDegree", student.getDegree());

        String query = "INSERT INTO student(name, address, phoneNumber, degree) VALUES (:studentName, :studentAddress, :studentPhoneNumber, :studentDegree)"; // Using the named parameter

        int rowsAffected = jdbc.update(query, namedParameters);

        if (rowsAffected > 0) System.out.println("Hard coded student inserted into database");


    }
}