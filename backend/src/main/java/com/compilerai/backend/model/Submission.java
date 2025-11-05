package com.compilerai.backend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "submissions")
public class Submission {
    
    @Id
    private String id;
    
    private String problemId;
    
    private String userId;
    
    private String code;
    
    private String language;
    
    private String input;
    
    private String expectedOutput;
    
    private String actualOutput;
    
    private Boolean isCorrect;
    
    private String verdict;
    
    private List<TestResult> testResults;
    
    @CreatedDate
    private LocalDateTime createdAt;
    
    @LastModifiedDate
    private LocalDateTime updatedAt;
}
