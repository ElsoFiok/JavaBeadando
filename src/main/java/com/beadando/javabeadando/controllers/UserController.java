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
    private UserService userService;

    @GetMapping("/")
    public String home() {
        return "index"; // returns index.html
    }

    @GetMapping("/register")
    public String register() {
        return "register"; // returns register.html
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute User user) {
        userService.saveUser(user);
        return "redirect:/login"; // Redirect to login page after registration
    }

    @GetMapping("/login")
    public String login() {
        return "login"; // returns register.html
    }

    @PostMapping("/login")
    public String loginmania() {
        return "redirect:/loginintermediate"; // Redirect to login page after registration
    }
    @GetMapping("/loginintermediate")
    public String showLoginIntermediatePage(Model model) {
        model.addAttribute("message", "This is the intermediate login page."); // Set a message for the view
        return "loginintermediate"; // Return the name of your HTML template (e.g., loginintermediate.html)
    }

}
