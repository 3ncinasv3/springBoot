package ca.sheridancollege.menegonj.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import ca.sheridancollege.menegonj.beans.Book;
import ca.sheridancollege.menegonj.services.BookService;
import jakarta.servlet.http.HttpSession;

@Controller
public class BookController {
	
	@Autowired 
	private BookService bookService; 
	
	@GetMapping("/")
	public String index(Model model, HttpSession session) {
	    List<Book> books = bookService.getAllBooks();
	    
	    if (session.isNew()) {
	        session.setAttribute("cart", new ArrayList<Book>());
	    }

	    model.addAttribute("books", books); 
	    model.addAttribute("newBook", new Book()); 
	    
	    return "index"; 
	}

	
    @GetMapping("/books")
    public String browseBooks(Model model) {
        List<Book> books = bookService.getAllBooks();
        model.addAttribute("books", books);

        model.addAttribute("newBook", new Book());

        return "books";
    }
	
    @GetMapping("/books/{isbn}")
    public String viewBookDetails(@PathVariable Long isbn, Model model) {
        Book book = bookService.getBookByIsbn(isbn);
        model.addAttribute("book", book);
        return "details";
    }
    
    @PostMapping("/insertBook")
    public String insertBook(@ModelAttribute Book book, HttpSession session, Model model) {
        bookService.insertBook(book);
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
        Book book = bookService.getBookByIsbn(isbn);
        model.addAttribute("book", book);
        return "edit";
    }
    
    @PostMapping("/updateBook")
    public String updateBook(@ModelAttribute Book book) {
        Book existingBook = bookService.getBookByIsbn(book.getIsbn());

        if (existingBook != null) {
            existingBook.setTitle(book.getTitle());
            existingBook.setAuthor(book.getAuthor());
            existingBook.setPrice(book.getPrice());
            existingBook.setDescription(book.getDescription());

            bookService.updateBook(existingBook);
        } else {
            return "redirect:/errorPage"; 
        }

        return "redirect:/books"; 
    }
    
    @GetMapping("/deleteBook/{isbn}")
    public String deleteBook(@PathVariable Long isbn, Model model) {
        bookService.deleteBook(isbn);

        List<Book> books = bookService.getAllBooks();
        model.addAttribute("books", books);

        model.addAttribute("newBook", new Book());

        return "redirect:/books";
    }
}
