package ca.sheridan.lec91_securityLoginLogout.database;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import ca.sheridan.lec91_securityLoginLogout.beans.Book;
import ca.sheridan.lec91_securityLoginLogout.beans.Order;
import ca.sheridan.lec91_securityLoginLogout.beans.User;

@Repository
public class DatabaseAccess {

    @Autowired
    protected NamedParameterJdbcTemplate jdbc;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public void insertBookFromHtml(Book book) {
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        namedParameters.addValue("isbn", book.getIsbn());
        namedParameters.addValue("bookName", book.getBookName());
        namedParameters.addValue("serialName", book.getSerialName());
        namedParameters.addValue("authorName", book.getAuthorName());
        namedParameters.addValue("category", book.getCategory());
        String query = "INSERT INTO book(isbn, bookName, serialName, authorName, category) "
                + "VALUES (:isbn, :bookName, :serialName, :authorName, :category)";

        int rowsAffected = jdbc.update(query, namedParameters);

        if (rowsAffected > 0) {
            System.out.println("Book inserted into the database");
        }

    }

    public List<Book> getAllBooks() {
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        String query = "SELECT * FROM book";
        return jdbc.query(query, namedParameters, new BeanPropertyRowMapper<Book>(Book.class));

    }

    public List<Book> getBookById(Long id) {

        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        String query = "SELECT * FROM book WHERE id = :id";
        namedParameters.addValue("id", id);
        return jdbc.query(query, namedParameters, new BeanPropertyRowMapper<Book>(Book.class));

    }

    public void deleteBookById(Long id) {
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        String query = "DELETE FROM book WHERE id = :id";
        namedParameters.addValue("id", id);
        if (jdbc.update(query, namedParameters) > 0) {
            System.out.println("Deleted book " + id + " from the database.");
        }

    }

    public List<Book> getBookList() {
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        String query = "SELECT * FROM book";
        return jdbc.query(query, namedParameters, new BeanPropertyRowMapper<Book>(Book.class));
    }

    public List<Book> getBookListById(Long id) {
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        String query = "SELECT * FROM book WHERE id = :id";
        namedParameters.addValue("id", id);
        return jdbc.query(query, namedParameters, new BeanPropertyRowMapper<Book>(Book.class));
    }

    public void updateBook(Book updatedBook) {
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        String query = "UPDATE book SET isbn = :isbn, bookName = :bookName, serialName = :serialName, authorName = :authorName , category = :category  WHERE id = :id";
        namedParameters.addValue("id", updatedBook.getId());
        namedParameters.addValue("isbn", updatedBook.getIsbn());
        namedParameters.addValue("bookName", updatedBook.getBookName());
        namedParameters.addValue("serialName", updatedBook.getSerialName());
        namedParameters.addValue("authorName", updatedBook.getAuthorName());
        namedParameters.addValue("category", updatedBook.getCategory());
        int rowsAffected = jdbc.update(query, namedParameters);

        if (rowsAffected > 0) {
            System.out.println("Updated Book with ID " + updatedBook.getId() + " in the database.");
        }
    }

    public User findUserAccount(String email) {
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();

        String query = "SELECT * FROM sec_user where email = :email";
        namedParameters.addValue("email", email);
        try {
            return jdbc.queryForObject(query, namedParameters, new BeanPropertyRowMapper<>(User.class));
        } catch (EmptyResultDataAccessException erdae) {
            return null;
        }
    }

    public List<String> getRolesById(Long userId) {
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        String query = "SELECT sec_role.roleName "
                + "FROM user_role, sec_role "
                + "WHERE user_role.roleId = sec_role.roleId "
                + "AND userId = :userId";
        namedParameters.addValue("userId", userId);
        return jdbc.queryForList(query, namedParameters,
                String.class);
    }

    public void saveOrder(Order order) {
        try {
            MapSqlParameterSource namedParameters = new MapSqlParameterSource();
            namedParameters.addValue("userId", order.getUserId());
            namedParameters.addValue("bookId", order.getBookId());
            namedParameters.addValue("quantity", order.getQuantity());
            namedParameters.addValue("orderDate", order.getOrderDate());

            String query = "INSERT INTO order_table (user_id, book_id, quantity, order_date) " +
                    "VALUES (:userId, :bookId, :quantity, :orderDate)";

            int rowsAffected = jdbc.update(query, namedParameters);

            if (rowsAffected > 0) {
                System.out.println("Order inserted into the database");
            } else {
                System.out.println("No rows affected during order insertion");
            }
        } catch (DataAccessException e) {
            // Log the exception or rethrow it as needed
            e.printStackTrace();
            System.out.println("Error inserting order into the database");
        }
    }

    public void addUser(String email, String password) {
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        String query = "INSERT INTO sec_user "
                + "(email, encryptedPassword, enabled) "
                + "VALUES (:email, :encryptedPassword, 1)";
        namedParameters.addValue("email", email);
        namedParameters.addValue("encryptedPassword",
                passwordEncoder.encode(password));
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

}
