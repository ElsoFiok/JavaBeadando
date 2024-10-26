package com.beadando.javabeadando.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    @Autowired
        private UserRepository repo;
    public users login(String username, String password){
        Users user = repo.findByUsernameAndPassword(username,password);
        return user;
    }
}
