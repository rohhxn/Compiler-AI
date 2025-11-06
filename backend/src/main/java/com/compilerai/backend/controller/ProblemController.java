package com.compilerai.backend.controller;

import com.compilerai.backend.dto.ProblemRequest;
import com.compilerai.backend.model.Problem;
import com.compilerai.backend.service.ProblemService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/problems")
public class ProblemController {

    @Autowired
    private ProblemService problemService;

    @PostMapping
    public ResponseEntity<?> createProblem(
            @Valid @RequestBody ProblemRequest request,
            HttpServletRequest httpRequest) {
        try {
            String userId = (String) httpRequest.getAttribute("userId");
            String role = (String) httpRequest.getAttribute("role");
            
            if (!"admin".equals(role)) {
                return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body(Map.of("message", "Only admins can create problems"));
            }
            
            Problem problem = problemService.createProblem(request, userId);
            return ResponseEntity.status(HttpStatus.CREATED).body(problem);
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("message", "Server error creating problem");
            error.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
        }
    }

    @GetMapping
    public ResponseEntity<?> getAllProblems() {
        try {
            List<Problem> problems = problemService.getAllProblems();
            return ResponseEntity.ok(problems);
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("message", "Failed to fetch problems");
            error.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getProblemById(@PathVariable String id) {
        try {
            Problem problem = problemService.getProblemById(id);
            return ResponseEntity.ok(problem);
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateProblem(
            @PathVariable String id,
            @Valid @RequestBody ProblemRequest request,
            HttpServletRequest httpRequest) {
        try {
            String role = (String) httpRequest.getAttribute("role");
            
            if (!"admin".equals(role)) {
                return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body(Map.of("message", "Only admins can update problems"));
            }
            
            Problem updated = problemService.updateProblem(id, request);
            return ResponseEntity.ok(updated);
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("message", "Error updating problem");
            error.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProblem(@PathVariable String id, HttpServletRequest httpRequest) {
        try {
            String role = (String) httpRequest.getAttribute("role");
            
            if (!"admin".equals(role)) {
                return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body(Map.of("message", "Only admins can delete problems"));
            }
            
            problemService.deleteProblem(id);
            Map<String, String> response = new HashMap<>();
            response.put("message", "Problem deleted successfully");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
        }
    }
}
