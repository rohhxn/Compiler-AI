package com.compilerai.backend.dto;

import com.compilerai.backend.model.TestCase;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProblemRequest {
    
    @NotBlank(message = "Title is required")
    private String title;
    
    @NotBlank(message = "Description is required")
    private String description;
    
    @NotBlank(message = "Input format is required")
    private String inputFormat;
    
    @NotBlank(message = "Output format is required")
    private String outputFormat;
    
    @NotBlank(message = "Difficulty is required")
    private String difficulty;
    
    private List<String> tags;
    
    @NotNull(message = "Test cases are required")
    private List<TestCase> testCases;
}
