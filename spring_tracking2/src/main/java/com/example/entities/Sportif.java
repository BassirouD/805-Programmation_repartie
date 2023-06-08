package com.example.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Sportif {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;
    private String prenom;
    private String email;
    private String password;
    private int age;
    private Double poids;
    @OneToMany(mappedBy = "sportif", fetch = FetchType.EAGER)
    private List<Activite> activites;

    public Sportif(Long id, String nom, String prenom, String email, String password, int age, Double poids) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.password = password;
        this.age = age;
        this.poids = poids;
    }
}
