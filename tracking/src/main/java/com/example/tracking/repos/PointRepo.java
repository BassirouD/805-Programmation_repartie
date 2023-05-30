package com.example.tracking.repos;

import com.example.tracking.entities.Point;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PointRepo extends JpaRepository<Point, Long> {
}

