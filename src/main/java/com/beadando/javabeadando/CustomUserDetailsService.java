package com.beadando.javabeadando;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepo; // Your UserRepository

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User useryay = userRepo.findByName(username) // Adjust this method based on your repository
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));

        return useryay; // Return your CustomUserDetails
    }

    private Collection<? extends GrantedAuthority> getAuthorities(User user) {
        // Assuming you have a getRole() method in your User class
        return List.of(new SimpleGrantedAuthority(user.getRole()));
    }
}



