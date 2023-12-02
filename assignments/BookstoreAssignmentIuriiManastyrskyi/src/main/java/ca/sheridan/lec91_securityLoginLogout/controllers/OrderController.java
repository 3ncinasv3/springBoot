package ca.sheridan.lec91_securityLoginLogout.controllers;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import ca.sheridan.lec91_securityLoginLogout.beans.CartItem;
import ca.sheridan.lec91_securityLoginLogout.beans.Order;
import ca.sheridan.lec91_securityLoginLogout.beans.ShoppingCart;
import ca.sheridan.lec91_securityLoginLogout.beans.User;
import ca.sheridan.lec91_securityLoginLogout.database.DatabaseAccess;
import org.springframework.web.bind.annotation.*;

@Controller
public class OrderController {

    @Autowired
    private DatabaseAccess databaseAccess;

    @Autowired
    private ShoppingCart shoppingCart;

    public Long getUserIdByUsername(String username) {
        // Assuming you have a method to find a user by email in your DatabaseAccess
        User user = databaseAccess.findUserAccount(username);

        if (user == null) {
            // Handle the case where the user is not found for the given username
            throw new RuntimeException("User not found for username: " + username);
        }

        // Retrieve the user ID from the user object
        return user.getUserId();
    }

    @PostMapping("/secure/placeOrder")
    public String placeOrder(Principal principal, Model model) {
        try {
            // Get the currently logged-in user's username (email)
            String username = principal.getName();

            // Retrieve the shopping cart from the session
            List<CartItem> cartItems = shoppingCart.getOrCreateCart();

            // Extract information from the shopping cart and save the order
            Long userId = getUserIdByUsername(username);

            for (CartItem cartItem : cartItems) {
                Long bookId = cartItem.getBook().getId();
                int quantity = cartItem.getQuantity();
                Order order = new Order(userId, bookId, bookId, quantity, LocalDateTime.now());
                databaseAccess.saveOrder(order);
            }

            // Clear the shopping cart after placing the order
            cartItems.clear();

            // Add a success message or any other relevant information to the model
            model.addAttribute("successMessage", "Order placed successfully!");

            // Redirect to a confirmation page or any other desired page
            return "redirect:/secure/orderConfirmation";
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("errorMessage", "Error placing the order.");
            return "redirect:/secure/shoppingCart";
        }
    }

}
