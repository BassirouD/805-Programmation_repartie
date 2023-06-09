package com.example.tracking.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Planning {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String date;
    private String heureD;
    private String heureF;
    @ManyToOne
    private Activite activite;
    @OneToMany(mappedBy = "planning", fetch = FetchType.EAGER)
    private List<Point> points;
}
