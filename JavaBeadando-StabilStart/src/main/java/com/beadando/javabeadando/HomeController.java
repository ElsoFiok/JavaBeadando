package com.beadando.javabeadando;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Sort;
import java.sql.Timestamp;
import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private JelentkezesRepository jelentkezesRepository;

    @Autowired
    private JelentkezoRepository jelentkezoRepository;

    @Autowired
    private KepzesRepository kepzesRepository;
    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private MessageService messageService; // Add the MessageService

    @GetMapping("/")
    public String home() {
        return "index"; // Show the index.html page
    }

    @GetMapping("/home")
    public String user(Model model) {
        return "user";
    }


    @GetMapping("/admin/home")
    public String admin(Model model) {
        List<Message> messageslista = messageRepository.findAll(Sort.by(Sort.Direction.DESC, "ido"));
        System.out.println("Number of messages: " + messageslista.size());
        model.addAttribute("messageslista", messageslista);
        return "admin";
    }


    @GetMapping("/regisztral")
    public String greetingForm(@RequestParam(value = "error", required = false) String error, Model model) {
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

    @GetMapping("/message")
    public String message(Model model) {
        return "message"; // Returns the message.html page
    }

    @PostMapping("/sendMessage")
    public String sendMessage(@RequestParam("uzenet") String uzenet, Authentication authentication) {
        System.out.println();
        int senderId=0;
        if (authentication != null && authentication.isAuthenticated()) {
            Object principal = authentication.getPrincipal();
            if (principal instanceof User) {
                User userDetails = (User) principal;
                senderId = userDetails.getId();
            }
        }
        Message newMessage = new Message();
        newMessage.setUzenet(uzenet);
        newMessage.setKuldoid(senderId);
        messageRepository.save(newMessage);
        return "redirect:/";
    }


}