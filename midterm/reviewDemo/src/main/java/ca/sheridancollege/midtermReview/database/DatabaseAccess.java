package ca.sheridancollege.midtermReview.database;

import ca.sheridancollege.midtermReview.beans.Appointment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DatabaseAccess {
    @Autowired
    protected NamedParameterJdbcTemplate jdbc;

    public void insertAppointment(Appointment appointment) {
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        namedParameters.addValue("appointmentFirstName", appointment.getFirstName());
        namedParameters.addValue("appointmentEmail", appointment.getEmail());
        namedParameters.addValue("appointmentDate", appointment.getAppointmentDate());
        namedParameters.addValue("appointmentTime", appointment.getAppointmentTime());
        String query = "INSERT INTO appointment(firstName, email, appointmentDate, appointmentTime) VALUES (:appointmentFirstName, :appointmentEmail, :appointmentDate, :appointmentTime)";
        jdbc.update(query, namedParameters);
    }

    public List<Appointment> getAllAppointments() {
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        String query = "SELECT * FROM appointment";
        return jdbc.query(query, namedParameters, new BeanPropertyRowMapper<Appointment>(Appointment.class));
    }

    public List<Appointment> getAppointmentById(Long id) {
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        namedParameters.addValue("id", id);
        String query = "SELECT * FROM appointment WHERE id = :id";
        return jdbc.query(query, namedParameters, new BeanPropertyRowMapper<Appointment>(Appointment.class));
    }

    public void updateAppointment(Appointment updatedAppointment) {
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        namedParameters.addValue("id", updatedAppointment.getId());
        namedParameters.addValue("firstName", updatedAppointment.getFirstName());
        namedParameters.addValue("email", updatedAppointment.getEmail());
        namedParameters.addValue("appointmentDate", updatedAppointment.getAppointmentDate());
        namedParameters.addValue("appointmentTime", updatedAppointment.getAppointmentTime());

        String query = "UPDATE appointment SET firstName = :firstName, " +
                "email = :email, " +
                "appointmentDate = :appointmentDate, " +
                "appointmentTime = :appointmentTime " +
                "WHERE id = :id";

        jdbc.update(query, namedParameters);
    }

    public void deleteAppointment(Long id) {
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        namedParameters.addValue("id", id);
        String query = "DELETE FROM appointment where id = :id";
        jdbc.update(query, namedParameters);
    }

}
