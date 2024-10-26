package com.beadando.javabeadando.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.beadando.javabeadando.entity.User;
import com.beadando.javabeadando.service.UserService;

@Controller
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService; // Ensure this service is defined and wired correctly

    @GetMapping("/login")
    public String showLoginPage() {
        return "login"; // Return to the login page (login.html)
    }

    @PostMapping("/loginposttest") // Ensure this matches the form action
    public String loginWithCredentials(@RequestParam String username,
                                       @RequestParam String password,
                                       Model model) {
        logger.info("Login attempt with username: {}", username);

        // Implement your authentication logic here
        boolean isAuthenticated = userService.authenticate(username, password); // Adjust as needed

        if (isAuthenticated) {
            logger.info("User {} authenticated successfully.", username);
            return "redirect:/"; // Redirect to the homepage on successful login
        } else {
            logger.warn("Authentication failed for user: {}", username);
            model.addAttribute("errorMessage", "Invalid username or password."); // Add an error message to the model
            return "redirect:/loginposttest"; // Return to the login page on failure
        }
    }

    @GetMapping("/register")
    public String register() {
        return "register"; // Return to the registration page (register.html)
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute User user) {
        userService.saveUser(user); // Implement saving logic in UserService
        return "redirect:/login"; // Redirect to the login page after registration
    }

    @GetMapping("/")
    public String home(Model model) {
        return "index"; // Return to the homepage (index.html)
    }
    @GetMapping("/loginposttest")
    public String loginposttest() {
        return "loginposttest"; // Return to the registration page (register.html)
    }
}
