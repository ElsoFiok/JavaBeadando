package com.beadando.javabeadando;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class HomeController {
    @GetMapping("/")
    public String home() {
        return "index"; // Show the index.html page
    }
    @GetMapping("/home")
    public String user(Model model) {
        return "user";
    }
    @GetMapping("/admin/home")
    public String admin() {
        return "admin";
    }
    @GetMapping("/regisztral")
    public String greetingForm(@RequestParam(value = "error", required = false) String error,Model model) {
        model.addAttribute("reg", new User());
        if (error != null) {
            model.addAttribute("errorMessage", "Hiba történt a regisztráció során!");
        }
        return "regisztral";
    }
    @GetMapping("/login")
    String login() {
        return "login";
    }
    @Autowired
    private UserRepository userRepo;
    @PostMapping("/regisztral_feldolgoz")
    public String Regisztráció(@ModelAttribute User user, Model model) {

        for (User felhasznalo2 : userRepo.findAll()) {
            if (felhasznalo2.getName().equals(user.getName())) {
                return "redirect:/regisztral?error=true";
            }
        }

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole("ROLE_USER");
        userRepo.save(user);
        return "login";
    }
    @Autowired
    private JelentkezesRepository jelentkezesRepository;

    @Autowired
    private JelentkezoRepository jelentkezoRepository;

    @Autowired
    private KepzesRepository kepzesRepository;

    @GetMapping("/adatbazis")
    public String viewDatabase(Model model) {
        List<Jelentkezes> jelentkezeseklistaja = jelentkezesRepository.findAll();
        model.addAttribute("jelentkezeseklistaja", jelentkezeseklistaja);

        List<Jelentkezo> jelentkezoklistaja = jelentkezoRepository.findAll();
        model.addAttribute("jelentkezoklistaja", jelentkezoklistaja);

        List<Kepzes> kepzeseklistaja = kepzesRepository.findAll();
        model.addAttribute("kepzeseklistaja", kepzeseklistaja);

        return "adatbazis";
    }


}


