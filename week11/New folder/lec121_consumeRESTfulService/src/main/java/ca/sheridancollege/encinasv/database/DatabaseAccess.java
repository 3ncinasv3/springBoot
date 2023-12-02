package ca.sheridancollege.encinasv.database;

import ca.sheridancollege.encinasv.beans.Student;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class DatabaseAccess {
    protected final NamedParameterJdbcTemplate jdbc;


    public DatabaseAccess(NamedParameterJdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public List<Student> findAll() {
        MapSqlParameterSource namedParameters = new
                MapSqlParameterSource();
        String query = "SELECT * FROM student";
        return jdbc.query(query, namedParameters, new
                BeanPropertyRowMapper<Student>(Student.class));

    }
    public Long save(Student student) {
        MapSqlParameterSource namedParameters = new
                MapSqlParameterSource();
        KeyHolder generatedKeyHolder = new GeneratedKeyHolder();
        String query = "INSERT INTO student(name, lastName, grade, letterGrade, attended, participated) VALUES(:name, :lastName, :grade, :letterGrade, :attended, :participated)";
        namedParameters.addValue("name", student.getName());
        namedParameters.addValue("lastName", student.getLastName());
        namedParameters.addValue("grade", student.getGrade());
        namedParameters.addValue("letterGrade", student.getLetterGrade());
        namedParameters.addValue("attended", student.getAttended());
        namedParameters.addValue("participated", student.getParticipated());
        jdbc.update(query, namedParameters, generatedKeyHolder);
        return (Long) generatedKeyHolder.getKey();

    }
    public void deleteAll() {
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        String query = "DELETE FROM student";
        jdbc.update(query, namedParameters);
    }

    public void saveAll(List<Student> studentList) {
        for (Student s : studentList) {
            save(s);
        }

    }

    public Long count() {
        MapSqlParameterSource namedParameters = new
                MapSqlParameterSource();
        String query = "SELECT count(*) FROM student";
        return jdbc.queryForObject(query, namedParameters, Long.TYPE);
    }


    public Student findById(Long id) {
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        String query = "SELECT * FROM student WHERE id = :id";
        namedParameters.addValue("id", id);
        return jdbc.queryForObject(query, namedParameters, new
                BeanPropertyRowMapper<Student>(Student.class));
    }
}
