package com.example.user_service.controller;

import com.example.user_service.model.Role;
import com.example.user_service.model.User;
import com.example.user_service.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
//@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public User register(@RequestBody User user){
//        user.setRole(Role.valueOf("PASSENGER"));
        return authService.register(user);
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody User user){
        String token = authService.login(user);

        if (token == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("error", "Invalid credentials"));
        }
        return ResponseEntity.ok(Map.of("token", token));
    }
}
