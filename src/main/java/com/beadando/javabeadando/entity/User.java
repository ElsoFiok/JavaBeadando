package com.beadando.javabeadando.entity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import jakarta.persistence.*;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "users") // Adjust the table name as needed
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id; // Changed from Long to int

    private String username;

    private String password;

    private String role; // Added role field

    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(() -> role); // Use a role granted authority
    }
    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public boolean isAccountNonExpired() {
        return true; // Adjust as necessary
    }

    public boolean isAccountNonLocked() {
        return true; // Adjust as necessary
    }

    public boolean isCredentialsNonExpired() {
        return true; // Adjust as necessary
    }

    public boolean isEnabled() {
        return true; // Adjust as necessary
    }
}
