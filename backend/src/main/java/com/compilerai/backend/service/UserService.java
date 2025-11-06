package com.compilerai.backend.service;

import com.compilerai.backend.model.Submission;
import com.compilerai.backend.model.User;
import com.compilerai.backend.repository.SubmissionRepository;
import com.compilerai.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SubmissionRepository submissionRepository;

    public Map<String, Object> getUserProfile(String userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Count accepted submissions
        List<Submission> submissions = submissionRepository.findByUserId(userId);
        long totalSolved = submissions.stream()
                .filter(s -> "Accepted".equals(s.getVerdict()))
                .map(Submission::getProblemId)
                .distinct()
                .count();

        Map<String, Object> profile = new HashMap<>();
        profile.put("name", user.getName());
        profile.put("email", user.getEmail());
        profile.put("role", user.getRole());
        profile.put("totalSolved", totalSolved);
        profile.put("totalSubmissions", submissions.size());

        return profile;
    }
}
