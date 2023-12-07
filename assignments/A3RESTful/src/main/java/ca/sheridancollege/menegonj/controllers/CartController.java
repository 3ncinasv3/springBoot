package ca.sheridancollege.menegonj.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import ca.sheridancollege.menegonj.beans.CPU;
import ca.sheridancollege.menegonj.beans.CartItem;
import ca.sheridancollege.menegonj.beans.GraphicsCard;
import ca.sheridancollege.menegonj.database.DatabaseAccess;
import jakarta.servlet.http.HttpSession;

@Controller
public class CartController {

	@Autowired
	private DatabaseAccess databaseAccess; // Inject DatabaseAccess instance
//	<a href="/secure/user/cart"	th:href="@{/secure/user/cart/cpu/} + ${cpu.id}">Add To Cart</a>


	@GetMapping("/secure/user/cart/{partType}/{id}")
	public String addCpuToCart(@PathVariable Long id,@PathVariable String partType, HttpSession session, Model model) {

		if (session.isNew()) {
			List<CartItem> cart = new ArrayList<>();
			session.setAttribute("cart", cart);
			System.out.println(session);
			System.out.println(cart);
		} else {
			System.out.println(session);

			CartItem itemToAdd = null;

			switch (partType.toLowerCase()) {
				case "cpu":
					CPU cpu = databaseAccess.getCPUById(id);
					if (cpu != null) {
						// Create a CartItem object from the CPU
						itemToAdd = convertCPUToCartItem(cpu);
					}
					break;
				case "gpu":
					GraphicsCard gpu = databaseAccess.getGraphicsCardById(id);
					if (gpu != null) {
						// Create a CartItem object from the GraphicsCard
						itemToAdd = convertGraphicsCardToCartItem(gpu);
					}
					break;
//		 Add similar cases for Memory, Motherboard, Storage, etc.

				default:
					// Handle the case when hardwareType is not recognized
					// Possibly return an error message or redirect appropriately
					break;
			}

			if (itemToAdd != null) {
				List<CartItem> cart = (List<CartItem>) session.getAttribute("cart");
				if (cart == null) {
					cart = new ArrayList<>();
				}
				cart.add(itemToAdd);
				session.setAttribute("cart", cart);
				System.out.println(cart);

			}
		}



		return "/secure/user/cart";
	}
//
//	@PostMapping("/addToCart/{hardwareType}/{itemId}")
//	public String addToCart(@PathVariable String hardwareType, @PathVariable Long itemId, HttpSession session) {
//		CartItem itemToAdd = null;
//
//		switch (hardwareType.toLowerCase()) {
//		case "cpu":
//			CPU cpu = databaseAccess.getCPUById(itemId);
//			if (cpu != null) {
//				// Create a CartItem object from the CPU
//				itemToAdd = convertCPUToCartItem(cpu);
//			}
//			break;
//		case "gpu":
//			GraphicsCard gpu = databaseAccess.getGraphicsCardById(itemId);
//			if (gpu != null) {
//				// Create a CartItem object from the GraphicsCard
//				itemToAdd = convertGraphicsCardToCartItem(gpu);
//			}
//			break;
//		// Add similar cases for Memory, Motherboard, Storage, etc.
//
//		default:
//			// Handle the case when hardwareType is not recognized
//			// Possibly return an error message or redirect appropriately
//			break;
//		}
//
//		if (itemToAdd != null) {
//			List<CartItem> cart = (List<CartItem>) session.getAttribute("cart");
//			if (cart == null) {
//				cart = new ArrayList<>();
//			}
//			cart.add(itemToAdd);
//			session.setAttribute("cart", cart);
//		}
//
//		return "redirect:/";
//	}

	//Following methods will be used to convert hardware entities to CartItems
	private CartItem convertCPUToCartItem(CPU cpu) {
		CartItem cartItem = new CartItem();
		cartItem.setId(cpu.getId()); // Assuming both CPU and CartItem have ID field
		cartItem.setType("CPU"); // Set type as CPU
		cartItem.setModel(cpu.getModel());
		cartItem.setManufacturer(cpu.getManufacturer());
		cartItem.setPrice(cpu.getPrice());
		cartItem.setDescription(cpu.getDescription());

		return cartItem;
	}

	private CartItem convertGraphicsCardToCartItem(GraphicsCard gpu) {
		CartItem cartItem = new CartItem();
		cartItem.setId(gpu.getId());
		cartItem.setType("GPU"); // Set type as GPU
		cartItem.setModel(gpu.getModel());
		cartItem.setManufacturer(gpu.getManufacturer());
		cartItem.setPrice(gpu.getPrice());
		cartItem.setDescription(gpu.getDescription());

		return cartItem;
	}

	// Methods to convert other hardware entities to CartItem
	// ... Implement similar conversion methods for Memory, Motherboard, Storage,
	// etc.
}
