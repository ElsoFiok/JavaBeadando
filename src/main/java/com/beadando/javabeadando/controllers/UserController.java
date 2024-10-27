package com.beadando.javabeadando.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.beadando.javabeadando.service.UserService;

@Controller
public class UserController {

    @Autowired
    private UserService userService;
    @GetMapping("/login")
    public String showLoginPage(@RequestParam(required = false) String IncorrectPasswordOrUsername, Model model) {
        if (IncorrectPasswordOrUsername != null) {
            model.addAttribute("errorMessage", "Hibás felhasználónév és jelszó kombináció.");
        }
        return "login";
    }

    @PostMapping("/loginmania")
    public String loginWithCredentials(@RequestParam String username,
                                       @RequestParam String password,
                                       Model model) {
        System.out.println("Login attempt with username: "+username+" and password: "+ password);
        boolean isAuthenticated = userService.authenticate(username, password);
        if (isAuthenticated) {
            System.out.println("User "+ username+" authenticated successfully.");
            return "redirect:/";
        } else {
            System.out.println("Authentication failed for user: "+ username);
            model.addAttribute("errorMessage", "Invalid username or password.");
            return "redirect:/login?IncorrectPasswordOrUsername";
        }
    }

    @GetMapping("/register")
    public String register(@RequestParam(required = false) String UserExists, Model model) {
        if (UserExists != null) {
            model.addAttribute("errorMessage", "Ez a felhasználó már létezik!");
        }
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@RequestParam String username,
                               @RequestParam String password) {
        if(userService.saveUser(username, password)){
            return "redirect:/login";
        }else{
            return "redirect:/register?UserExists";
        }
    }

    @GetMapping("/")
    public String home(Model model) {
        return "index";
    }
    @GetMapping("/loginposttest")
    public String loginposttest() {
        return "loginposttest";
    }
}
