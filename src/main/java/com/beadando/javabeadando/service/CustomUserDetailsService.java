package com.beadando.javabeadando.service;

import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired private UserRepository userRepo; // Dependency injection
    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User user = userRepo.findByUsername(userName)
                .orElseThrow(() -> new UsernameNotFoundException("Username " + userName + " not found"));
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
                getAuthorities(user));
    }
    // A kiválasztott felhasználó szerepeinek lekérdezése:.
// A szerepek is olyan tulajdonsága a felhasználónak, mint a neve, címe, telefonszáma, …
    private static Collection<? extends GrantedAuthority> getAuthorities(User user) {
        Collection<GrantedAuthority> authorities = user.getAuthorities();
        return authorities;
    }
}