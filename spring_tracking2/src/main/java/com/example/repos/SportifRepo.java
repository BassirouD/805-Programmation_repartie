package com.example.repos;


import com.example.entities.Sportif;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SportifRepo extends JpaRepository<Sportif, Long> {
}
