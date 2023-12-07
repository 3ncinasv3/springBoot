package ca.sheridancollege.menegonj.services;

import org.springframework.stereotype.Service;

import ca.sheridancollege.menegonj.beans.Book;
import jakarta.servlet.http.HttpSession;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartService {

	public void addToCart(HttpSession session, Book book) {
		List<Book> cart = (List<Book>) session.getAttribute("cart");
		if (cart == null) {
			cart = new ArrayList<>();
		}
		cart.add(book);
		session.setAttribute("cart", cart);
	}

	public void updateCartItemQuantity(HttpSession session, Long isbn, int quantity) {
		List<Book> cart = (List<Book>) session.getAttribute("cart");

		if (cart != null) {
			for (Book cartBook : cart) {
				if (cartBook.getIsbn().equals(isbn)) {
					cartBook.setQuantity(quantity);
					break;
				}
			}
			session.setAttribute("cart", cart);
		}
	}

	public void updateCart(HttpSession session, List<Book> updatedCart) {
		session.setAttribute("cart", updatedCart);
	}

	public List<Book> getCartBooks(HttpSession session) {
		return (List<Book>) session.getAttribute("cart");
	}

	public void clearCart(HttpSession session) {
		session.removeAttribute("cart");
	}
}
