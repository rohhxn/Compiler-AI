package com.compilerai.backend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TestResult {
    private String input;
    private String expectedOutput;
    private String actualOutput;
    private Boolean isCorrect;
}
