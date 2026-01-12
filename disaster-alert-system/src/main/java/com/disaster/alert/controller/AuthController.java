package com.disaster.alert.controller;

import com.disaster.alert.model.User;
import com.disaster.alert.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    private final UserRepository userRepository;

    public AuthController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // ================= REGISTER =================
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user) {

        if (userRepository.existsByEmail(user.getEmail())) {
            return ResponseEntity.badRequest().body("Email already registered");
        }

        userRepository.save(user);
        return ResponseEntity.ok(user);
    }

    // ================= LOGIN =================
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User loginData) {

        Optional<User> optionalUser =
                userRepository.findByEmail(loginData.getEmail());

        if (optionalUser.isEmpty()) {
            return ResponseEntity.status(401).body("Invalid email");
        }

        User user = optionalUser.get();

        if (!user.getPassword().equals(loginData.getPassword())) {
            return ResponseEntity.status(401).body("Invalid password");
        }

        if (!user.getRole().equalsIgnoreCase(loginData.getRole())) {
            return ResponseEntity.status(403).body("Role mismatch");
        }

        return ResponseEntity.ok(user);
    }
}
