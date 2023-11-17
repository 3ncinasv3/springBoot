package ca.sheridancollege.BookStoreAssignmentencinasv.controllers;

import ca.sheridancollege.BookStoreAssignmentencinasv.beans.Book;
//import ca.sheridancollege.BookStoreAssignmentencinasv.beans.Customer;
import ca.sheridancollege.BookStoreAssignmentencinasv.database.DatabaseAccess;
import jakarta.servlet.http.HttpSession;
//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class HomeController {

    private final DatabaseAccess da;

    public HomeController(DatabaseAccess da) {
        this.da = da;
    }


    @GetMapping("/")
    public String index(Model model, HttpSession session) {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        session.getAttribute(session.getId());
        session.setAttribute("sessionId", session.getId());
//        String userId = authentication.getName();
//        System.out.println(userId);
        // Calculate pagination information
//        int pageSize = 5; // Adjust as needed
//        int totalBooks = da.getBooksSize(); // Replace with your method to get total books
//        int totalPages = (int) Math.ceil((double) totalBooks / pageSize);
        model.addAttribute("books", da.getBooks());
//        model.addAttribute("customers", da.getCustomers());
//        model.addAttribute("totalPages", totalPages);
//        model.addAttribute("currentPage", page);
//        model.addAttribute("pageSize", pageSize);
//        session.setAttribute
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        String userId = authentication.getName();
//        session.getAttribute("book_" + userId);
//        model.addAttribute("user")
        return "index";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/registration")
    public String registration(Model model) {
//        model.addAttribute("customer", new Customer());
        return "registration";
    }

    @GetMapping("/secure/index")
    public String secure(Model model, HttpSession session) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userId = authentication.getName();
        System.out.println(userId);
//        session.getAttribute()
//        if (session.isNew()) {
            session.setAttribute("userId", userId);
//            session.setAttribute("book_" + userId);
//        } else {
//            userBooks = (List<Book>) session.getAttribute("book_" + userId);

//            if (userBooks == null) {
//                userBooks = new ArrayList<>();
//                session.setAttribute("book_" + userId, userBooks);
//            }
//            session.setAttribute("book_" + userId, userBooks);
//        }
//        model.addAttribute("books", da.getBooks());
//        model.addAttribute("customers", da.getCustomers());
        model.addAttribute("books", da.getBooks());
        session.getAttribute("books_" + userId);
//        session.getAttribute("book_" + userId);
//        session.setAttribute("sessionId", session.getId());
//        session.setAttribute("sessionPrice", session.getAttribute("book_"))


        return "secure/index";
    }
//@GetMapping("/secure/index")
//public String secure(Model model, HttpSession session) {
//    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//    String userId = authentication.getName();
//
//    List<Book> userBooks = (List<Book>) session.getAttribute("book_" + userId);
//
//    if (userBooks == null) {
//        userBooks = new ArrayList<>();
//        session.setAttribute("book_" + userId, userBooks);
//    }
//    session.setAttribute("book_" + userId, userBooks);
//    model.addAttribute("books", da.getBooks());
//    model.addAttribute("customers", da.getCustomers());
//    model.addAttribute("userBooks", userBooks);
//
//    return "/secure/index";
//}
//    @PostMapping("/registration")
//    public String registerCustomer(Model model, @ModelAttribute Customer customer) {
//        da.registerCustomer(customer);
//        model.addAttribute("customer", new Customer());
//        model.addAttribute("books", da.getBooks());
//        return "index";
//    }
    @GetMapping("/register")
    public String getRegister() { return "register"; }

    @PostMapping("/register")
    public String postRegister(@RequestParam String username, @RequestParam String password) {
        da.addUser(username, password);
        Long userId = da.findUserAccount(username).getUserId();
        da.addRole(userId, 1L);
        return "redirect:/";
    }

//    @GetMapping("/secure/userlist")
//    public String userList(Model model) {
////        model.addAttribute("customers", da.getCustomers());
//        return "/secure/userlist";
//    }

    @GetMapping ("/secure/addToCart/{isbn}")
    public String addToCart(HttpSession session, @PathVariable Long isbn) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userId = authentication.getName();

        System.out.println(isbn);
        Book specificBook = da.getBookByISBN(isbn);

        List<Book> userBooks = (List<Book>) session.getAttribute("book_" + userId);
        if (userBooks == null) {
            userBooks = new ArrayList<>();
            session.setAttribute("book_" + userId, userBooks);
        }
        userBooks.add(specificBook);
        session.setAttribute("book_" + userId, userBooks);

//        List<Book> cart = (List<Book>) session.getAttribute("cart");
//        System.out.println("Cart content: " + cart);
//        if (cart == null) {
//            cart = new ArrayList<>();
//        }

//        cart.add(book);
//        session.setAttribute("cart", cart);
//        model.addAttribute("books", da.getBooks());
//        model.addAttribute("book", new Book());
//        System.out.println("Book added to cart:" + book);
//        model.add
//        session.setAttribute("book", books);

//        model.addAttribute("books", da.getBooks());
//        model.addAttribute("book", book);
        return "secure/cart";
    }

//    @GetMapping("/secure/addToCart/{isbn}")
//    public String addToCart(HttpSession session, @PathVariable Long isbn) {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        String userId = authentication.getName();
//        System.out.println(isbn);
//
//        List<Book> userBooks = (List<Book>) session.getAttribute("book_" + userId);
//
//        if (userBooks == null) {
//            userBooks = new ArrayList<>();
//            session.setAttribute("book_" + userId, userBooks);
//        }
//
//        Book specificBook = da.getBookByISBN(isbn);
//        userBooks.add(specificBook);
//        session.setAttribute("book_" + userId, userBooks);
//
//        return "/secure/cart";
//    }

    @GetMapping("/secure/cart")
    public String cart() {
        return "secure/cart";
    }

}