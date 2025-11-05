package com.compilerai.backend.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AIReviewRequest {
    
    private String code;
    
    @NotBlank(message = "Problem title is required")
    private String problemTitle;
    
    @NotBlank(message = "Problem description is required")
    private String problemDescription;
}
