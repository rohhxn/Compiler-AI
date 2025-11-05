package com.compilerai.backend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.index.Indexed;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "problems")
public class Problem {
    
    @Id
    private String id;
    
    @Indexed(unique = true)
    private String title;
    
    private String description;
    
    private String inputFormat;
    
    private String outputFormat;
    
    private String difficulty; // "Easy", "Medium", or "Hard"
    
    private List<String> tags = new ArrayList<>();
    
    private String createdBy;
    
    private List<TestCase> testCases = new ArrayList<>();
    
    @CreatedDate
    private LocalDateTime createdAt;
    
    @LastModifiedDate
    private LocalDateTime updatedAt;
}
