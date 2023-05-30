package com.example.tracking.repos;

import com.example.tracking.entities.Activite;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ActiviteRepo extends JpaRepository<Activite, Long> {
    List<Activite> findBySportifId(Long sportifId);
}
