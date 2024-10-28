package com.beadando.javabeadando;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sendMessage") // Change to avoid conflict with HomeController
public class MessageController {

    @Autowired
    private MessageRepository messageRepository;

    @PostMapping("/sendMessage")
    public String sendMessage(@RequestParam("uzenet") String uzenet, Authentication authentication) {
        System.out.println("Received message: " + uzenet);
        int senderId = 0;

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

        return "Message sent!";

        //curl parancs : curl -X POST http://localhost:8080/sendMessage/sendMessage -H "Content-Type: application/x-www-form-urlencoded" -d "uzenet=Hello%20World"
    }
}
