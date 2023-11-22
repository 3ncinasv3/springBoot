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
import org.springframework.web.bind.annotation.RequestParam;

import ca.sheridancollege.menegonj.beans.Book;
import ca.sheridancollege.menegonj.services.BookService;
import ca.sheridancollege.menegonj.services.CartService;
import jakarta.servlet.http.HttpSession;

@Controller
public class CartController {

	@Autowired
	private BookService bookService;

	@Autowired
	private CartService cartService;

	@GetMapping("/cart")
	public String viewCart(Model model, HttpSession session) {
		List<Book> cart = (List<Book>) session.getAttribute("cart");
		model.addAttribute("cart", cart != null ? cart : new ArrayList<Book>());

		// Calculate total cost
		double totalCost = calculateTotalCost(cart);
		model.addAttribute("totalCost", totalCost);

		return "cart";
	}

	private double calculateTotalCost(List<Book> cart) {
		double totalCost = 0.0;
		if (cart != null) {
//			for (Book book : cart) {
//				totalCost += (book.getPrice() * book.getQuantity());
//			}
		}
		return totalCost;
	}

	@PostMapping("/addToCart/{isbn}")
	public String addToCart(@PathVariable Long isbn, @RequestParam("quantity") int quantity, HttpSession session) {
		Book bookToAdd = bookService.getBookByIsbn(isbn);

		if (bookToAdd != null) {
			bookToAdd.setQuantity(quantity);

			// Retrieve cart from session or initialize it if null
			List<Book> cart = (List<Book>) session.getAttribute("cart");
			if (cart == null) {
				cart = new ArrayList<>();
			}

			// Check if the book already exists in the cart
			boolean bookExists = false;
			for (Book cartBook : cart) {
				if (cartBook.getIsbn().equals(isbn)) {
					cartBook.setQuantity(cartBook.getQuantity() + quantity); // Update quantity
					bookExists = true;
					break;
				}
			}

			// If the book doesn't exist, add it to the cart
			if (!bookExists) {
				cart.add(bookToAdd);
			}

			// Update the cart in the session
			session.setAttribute("cart", cart);
		}

		return "redirect:/";
	}

//	@PostMapping("/updateCart")
//	public String updateCart(@RequestParam("isbn") Long isbn, @RequestParam("quantity") int quantity,
//			HttpSession session) {
//		List<Book> cart = (List<Book>) session.getAttribute("cart");
//
//		if (cart != null) {
//			if (quantity <= 0) {
//				// Remove the book if quantity is less than or equal to 0
//				cart.removeIf(book -> book.getIsbn().equals(isbn));
//			} else {
//				// Update the quantity for the book
//				for (Book cartBook : cart) {
//					if (cartBook.getIsbn().equals(isbn)) {
//						cartBook.setQuantity(quantity);
//						break;
//					}
//				}
//			}
//			session.setAttribute("cart", cart);
//		}
//
//		return "redirect:/cart";
//	}
	
	@PostMapping("/updateCart")
	public String updateCart(@RequestParam("isbn") Long isbn, @RequestParam("quantity") int quantity,
	        HttpSession session) {
	    List<Book> cart = (List<Book>) session.getAttribute("cart");

	    if (cart != null) {
	        if (quantity <= 0) {
	            // Remove the book if quantity is less than or equal to 0
	            cart.removeIf(book -> book.getIsbn().equals(isbn));
	        } else {
	            // Update the quantity for the book
	            for (Book cartBook : cart) {
	                if (cartBook.getIsbn().equals(isbn)) {
	                    cartBook.setQuantity(quantity);
	                    break;
	                }
	            }
	        }
	        session.setAttribute("cart", cart);
	    }

	    return "redirect:/cart";
	}



	@PostMapping("/checkout")
	public String checkout(HttpSession session) {
		// Retrieve the cart from the session and save the order details to your
		// database
		List<Book> cart = cartService.getCartBooks(session);
		// Saving order details logic here

		// Clear the cart after placing the order
		cartService.clearCart(session);

		return "redirect:/cart";
	}
}
