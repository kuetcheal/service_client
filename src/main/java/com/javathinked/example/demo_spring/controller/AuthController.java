package com.javathinked.example.demo_spring.controller;

import com.javathinked.example.demo_spring.dto.LoginRequest;
import com.javathinked.example.demo_spring.dto.LoginResponse;
import com.javathinked.example.demo_spring.util.JwtUtil;
import jakarta.annotation.PostConstruct;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    // stockage très simple en mémoire pour la démo
    private static class UserInfo {
        String hash;
        List<String> roles;
        UserInfo(String hash, List<String> roles) {
            this.hash = hash;
            this.roles = roles;
        }
    }
    private final Map<String, UserInfo> USERS = new HashMap<>();

    public AuthController(PasswordEncoder passwordEncoder, JwtUtil jwtUtil) {
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    @PostConstruct
    public void init() {
        // utilisateurs de test (admin/admin, user/user)
        USERS.put("admin", new UserInfo(passwordEncoder.encode("admin"), List.of("ROLE_ADMIN")));
        USERS.put("user",  new UserInfo(passwordEncoder.encode("user"),  List.of("ROLE_USER")));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest req) {
        if (req == null || req.getUsername() == null || req.getPassword() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("error", "Credentials required"));
        }

        UserInfo info = USERS.get(req.getUsername());
        if (info == null || !passwordEncoder.matches(req.getPassword(), info.hash)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("error", "Invalid credentials"));
        }

        String token = jwtUtil.generate(req.getUsername(), info.roles);
        long expiresInSec = jwtUtil.getExpirationMs() / 1000; // nécessite getExpirationMs() dans JwtUtil

        return ResponseEntity.ok(new LoginResponse(token, "Bearer", expiresInSec));
    }
}
