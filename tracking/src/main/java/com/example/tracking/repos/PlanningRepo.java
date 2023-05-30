package com.example.tracking.repos;

import com.example.tracking.entities.Planning;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlanningRepo extends JpaRepository<Planning, Long> {
}

