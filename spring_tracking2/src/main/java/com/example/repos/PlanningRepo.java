package com.example.repos;

import com.example.entities.Planning;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlanningRepo extends JpaRepository<Planning, Long> {
}

