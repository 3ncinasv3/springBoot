package ca.sheridancollege.BookStoreAssignmentencinasv.controllers;

import ca.sheridancollege.BookStoreAssignmentencinasv.beans.Book;
import ca.sheridancollege.BookStoreAssignmentencinasv.beans.Cart;
import ca.sheridancollege.BookStoreAssignmentencinasv.database.DatabaseAccess;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unchecked")
@Controller
public class HomeController {
    private final DatabaseAccess da;

    public HomeController(DatabaseAccess da) {
        this.da = da;
    }

    @GetMapping("/")
    public String index(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userId = authentication.getName();
        // Calculate pagination information
//        int pageSize = 5; // Adjust as needed
//        int totalBooks = da.getBooksSize(); // Replace with your method to get total books
//        int totalPages = (int) Math.ceil((double) totalBooks / pageSize);

        model.addAttribute("books", da.getBooks());

//        model.addAttribute("customers", da.getCustomers());
//        model.addAttribute("totalPages", totalPages);
//        model.addAttribute("currentPage", page);
//        model.addAttribute("pageSize", pageSize);

         model.addAttribute("userFirstName", da.findUserAccount(userId));
        return "index";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }


    @GetMapping("/secure/index")
    public String secure(Model model) {
        model.addAttribute("books", da.getAllBooks());

        return "secure/index";
    }

    @GetMapping("/register")
    public String getRegister() { return "register"; }

    @PostMapping("/register")
    public String postRegister(@RequestParam String username, @RequestParam String password, @RequestParam String firstName) {
        da.addUser(username, password, firstName);
        Long userId = da.findUserAccount(username).getUserId();
        da.addRole(userId, 1L);
        return "redirect:/";
    }

    @GetMapping ("/secure/addToCart/{isbn}")
    public String addToCart(HttpSession session, @PathVariable Long isbn) {
        Book specificBook;

        if (da.checkIsGameOfThrones(isbn)) {
            specificBook = da.getGameOfThronesByISBN(isbn);
        } else {
            specificBook = da.getBookByISBN(isbn);
        }

        List<Book> userBooks = (List<Book>) session.getAttribute("cart");
        if (userBooks == null) {
            userBooks = new ArrayList<>();
            session.setAttribute("cart", userBooks);
        }
        userBooks.add(specificBook);
        session.setAttribute("cart", userBooks);

        return "secure/cart";
    }

    @GetMapping("/secure/remove/{isbn}")
    public String remove(@PathVariable Long isbn, HttpSession session) {
        Object cartAttribute = session.getAttribute("cart");

        Book specificBook;
        if (da.checkIsGameOfThrones(isbn)) {
            specificBook = da.getGameOfThronesByISBN(isbn);
        } else {
            specificBook = da.getBookByISBN(isbn);
        }

        List<Book> userBooks;
        if (cartAttribute instanceof List<?> && !((List<?>) cartAttribute).isEmpty()) {
            userBooks = (List<Book>) cartAttribute;
            userBooks.remove(specificBook);
            session.setAttribute("cart", userBooks);
            // Now you can use the userBooks list safely
        } else {
            userBooks = new ArrayList<>();
            // Handle the case when the attribute is not present or not a List<Book>
            session.setAttribute("cart", userBooks);
//            session.setAttribute("cart", new List<Book>());
        }

//        if (userBooks == null) {
//            userBooks = new ArrayList<>();
//            session.setAttribute("cart", userBooks);
//        }
//        userBooks.remove(specificBook);
//        session.setAttribute("cart", userBooks);

        return "secure/cart";
    }


    @GetMapping("/secure/cart")
    public String cart(HttpSession session) {
        List<Book> cartBooks  = (List<Book>) session.getAttribute("cart");
//        session.setAttribute("userCart", session.getAttribute("cart"));


        return "secure/cart";
    }

    @GetMapping("/details/{isbn}")
    public String details(@PathVariable Long isbn, Model model) {

        if (da.checkIsGameOfThrones(isbn)) {
            Book specificBook = da.getGameOfThronesByISBN(isbn);
            model.addAttribute("book", specificBook);
        } else {
            Book specificBook = da.getBookByISBN(isbn);
            model.addAttribute("book", specificBook);
        }
        return "details";
    }

//    @GetMapping("/secure/pay")
//    public String pay() {
//        return "secure/pay";
//    }

    @GetMapping("/secure/pay")
    public String pay(HttpSession session) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userId = authentication.getName();
        System.out.println(userId);

        List<Book> userCart = (List<Book>) session.getAttribute("cart");

        if (userCart != null && !userCart.isEmpty()) {
            // Add user cart to the database
            da.addCartToDatabase(userId, userCart);

            // Clear the user's cart in the session after adding to the database
            userCart.clear();
            session.setAttribute("cart", userCart);
        }

        return "secure/pay";
    }

    // Game Of Thrones Logic
    @GetMapping("/gameOfThrones")
    public String gameOfThrones(Model model) {
        model.addAttribute("books", da.getGameOfThrones());

        return "gameOfThrones";
    }

    // Admin Pages
    @GetMapping("/admin/index")
    public String admin(Model model) {
        model.addAttribute("books", da.getAllBooks());
        model.addAttribute("book", new Book());
        return "/admin/index";
    }
    @GetMapping("/admin/editBook/{isbn}")
    public String editBook(Model model, @PathVariable Long isbn) {
        if (da.checkIsGameOfThrones(isbn)) {
            if (da.getGameOfThronesByISBN(isbn) != null) {
                model.addAttribute("book", da.getGameOfThronesByISBN(isbn));
            }
        } else model.addAttribute("book", da.getBookByISBN(isbn));
        model.addAttribute("books", da.getAllBooks());

        return "/admin/index";
    }


    @PostMapping("/admin/insertBook")
    public String insertBook(Model model, @ModelAttribute Book book) {
        List<Book> existingBooks = da.getAllMatchingISBN(Long.valueOf(book.getISBN()));
        if (existingBooks.isEmpty()) {
//            if (da.checkIsGameOfThrones(Long.valueOf(book.getISBN()))) {
                da.insertBook(book);
            } else { da.updateBook(book);

            }

        model.addAttribute("book", new Book());
        model.addAttribute("books", da.getAllBooks());
        return "/admin/index";
    }

    @GetMapping("/permission-denied")
    public String permissionDenied() {
        return "/error/permission-denied";
    }


}