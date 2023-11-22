package ca.sheridancollege.menegonj.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ca.sheridancollege.menegonj.beans.Book;
import ca.sheridancollege.menegonj.database.DatabaseAccess;

@Service
public class BookService {

    @Autowired
    private DatabaseAccess databaseAccess;

    // Define methods for book-related operations
    public List<Book> getAllBooks() {
        return databaseAccess.getAllBooks();
    }

    public Book getBookByIsbn(Long isbn) {
        return databaseAccess.getBookByIsbn(isbn);
    }

    public void insertBook(Book book) {
        databaseAccess.insertBook(book);
    }

    public void updateBook(Book book) {
        databaseAccess.updateBook(book);
    }

    public void deleteBook(Long isbn) {
        databaseAccess.deleteBookByIsbn(isbn);
    }
}
