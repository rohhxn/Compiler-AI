package com.compilerai.backend.repository;

import com.compilerai.backend.model.Problem;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProblemRepository extends MongoRepository<Problem, String> {
    Optional<Problem> findByTitle(String title);
    boolean existsByTitle(String title);
}
