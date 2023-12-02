    package ca.sheridan.lec91_securityLoginLogout.beans;

    // import org.springframework.stereotype.Component;
    // import org.springframework.web.context.annotation.SessionScope;

    // import java.util.ArrayList;
    // import java.util.List;

    // @Component
    // @SessionScope
    // public class ShoppingCart {

    //     private List<CartItem> cartItems = new ArrayList<>();

    //     public List<CartItem> getCartItems() {
    //         return cartItems;
    //     }

    //     public void setCartItems(List<CartItem> cartItems) {
    //         this.cartItems = cartItems;
    //     }
    // }


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import jakarta.servlet.http.HttpSession;

import java.util.ArrayList;
import java.util.List;

@Component
@SessionScope
public class ShoppingCart {

    private List<CartItem> cartItems = new ArrayList<>();

    // Autowire the HttpSession to retrieve the ShoppingCart from the session
    @Autowired
    private HttpSession httpSession;

    public List<CartItem> getCartItems() {
        return cartItems;
    }

    public void setCartItems(List<CartItem> cartItems) {
        this.cartItems = cartItems;
    }

    // Add other methods for manipulating the shopping cart

    // Implement the logic to get or create the cart in the session
    public List<CartItem> getOrCreateCart() {
        // Try to retrieve the shopping cart from the session
        List<CartItem> cartItems = (List<CartItem>) httpSession.getAttribute("cartItems");

        // If the cart is not found in the session, create a new one and store it in the session
        if (cartItems == null) {
            cartItems = new ArrayList<>();
            httpSession.setAttribute("cartItems", cartItems);
        }

        return cartItems;
    }

        // Implement the logic to get or create the cart in the session
        public List<CartItem> getShoppingCartFromSession() {
            List<CartItem> cartItems = (List<CartItem>) httpSession.getAttribute("cartItems");
            if (cartItems == null) {
                cartItems = new ArrayList<>();
                httpSession.setAttribute("cartItems", cartItems);
            }
            return cartItems;
        }
}
