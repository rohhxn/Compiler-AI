package com.compilerai.backend.service;

import com.compilerai.backend.dto.AIReviewRequest;
import com.compilerai.backend.dto.AIReviewResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AIReviewService {

    @Value("${gemini.api.key}")
    private String geminiApiKey;

    private static final String GEMINI_API_URL = "https://generativelanguage.googleapis.com/v1beta/models/gemini-1.5-flash:generateContent";

    public AIReviewResponse getAIReview(AIReviewRequest request) {
        String prompt;
        
        if (request.getCode() == null || request.getCode().trim().isEmpty()) {
            // No code provided - give hints
            prompt = String.format(
                    "The user has NOT entered any code.\n" +
                    "The problem is titled: \"%s\".\n" +
                    "Description: %s.\n" +
                    "Give them **only hints and intuition** for solving this problem in 3 bullet points.\n" +
                    "Do NOT reveal exact code.",
                    request.getProblemTitle(),
                    request.getProblemDescription()
            );
        } else {
            // Code provided - give review
            prompt = String.format(
                    "You are an AI code reviewer.\n" +
                    "Review the following code for the problem: \"%s\".\n" +
                    "Problem description: %s.\n" +
                    "Code:\n%s\n\n" +
                    "Give output in **structured format**:\n" +
                    "1. Review: (2 concise sentences about the quality of the code)\n" +
                    "2. Hints: (2–3 bullet points for improving the code or solving the problem better)\n" +
                    "Do NOT give the complete solution or exact code.",
                    request.getProblemTitle(),
                    request.getProblemDescription(),
                    request.getCode()
            );
        }

        try {
            WebClient webClient = WebClient.builder().build();
            
            Map<String, Object> requestBody = new HashMap<>();
            Map<String, Object> content = new HashMap<>();
            Map<String, String> part = new HashMap<>();
            part.put("text", prompt);
            content.put("parts", List.of(part));
            requestBody.put("contents", List.of(content));

            String responseText = webClient.post()
                    .uri(GEMINI_API_URL + "?key=" + geminiApiKey)
                    .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                    .bodyValue(requestBody)
                    .retrieve()
                    .bodyToMono(Map.class)
                    .map(response -> {
                        try {
                            List<Map<String, Object>> candidates = (List<Map<String, Object>>) response.get("candidates");
                            Map<String, Object> firstCandidate = candidates.get(0);
                            Map<String, Object> contentMap = (Map<String, Object>) firstCandidate.get("content");
                            List<Map<String, String>> parts = (List<Map<String, String>>) contentMap.get("parts");
                            return parts.get(0).get("text");
                        } catch (Exception e) {
                            return "Failed to parse AI response";
                        }
                    })
                    .block();

            if (request.getCode() == null || request.getCode().trim().isEmpty()) {
                return new AIReviewResponse(null, responseText);
            } else {
                // Try to split into review and hints
                String review = "";
                String hints = "";
                
                if (responseText.contains("Review:") && responseText.contains("Hints:")) {
                    String[] parts = responseText.split("Hints:", 2);
                    String reviewPart = parts[0].replace("Review:", "").trim();
                    hints = parts[1].trim();
                    review = reviewPart;
                } else {
                    review = responseText;
                }
                
                return new AIReviewResponse(review, hints);
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to get AI review: " + e.getMessage());
        }
    }
}
