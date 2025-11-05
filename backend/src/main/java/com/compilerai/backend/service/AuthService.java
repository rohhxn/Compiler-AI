package com.compilerai.backend.service;

import com.compilerai.backend.dto.AuthResponse;
import com.compilerai.backend.dto.LoginRequest;
import com.compilerai.backend.dto.RegisterRequest;
import com.compilerai.backend.model.User;
import com.compilerai.backend.repository.UserRepository;
import com.compilerai.backend.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    public String register(RegisterRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email already in use");
        }

        User user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(request.getRole() != null ? request.getRole() : "user");

        userRepository.save(user);
        
        return "User registered successfully";
    }

    public AuthResponse login(LoginRequest request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }

        String token = jwtUtil.generateToken(user.getId(), user.getRole());

        AuthResponse.UserInfo userInfo = new AuthResponse.UserInfo(
                user.getName(),
                user.getEmail(),
                user.getRole()
        );

        return new AuthResponse(token, userInfo);
    }
}
