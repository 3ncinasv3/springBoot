package ca.sheridan.lec91_securityLoginLogout.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import ca.sheridan.lec91_securityLoginLogout.beans.Book;
import ca.sheridan.lec91_securityLoginLogout.beans.CartItem;
import ca.sheridan.lec91_securityLoginLogout.database.DatabaseAccess;

@Controller
@SessionAttributes("cartItems")
public class HomeController {

    List<Book> bookList = new CopyOnWriteArrayList<Book>();

    @Autowired
    @Lazy
    private DatabaseAccess da;

    @GetMapping("/")
    public String index(Model model) {

        // this method just returning nothing

        // model.addAttribute("book", new Book());
        // model.addAttribute("bookList", da.getAllBooks());
        return "index";
    }

    @GetMapping("/secure/cart")
    public String getCart() {
        return "secure/cart";
    }

    @GetMapping("/register")
    public String getRegister() {
        return "register";
    }

    @PostMapping("/register")
    public ResponseEntity<String> postRegister(@RequestParam String username, @RequestParam String password) {
        try {
            // Your registration logic here
            da.addUser(username, password);
            Long userId = da.findUserAccount(username).getUserId();
            da.addRole(userId, Long.valueOf(1));

            // Construct a JSON success response
            String jsonResponse = "{\"success\": true, \"message\": \"Registration successful\"}";

            return ResponseEntity.ok().body(jsonResponse);
        } catch (Exception e) {
            // Handle registration failure
            // Construct a JSON error response
            String errorResponse = "{\"success\": false, \"message\": \"Registration failed: " + e.getMessage() + "\"}";

            return ResponseEntity.status(500).body(errorResponse);
        }
    }

    @GetMapping("/secure")
    public String secureIndex() {
        return "/secure/book";
    }

    @GetMapping("/secure/orderConfirmation")
    public String secureOrderConfimation() {
        return "/secure/orderConfirmation";
    }

    // @GetMapping("/admin")
    // public String adminIndex() {
    // return "/admin/admin";
    // }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/permission-denied")
    public String permissionDenied() {
        return "/error/permission-denied";
    }

    @GetMapping("/secure/book")
    public String book(Model model) {
        model.addAttribute("book", new Book());
        model.addAttribute("bookList", da.getAllBooks());
        return "/secure/book";
    }

    @PostMapping("/addBook")
    public String addBook(Model model, @ModelAttribute Book book) {
        da.insertBookFromHtml(book);
        model.addAttribute("book", new Book());
        model.addAttribute("bookList", da.getAllBooks());
        return "/secure/book";
    }

    @GetMapping("/secure/book/editBookById/{id}")
    public String editBookById(Model model, @PathVariable Long id) {

        Book book = da.getBookById(id).get(0);
        da.updateBook(book);
        model.addAttribute("book", book);
        model.addAttribute("bookList", da.getBookList());

        return "/secure/book";
    }

    @GetMapping("/secure/book/deleteBookById/{id}")
    public String deleteBookById(Model model, @PathVariable Long id) {
        da.deleteBookById(id);
        model.addAttribute("message", "Book with ID" + id + " has been deleted successfully");

        model.addAttribute("bookList", da.getBookList());
        model.addAttribute("book", new Book());

        return "redirect:/secure/book";
    }

    @PostMapping("/secure/book/insertBook")
    public String insertBook(Model model, @ModelAttribute Book book) {
        List<Book> existingBook = da.getBookById(book.getId());

        if (existingBook.isEmpty()) {
            da.insertBookFromHtml(book);
        } else {
            da.updateBook(book);
        }

        model.addAttribute("bookList", da.getBookList());
        model.addAttribute("book", new Book());

        return "redirect:/secure/book";
    }

    @GetMapping("/secure/bookdetails/{id}")
    public String viewBookDetails(Model model, @PathVariable Long id) {
        Book book = da.getBookById(id).get(0);
        model.addAttribute("book", book);
        return "secure/bookdetails";
    }

    // ==================================================================
    // Add books to the cart
    @PostMapping("/secure/addToCart")
    public String addToCart(Model model, @RequestParam Long bookId) {
        Book book = da.getBookById(bookId).get(0);
        CartItem cartItem = new CartItem(book, 1); // Initial quantity is 1
        List<CartItem> cartItems = getOrCreateCart(model);
        cartItems.add(cartItem);
        return "redirect:/secure/book";
    }

    // Update quantities in the cart
    @PostMapping("/secure/updateCart")
    public String updateCart(Model model, @RequestParam Long bookId, @RequestParam Integer quantity) {
        List<CartItem> cartItems = getOrCreateCart(model);
        for (CartItem item : cartItems) {
            if (item.getBook().getId().equals(bookId)) {
                item.setQuantity(quantity);
                break;
            }
        }
        return "redirect:/secure/cart";
    }

    // Place an order
    // @PostMapping("/secure/placeOrder")
    // public String placeOrder(Model model) {
    // List<CartItem> cartItems = getOrCreateCart(model);
    // // Save order details to the database (you can create a new table for orders)
    // // Reset the cart after placing the order
    // cartItems.clear();
    // return "redirect:/secure/book";
    // }

    // Utility method to get or create the cart in the session
    private List<CartItem> getOrCreateCart(Model model) {
        List<CartItem> cartItems = (List<CartItem>) model.getAttribute("cartItems");
        if (cartItems == null) {
            cartItems = new ArrayList<>();
            model.addAttribute("cartItems", cartItems);
        }
        return cartItems;
    }

    // SecurityContextLogoutHandler logoutHandler = new
    // SecurityContextLogoutHandler();

    // @PostMapping("/user-logout")
    // public String performLogout(Authentication authentication, HttpServletRequest
    // request,
    // HttpServletResponse response) {
    // // .. perform logout
    // this.logoutHandler.logout(request, response, authentication);
    // return "index";
    // }

    // @GetMapping("/user-logout")
    // public String logout(){
    // return "redirect:/";
    // }

}