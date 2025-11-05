package com.compilerai.backend.service;

import com.compilerai.backend.dto.CompilerRequest;
import com.compilerai.backend.dto.CompilerResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class CompilerService {

    private final WebClient webClient;

    public CompilerService(@Value("${compiler.service.url}") String compilerUrl) {
        this.webClient = WebClient.builder()
                .baseUrl(compilerUrl)
                .build();
    }

    public CompilerResponse runCode(String language, String code, String input) {
        CompilerRequest request = new CompilerRequest(language, code, input);
        
        try {
            return webClient.post()
                    .uri("/run")
                    .bodyValue(request)
                    .retrieve()
                    .bodyToMono(CompilerResponse.class)
                    .block();
        } catch (Exception e) {
            CompilerResponse errorResponse = new CompilerResponse();
            errorResponse.setError("Failed to execute code: " + e.getMessage());
            return errorResponse;
        }
    }
}
