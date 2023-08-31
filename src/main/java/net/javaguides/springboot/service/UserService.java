package net.javaguides.springboot.service;

import net.javaguides.springboot.model.User;
import net.javaguides.springboot.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;



    public ResponseEntity<User> registerUser(User user) {
        if (userRepository.findByUsername(user.getUsername()).isPresent()) {
            throw new RuntimeException("Username is already taken");
        }
        user.setPassword(user.getPassword()); // Store password as is (for now)
        userRepository.save(user);
        return ResponseEntity.ok(user);
    }

    public boolean login(User user) {
        Optional<User> existingUser = userRepository.findByUsername(user.getUsername());
        return existingUser.isPresent() && existingUser.get().getPassword().equals(user.getPassword());
    }



}