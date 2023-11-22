package ca.sheridancollege.menegonj.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import ca.sheridancollege.menegonj.beans.Book;
import ca.sheridancollege.menegonj.database.DatabaseAccess;
import ca.sheridancollege.menegonj.services.BookService;
import jakarta.servlet.http.HttpSession;

@Controller
public class BookController {
	
	@Autowired 
	@Lazy
	private BookService bookService; 
	
	@Autowired
	private DatabaseAccess databaseAccess;

	
	@GetMapping("/")
	public String index(Model model, HttpSession session) {
	    List<Book> books = bookService.getAllBooks(); // Fetch all books

	    // Check if the session attribute for the cart is new, and set it if needed
	    if (session.isNew()) {
	        session.setAttribute("cart", new ArrayList<Book>());
	    }

	    model.addAttribute("books", books); // Add books to the model
	    model.addAttribute("newBook", new Book()); // Add a new book object for the form

	    return "index"; // Return the index.html view
	}

	
    @GetMapping("/books")
    public String browseBooks(Model model) {
        List<Book> books = bookService.getAllBooks();
        model.addAttribute("books", books);

        // Instantiate a new Book object for the form
        model.addAttribute("newBook", new Book());

        return "books";
    }
	
    @GetMapping("/books/{isbn}")
    public String viewBookDetails(@PathVariable Long isbn, Model model) {
        Book book = bookService.getBookByIsbn(isbn);
        model.addAttribute("book", book);
        return "details";
    }
    
//    @PostMapping("/insertBook")
//    public String insertBook(@ModelAttribute Book book, HttpSession session, Model model) {
//        // Insert the new book into the database using your bookService
//        bookService.insertBook(book);
//
//        List<Book> books = bookService.getAllBooks();
//
//        model.addAttribute("books", books);
//
//        return "redirect:/books"; 
//    }
    
    @PostMapping("/insertBook")
    public String insertBook(@ModelAttribute Book book, HttpSession session, Model model) {
        // Check user role before inserting the book
        Long userId = (Long) session.getAttribute("userId");
        List<String> roles = databaseAccess.getRolesById(userId);
        
        // Check if the user has admin rights (assuming 'ADMIN' is a role name)
        if (roles.contains("ADMIN")) {
            bookService.insertBook(book);
        } else {
            // Handle unauthorized access (e.g., redirect to an error page)
            return "redirect:/errorPage";
        }

        List<Book> books = bookService.getAllBooks();
        model.addAttribute("books", books);

        return "redirect:/books"; 
    }

    
    @GetMapping("/insert")
    public String showInsertBookForm(Model model) {
        model.addAttribute("book", new Book());
        return "insert";
    }
    
    @GetMapping("/editBook/{isbn}")
    public String showEditBookForm(@PathVariable Long isbn, Model model) {
        // Retrieve the book by ISBN
        Book book = bookService.getBookByIsbn(isbn);
        model.addAttribute("book", book);
        return "edit"; // Assuming "edit.html" is your edit form
    }
    
    @PostMapping("/updateBook")
    public String updateBook(@ModelAttribute Book book) {
        // Get the existing book from your data store
        Book existingBook = bookService.getBookByIsbn(book.getIsbn());

        if (existingBook != null) {
            // Update the fields of the existing book with the new data
            existingBook.setTitle(book.getTitle());
            existingBook.setAuthor(book.getAuthor());
            existingBook.setPrice(book.getPrice());
            existingBook.setDescription(book.getDescription());

            // Save the updated book back to the data store
            bookService.updateBook(existingBook);
        } else {
            // Handle the case where no book with the specified ISBN is found
            // You can redirect to an error page or display an error message
            return "redirect:/errorPage"; // Replace with the appropriate error page
        }

        return "redirect:/books"; // Redirect to the book list after updating
    }
    
    @GetMapping("/deleteBook/{isbn}")
    public String deleteBook(@PathVariable Long isbn, Model model) {
        // Delete the book by its ID
        bookService.deleteBook(isbn);

        // Re-populate the book list
        List<Book> books = bookService.getAllBooks();
        model.addAttribute("books", books);

        // Add the newBook object for the form
        model.addAttribute("newBook", new Book());

        return "redirect:/books";
    }
}
