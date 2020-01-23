package com.example.demo.storage;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.MatchResult;

@Repository
public interface MatchResultRepository extends JpaRepository<MatchResult, Long> {
}