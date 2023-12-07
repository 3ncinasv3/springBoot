package ca.sheridancollege.menegonj.controllers;

import ca.sheridancollege.menegonj.beans.User;
import ca.sheridancollege.menegonj.database.DatabaseAccess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserController {

    @Autowired
    private DatabaseAccess databaseAccess;
    
	@GetMapping("/register")
	public String getRegister() {
		return "register";
	}

	@PostMapping("/register") // Handles POST requests to /register endpoint
	public String register(User user, Model model) {
	    // Assuming your User class setters/getters are defined

	    if (!user.getPassword().equals(user.getPassword())) {
	        model.addAttribute("error", "Passwords do not match");
	        return "register"; // Return to the registration page with an error message
	    }

	    // Save user to the database
	    databaseAccess.insertUser(user);

	    // Add a message to display on the next page
	    model.addAttribute("message", "Thanks for signing up! Please click <a href='/'>this link</a> to begin shopping.");

	    return "registrationSuccess"; // This is the name of the new page to display after successful registration
	}

    @PostMapping("/login")
    public String login(User user, Model model) {
        // Retrieve user details from the database by username
        User retrievedUser = databaseAccess.getUserByUsername(user.getUsername());

        if (retrievedUser != null && retrievedUser.getPassword().equals(user.getPassword())) {
            // If the user exists and passwords match, consider it a successful login
            return "redirect:/dashboard"; // Redirect to a dashboard or home page after successful login
        } else {
            // If user doesn't exist or passwords don't match, show error message
            model.addAttribute("error", "Invalid username or password");
            return "login"; // Return back to the login page with an error message
        }
    }
}

