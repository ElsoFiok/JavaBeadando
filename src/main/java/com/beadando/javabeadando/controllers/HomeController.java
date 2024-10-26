package com.beadando.javabeadando.controllers;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping("/home") // Change the path
    public String index() {
        return "home"; // your view name
    }
}
