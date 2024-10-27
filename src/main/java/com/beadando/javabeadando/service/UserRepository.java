package com.beadando.javabeadando.service;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.security.core.userdetails.User;

public interface UserRepository extends CrudRepository<User, Integer>
{
    Optional<User> findByUsername(String username);
}
