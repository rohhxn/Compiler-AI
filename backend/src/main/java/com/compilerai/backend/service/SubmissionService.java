package com.compilerai.backend.service;

import com.compilerai.backend.dto.CompilerResponse;
import com.compilerai.backend.dto.SubmissionRequest;
import com.compilerai.backend.model.Problem;
import com.compilerai.backend.model.Submission;
import com.compilerai.backend.model.TestCase;
import com.compilerai.backend.model.TestResult;
import com.compilerai.backend.repository.ProblemRepository;
import com.compilerai.backend.repository.SubmissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SubmissionService {

    @Autowired
    private SubmissionRepository submissionRepository;

    @Autowired
    private ProblemRepository problemRepository;

    @Autowired
    private CompilerService compilerService;

    public Submission submitCode(SubmissionRequest request, String userId) {
        Problem problem = problemRepository.findById(request.getProblemId())
                .orElseThrow(() -> new RuntimeException("Problem not found"));

        if (problem.getTestCases() == null || problem.getTestCases().isEmpty()) {
            throw new RuntimeException("No test cases found for this problem");
        }

        List<TestResult> testResults = new ArrayList<>();
        boolean allPassed = true;

        for (TestCase testCase : problem.getTestCases()) {
            CompilerResponse response = compilerService.runCode(
                    request.getLanguage(),
                    request.getCode(),
                    testCase.getInput()
            );

            String actualOutput = response.getOutput() != null ? response.getOutput().trim() : "";
            String expectedOutput = testCase.getExpectedOutput().trim();
            boolean passed = actualOutput.equals(expectedOutput);

            if (!passed) {
                allPassed = false;
            }

            TestResult testResult = new TestResult(
                    testCase.getInput(),
                    testCase.getExpectedOutput(),
                    actualOutput,
                    passed
            );
            testResults.add(testResult);
        }

        String verdict = allPassed ? "✅ Passed" : "❌ Failed";

        Submission submission = new Submission();
        submission.setProblemId(request.getProblemId());
        submission.setUserId(userId);
        submission.setCode(request.getCode());
        submission.setLanguage(request.getLanguage());
        submission.setIsCorrect(allPassed);
        submission.setVerdict(verdict);
        submission.setTestResults(testResults);

        // Store last test case details for backward compatibility
        if (!testResults.isEmpty()) {
            TestResult lastResult = testResults.get(testResults.size() - 1);
            submission.setInput(lastResult.getInput());
            submission.setExpectedOutput(lastResult.getExpectedOutput());
            submission.setActualOutput(lastResult.getActualOutput());
        }

        return submissionRepository.save(submission);
    }

    public List<Submission> getUserSubmissions(String userId) {
        return submissionRepository.findByUserIdOrderByCreatedAtDesc(userId);
    }
}
