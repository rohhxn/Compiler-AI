package com.compilerai.backend.repository;

import com.compilerai.backend.model.Submission;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubmissionRepository extends MongoRepository<Submission, String> {
    List<Submission> findByUserIdOrderByCreatedAtDesc(String userId);
    List<Submission> findByProblemId(String problemId);
}
