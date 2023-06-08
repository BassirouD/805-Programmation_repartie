package com.example.repos;

import com.example.entities.Activite;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ActiviteRepo extends JpaRepository<Activite, Long> {
    List<Activite> findBySportifId(Long sportifId);
}
