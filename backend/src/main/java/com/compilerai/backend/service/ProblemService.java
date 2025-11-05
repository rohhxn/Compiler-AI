package com.compilerai.backend.service;

import com.compilerai.backend.dto.ProblemRequest;
import com.compilerai.backend.model.Problem;
import com.compilerai.backend.repository.ProblemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProblemService {

    @Autowired
    private ProblemRepository problemRepository;

    public Problem createProblem(ProblemRequest request, String createdBy) {
        if (problemRepository.existsByTitle(request.getTitle())) {
            throw new RuntimeException("Problem with this title already exists");
        }

        Problem problem = new Problem();
        problem.setTitle(request.getTitle());
        problem.setDescription(request.getDescription());
        problem.setInputFormat(request.getInputFormat());
        problem.setOutputFormat(request.getOutputFormat());
        problem.setDifficulty(request.getDifficulty());
        problem.setTags(request.getTags());
        problem.setTestCases(request.getTestCases());
        problem.setCreatedBy(createdBy);

        return problemRepository.save(problem);
    }

    public List<Problem> getAllProblems() {
        return problemRepository.findAll();
    }

    public Problem getProblemById(String id) {
        return problemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Problem not found"));
    }

    public Problem updateProblem(String id, ProblemRequest request) {
        Problem problem = problemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Problem not found"));

        problem.setTitle(request.getTitle());
        problem.setDescription(request.getDescription());
        problem.setInputFormat(request.getInputFormat());
        problem.setOutputFormat(request.getOutputFormat());
        problem.setDifficulty(request.getDifficulty());
        problem.setTags(request.getTags());
        problem.setTestCases(request.getTestCases());

        return problemRepository.save(problem);
    }

    public void deleteProblem(String id) {
        if (!problemRepository.existsById(id)) {
            throw new RuntimeException("Problem not found");
        }
        problemRepository.deleteById(id);
    }
}
