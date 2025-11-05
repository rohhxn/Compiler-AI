package com.compilerai.backend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class TestController {

    @GetMapping("/")
    public ResponseEntity<String> root() {
        return ResponseEntity.ok("API is working 🚀");
    }

    @PostMapping("/test")
    public ResponseEntity<?> test() {
        Map<String, String> response = new HashMap<>();
        response.put("message", "Test route working");
        return ResponseEntity.ok(response);
    }
}
