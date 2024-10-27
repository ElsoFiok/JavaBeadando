package com.beadando.javabeadando.service;
import java.util.Optional;
import com.beadando.javabeadando.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
public interface UserRepository extends JpaRepository<User, Integer>
{
    User findByUsername(String username);
}
