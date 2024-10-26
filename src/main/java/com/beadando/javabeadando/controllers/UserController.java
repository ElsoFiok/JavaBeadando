package com.beadando.javabeadando.controller; // Adjust according to your actual package structure

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;

import com.beadando.javabeadando.entity.User; // Adjust the import according to your User entity package
import com.beadando.javabeadando.service.UserService; // Adjust the import according to your UserService package

@Controller
public class UserController {

    @Autowired
    private UserService userService; // Assume you have a UserService to handle user-related operations

    @GetMapping("/")
    public String home(Model model, Principal principal) {
        boolean loggedIn = principal != null;
        model.addAttribute("loggedIn", loggedIn);
        if (loggedIn) {
            model.addAttribute("username", principal.getName());
        }
        return "index"; // returns index.html
    }

    @GetMapping("/register")
    public String register() {
        return "register"; // returns register.html
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute User user) {
        userService.saveUser(user); // Save user
        return "redirect:/login"; // Redirect to login page
    }

    @GetMapping("/login")
    public String login() {
        return "login"; // returns login.html
    }
}

