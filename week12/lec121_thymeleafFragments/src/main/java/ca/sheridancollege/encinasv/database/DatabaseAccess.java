package ca.sheridancollege.encinasv.database;

import ca.sheridancollege.encinasv.beans.Student;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DatabaseAccess {
    protected final NamedParameterJdbcTemplate jdbc;
    public DatabaseAccess(NamedParameterJdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }
    public void insertStudent(Student student) {
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        String query = "INSERT INTO student(name) VALUES (:name)";
        namedParameters.addValue("name", student.getName());
        jdbc.update(query, namedParameters);
    }
    public List<Student> getStudentList() {
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        String query = "SELECT * FROM student";
        return jdbc.query(query, namedParameters, new BeanPropertyRowMapper<Student>(Student.class));
    }
}
