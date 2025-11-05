package com.compilerai.backend.controller;

import com.compilerai.backend.dto.AuthResponse;
import com.compilerai.backend.dto.LoginRequest;
import com.compilerai.backend.dto.RegisterRequest;
import com.compilerai.backend.service.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody RegisterRequest request) {
        try {
            String message = authService.register(request);
            Map<String, String> response = new HashMap<>();
            response.put("message", message);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest request) {
        try {
            AuthResponse response = authService.login(request);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error);
        }
    }

    @GetMapping("/me")
    public ResponseEntity<?> getMe(HttpServletRequest request) {
        String userId = (String) request.getAttribute("userId");
        String role = (String) request.getAttribute("role");
        
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Protected route accessed ✅");
        
        Map<String, String> user = new HashMap<>();
        user.put("userId", userId);
        user.put("role", role);
        response.put("user", user);
        
        return ResponseEntity.ok(response);
    }

    @GetMapping("/admin-test")
    public ResponseEntity<?> adminTest() {
        Map<String, String> response = new HashMap<>();
        response.put("message", "Admin route accessed ✅");
        return ResponseEntity.ok(response);
    }
}
