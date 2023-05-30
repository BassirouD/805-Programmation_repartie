package com.example.tracking.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Activite {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;
    @ManyToOne
    private Sportif sportif;
    @OneToMany(mappedBy = "activite", fetch = FetchType.EAGER)
    private Collection<Planning> plannings;

    public Activite(Long id, String nom) {
        this.id = id;
        this.nom = nom;
    }
}
