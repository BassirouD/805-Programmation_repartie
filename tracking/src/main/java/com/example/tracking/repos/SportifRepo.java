package com.example.tracking.repos;

import com.example.tracking.entities.Sportif;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SportifRepo extends JpaRepository<Sportif, Long> {
}
