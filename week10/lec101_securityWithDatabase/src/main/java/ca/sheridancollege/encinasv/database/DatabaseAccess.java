package ca.sheridancollege.encinasv.database;

import ca.sheridancollege.encinasv.beans.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DatabaseAccess {
    @Autowired
    private NamedParameterJdbcTemplate jdbc;
    // Method to find a user account by email
    public User findUserAccount(String email) {
        MapSqlParameterSource namedParameters = new
                MapSqlParameterSource();
        String query = "SELECT * FROM sec_user where email = :email";
        namedParameters.addValue("email", email);
        try {
            return jdbc.queryForObject(query, namedParameters, new
                    BeanPropertyRowMapper<>(User.class));
        } catch (EmptyResultDataAccessException erdae) {
            return null;
        }
    }
    // Method to get User Roles for a specific User id
    public List<String> getRolesById(Long userId) {
        MapSqlParameterSource namedParameters = new
                MapSqlParameterSource();
        String query = "SELECT sec_role.roleName "
                + "FROM user_role, sec_role "
                + "WHERE user_role.roleId = sec_role.roleId "
                + "AND userId = :userId";
        namedParameters.addValue("userId", userId);
        return jdbc.queryForList(query, namedParameters,
                String.class);
    }
}