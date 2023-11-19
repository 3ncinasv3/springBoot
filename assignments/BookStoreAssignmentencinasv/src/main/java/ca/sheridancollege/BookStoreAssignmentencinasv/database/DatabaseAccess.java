package ca.sheridancollege.BookStoreAssignmentencinasv.database;

import ca.sheridancollege.BookStoreAssignmentencinasv.beans.Book;
import ca.sheridancollege.BookStoreAssignmentencinasv.beans.User;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
@Component
public class DatabaseAccess {
    protected final NamedParameterJdbcTemplate jdbc;

    private final BCryptPasswordEncoder passwordEncoder;

    public DatabaseAccess(NamedParameterJdbcTemplate jdbc, BCryptPasswordEncoder passwordEncoder) {
        this.jdbc = jdbc;
        this.passwordEncoder = passwordEncoder;
    }


    public List<Book> getBooks() {
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        String query = "SELECT * FROM book";
        return jdbc.query(query, namedParameters, new BeanPropertyRowMapper<Book>(Book.class));
    }

    public int getBooksSize() {
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        String query = "SELECT * FROM book";
        return jdbc.query(query, namedParameters, new BeanPropertyRowMapper<Book>(Book.class)).size();
    }

    public Book getBookByISBN(Long isbn) {
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        namedParameters.addValue("isbn", isbn);
        String query = "SELECT * FROM book WHERE isbn = :isbn";

        return jdbc.queryForObject(query, namedParameters, (rs, rowNum) -> {
            Book book = new Book();
            book.setISBN(rs.getString("isbn"));
            book.setTitle(rs.getString("title"));
            book.setAuthor(rs.getString("author"));
            book.setPrice(rs.getBigDecimal("price"));
            book.setDescription(rs.getString("description"));
            book.setImageUrl(rs.getString("imageUrl"));

            return book;
        });

    }


//    public List<Customer> getCustomers() {
//        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
//        String query = "SELECT * FROM customer";
//        return jdbc.query(query, namedParameters, new BeanPropertyRowMapper<Customer>(Customer.class));
//    }

//    public int getCustomerListSize() {
//        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
//        String query = "SELECT * FROM customer";
//        List<Customer> count = jdbc.query(query, namedParameters, new BeanPropertyRowMapper<Customer>(Customer.class));
//        return count.size();
//    }



//    public void registerCustomer(Customer customer) {
//        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
//        namedParameters.addValue("username", customer.getUsername());
//        namedParameters.addValue("email", customer.getEmail());
//        namedParameters.addValue("password", customer.getPassword());
//        String query = "INSERT INTO customer(username, email, password) VALUES (:username, :email, :password)";
//        jdbc.update(query, namedParameters);
//
//    }


    // New stuff
    public User findUserAccount(String email){
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        String query = "SELECT * FROM sec_user WHERE email = :email";
        namedParameters.addValue("email", email);
        try {
            return jdbc.queryForObject(query, namedParameters, new BeanPropertyRowMapper<>(User.class));
        } catch (EmptyResultDataAccessException erdae) {
            return null;
        }
    }

//    public User findUserFirstName(String email){
//        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
//        String query = "SELECT * FROM sec_user WHERE email = :email";
//        namedParameters.addValue("email", email);
//        try {
//            return jdbc.queryForObject(query, namedParameters, new BeanPropertyRowMapper<>(User.class));
//        } catch (EmptyResultDataAccessException erdae) {
//            return null;
//        }
//    }


    public List<String> getRolesById(Long userId) {
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        String query ="SELECT sec_role.roleName "
                + "FROM user_role, sec_role "
                + "WHERE user_role.roleId = sec_role.roleId "
                + "AND userId = :userId";
        namedParameters.addValue("userId", userId);
        return jdbc.queryForList(query, namedParameters, String.class);
    }

    public void addUser(String email, String password, String firstName) {
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        String query = "INSERT INTO sec_user "
                + "(email, firstName, encryptedPassword, enabled) "
                + "VALUES (:email, :firstName, :encryptedPassword, 1)";
        namedParameters.addValue("email", email);
        namedParameters.addValue("encryptedPassword",
                passwordEncoder.encode(password));
        namedParameters.addValue("firstName", firstName);
        jdbc.update(query, namedParameters);
    }

    public void addRole(Long userId, Long roleId) {
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        String query = "INSERT INTO user_role (userId, roleId) "
                + "VALUES (:userId, :roleId)";
        namedParameters.addValue("userId", userId);
        namedParameters.addValue("roleId", roleId);
        jdbc.update(query, namedParameters);

    }


    // Game Of Thrones Books
    public List<Book> getGameOfThrones() {
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        String query = "SELECT * FROM gameOfThrones";
        return jdbc.query(query, namedParameters, new BeanPropertyRowMapper<Book>(Book.class));
    }
    public Book getGameOfThronesByISBN(Long isbn) {
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        namedParameters.addValue("isbn", isbn);
        String query = "SELECT * FROM gameOfThrones WHERE isbn = :isbn";

        return jdbc.queryForObject(query, namedParameters, (rs, rowNum) -> {
            Book book = new Book();
            book.setISBN(rs.getString("isbn"));
            book.setTitle(rs.getString("title"));
            book.setAuthor(rs.getString("author"));
            book.setPrice(rs.getBigDecimal("price"));
            book.setDescription(rs.getString("description"));
            book.setImageUrl(rs.getString("imageUrl"));

            return book;
        });

    }
    public boolean checkIsGameOfThrones(Long isbn) {
        return isbn.toString().startsWith(String.valueOf(9));

    }

    public List<Book> getAllBooks() {

        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        // Query for Harry Potter Books
        String bookQuery = "SELECT * FROM book";
        List<Book> hpBooks = jdbc.query(bookQuery, namedParameters, new BeanPropertyRowMapper<Book>(Book.class));

        // Query for Game of Thrones Books
        String gameOfThronesQuery = "SELECT * FROM gameOfThrones";
        List<Book> gotBooks = jdbc.query(gameOfThronesQuery, namedParameters, new BeanPropertyRowMapper<Book>(Book.class));

        List<Book> allBooks = new ArrayList<>();
        allBooks.addAll(hpBooks);
        allBooks.addAll(gotBooks);

      return allBooks;
    }

}
