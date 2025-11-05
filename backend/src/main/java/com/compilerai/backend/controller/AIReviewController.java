package com.compilerai.backend.controller;

import com.compilerai.backend.dto.AIReviewRequest;
import com.compilerai.backend.dto.AIReviewResponse;
import com.compilerai.backend.service.AIReviewService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class AIReviewController {

    @Autowired
    private AIReviewService aiReviewService;

    @PostMapping("/ai-review")
    public ResponseEntity<?> getAIReview(@Valid @RequestBody AIReviewRequest request) {
        try {
            AIReviewResponse response = aiReviewService.getAIReview(request);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("message", "Failed to get AI review");
            error.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
        }
    }
}
