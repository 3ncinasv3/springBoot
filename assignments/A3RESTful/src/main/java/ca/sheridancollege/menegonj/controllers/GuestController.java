package ca.sheridancollege.menegonj.controllers;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ca.sheridancollege.menegonj.beans.CPU;
import ca.sheridancollege.menegonj.beans.GraphicsCard;
import ca.sheridancollege.menegonj.beans.Memory;
import ca.sheridancollege.menegonj.beans.Motherboard;
import ca.sheridancollege.menegonj.beans.Storage;
import ca.sheridancollege.menegonj.database.DatabaseAccess;

@Controller
public class GuestController {

	@Autowired
	private DatabaseAccess databaseAccess;

	// Landing page
	@GetMapping("/")
	public String indexPage(Model model) {
		List<String> navigationLinks = Arrays.asList("CPU", "Graphics Card", "Memory", "Motherboard", "Storage");
		model.addAttribute("navigationLinks", navigationLinks);
		return "index";
	}
	
    @GetMapping("/error/permission-denied")
    public String permissionDeniedCPU() {
        return "/error/permission-denied";
    }
	
//	@GetMapping("/secure/user/")
//	public String secureIndexPage(Model model) {
//		List<String> navigationLinks = Arrays.asList("CPU", "Graphics Card", "Memory", "Motherboard", "Storage");
//		model.addAttribute("navigationLinks", navigationLinks);
//		return "/secure/user/";
//	}

	// Registration page
	@GetMapping("/register")
	public String registerPage() {
		return "register";
	}

	// Handling user registration
	@PostMapping("/register")
	public String registerUser(@RequestParam String userName, @RequestParam String email, @RequestParam String password) {
	    // Assuming 'da' refers to your database access object
	    // Add user and retrieve the generated userId
		databaseAccess.addUser(userName, email, password);
	    Long userId = databaseAccess.findUserAccount(userName).getUserId();
	    
	    // Assign a default role ID (change '1' to the correct default role ID)
	    // Adjust this according to your website's role setup
	    databaseAccess.addRole(userId, Long.valueOf(1));

	    // Redirect to the appropriate landing page or dashboard
	    return "redirect:/login";
	}

	// Login page
	@GetMapping("/login")
	public String loginPage() {
		return "login";
	}

//	// Handling user login
//	@PostMapping("/login")
//	public String loginUser(@RequestParam String username, @RequestParam String password) {
//		// Implement login logic using Spring Security
//		return "redirect:/"; // Redirect to a secure page after login
//	}
}