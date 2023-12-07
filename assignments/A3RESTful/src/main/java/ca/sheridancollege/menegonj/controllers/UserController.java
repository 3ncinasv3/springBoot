package ca.sheridancollege.menegonj.controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import ca.sheridancollege.menegonj.beans.*;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import ca.sheridancollege.menegonj.database.DatabaseAccess;

@Controller
public class UserController {
	
	@Autowired
	private DatabaseAccess databaseAccess;
	
	// Landing page
	@GetMapping("/secure/user/index")
	public String indexPage(Model model, HttpSession session) {
		model.addAttribute("session", session);
		if (session.isNew()) {
			List<CartItem> cart = new ArrayList<>();
			session.setAttribute("cart", cart);
		}
//		session.setAttribute("cart", );
		System.out.println(session);
//		List<String> navigationLinks = Arrays.asList("CPU", "Graphics Card", "Memory", "Motherboard", "Storage");
//		model.addAttribute("navigationLinks", navigationLinks);
		return "secure/user/index";
	}
	
	// CPU page
	@GetMapping("/secure/user/cpu")
	public String cpuPage(Model model) {
		List<CPU> cpus = databaseAccess.getAllCPUs();
		model.addAttribute("cpus", cpus);
		return "/secure/user/cpu";
	}

	// CPU details page
	@GetMapping("/secure/user/cpu/{id}")
	public String cpuDetails(@PathVariable Long id, Model model) {
		CPU cpu = databaseAccess.getCPUById(id);
		model.addAttribute("cpu", cpu);
		return "/secure/user/cpu_details";
	}

	// GPU page
	@GetMapping("/secure/user/graphicscard")
	public String graphicsCardPage(Model model) {
		List<GraphicsCard> graphicsCards = databaseAccess.getAllGPUs();
		model.addAttribute("graphicsCards", graphicsCards);
		return "/secure/user/graphicscard";
	}

	// GPU details page
	@GetMapping("/secure/user/graphicscard/{id}")
	public String graphicsCardDetails(@PathVariable Long id, Model model) {
		GraphicsCard graphicsCard = databaseAccess.getGraphicsCardById(id);
		model.addAttribute("graphicsCard", graphicsCard);
		return "/secure/user/graphicscard_details";
	}

	// Memory page
	@GetMapping("/secure/user/memory")
	public String memoryPage(Model model) {
		List<Memory> memories = databaseAccess.getAllMemories();
		model.addAttribute("memories", memories);
		return "/secure/user/memory";
	}

	// Memory details page
	@GetMapping("/secure/user/memory/{id}")
	public String memoryDetails(@PathVariable Long id, Model model) {
		Memory memory = databaseAccess.getMemoryById(id);
		model.addAttribute("memory", memory);
		return "/secure/user/memory_details";
	}

	// Motherboard page
	@GetMapping("/secure/user/motherboard")
	public String motherboardPage(Model model) {
		List<Motherboard> motherboards = databaseAccess.getAllMotherboards();
		model.addAttribute("motherboards", motherboards);
		return "/secure/user/motherboard";
	}

	// Motherboard details page
	@GetMapping("/secure/user/motherboard/{id}")
	public String motherboardDetails(@PathVariable Long id, Model model) {
		Motherboard motherboard = databaseAccess.getMotherboardById(id);
		model.addAttribute("motherboard", motherboard);
		return "/secure/user/motherboard_details";
	}

	// Storage page
	@GetMapping("/secure/user/storage")
	public String storagePage(Model model) {
		List<Storage> storage = databaseAccess.getAllStorage();
		model.addAttribute("storage", storage);
		return "/secure/user/storage";
	}

	// Storage details page
	@GetMapping("/secure/user/storage/{id}")
	public String storageDetails(@PathVariable Long id, Model model) {
		Storage storage = databaseAccess.getStorageById(id);
		model.addAttribute("storage", storage);
		return "/secure/user/storage_details";
	}

//	// Adding items to cart (CPU)
//	@PostMapping("/addToCartCPU/{id}")
//	public String addToCartCPU(@PathVariable Long id) {
//		CPU cpu = databaseAccess.getCPUById(id);
//		databaseAccess.addToCartCPU(cpu);
//		return "redirect:/cpu";
//	}
//
//	// Other mappings for adding GPU, Memory, Motherboard, Storage to cart...
//
//	// Handle redirection to registration when trying to add to cart
//	@GetMapping("/addToCartRedirect")
//	public String redirectToRegistration() {
//		return "redirect:/register"; // Redirects to registration page if trying to add to cart
//	}
}
