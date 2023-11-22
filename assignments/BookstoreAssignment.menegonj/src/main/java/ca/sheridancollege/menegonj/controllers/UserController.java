package ca.sheridancollege.menegonj.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ca.sheridancollege.menegonj.database.DatabaseAccess;
import jakarta.servlet.http.HttpSession;

public class UserController {

    @Autowired
    @Lazy
    private DatabaseAccess da; 

    // Mapping for Login
    @GetMapping("/login")
    public String login() {
        return "login";
    }
    
    // Mapping for Secure Admin Page
    @GetMapping("/secure")
    public String secureAdminPage() {
        return "secure/admin/adminpage";
    }
    
    // Mapping for Permission Denied
    @GetMapping("/permission-denied")
    public String permissionDenied() {
        return "/error/permission-denied";
    }
    
    // Mapping for Register Page
    @GetMapping("/register")
    public String getRegister() {
        return "register";
    }

    // Post Mapping after a User Registers
    @PostMapping("/register")
    public String postRegister(@RequestParam String userName,
                               @RequestParam String email,
                               @RequestParam String password) {
        da.addUser(userName, email, password);

        // Assuming you can retrieve the user's ID after registration
        String userId = da.findUserAccount(userName).getUsername();
        da.addRole(userId, Long.valueOf(1)); // Assuming '1' is the role ID for 'USER'

        return "redirect:/secure/guest/bookstore";
    }

    // Login Process
    @PostMapping("/login")
    public String loginProcess(@RequestParam String username,
                               @RequestParam String password,
                               HttpSession session) {
        // Validate the user's credentials against the database
        if (da.validateUserCredentials(username, password)) {
            // If credentials are valid, set user information in session
            session.setAttribute("username", username);
            return "redirect:/secure/guest/bookstore"; // Redirect to the main page or user-specific page
        } else {
            // Credentials are invalid, handle the authentication failure
            return "redirect:/login?error"; // Redirect back to the login page with an error message
        }
    }
}
