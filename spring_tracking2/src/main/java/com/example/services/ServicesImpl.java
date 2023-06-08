package com.example.services;

import com.example.entities.Activite;
import com.example.entities.Planning;
import com.example.entities.Point;
import com.example.entities.Sportif;
import com.example.repos.ActiviteRepo;
import com.example.repos.SportifRepo;
import com.example.services.interfaces.IServices;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@AllArgsConstructor
public class ServicesImpl implements IServices {
    private SportifRepo sportifRepo;
    private ActiviteRepo activiteRepo;

    @Override
    public Sportif saveSportif(Sportif sportif) {
        return sportifRepo.save(sportif);
    }

    @Override
    public List<Sportif> getAllSportifs() {
        return sportifRepo.findAll();
    }

    @Override
    public Sportif getSportif(Long id) {
        return null;
    }

    @Override
    public void deleteSportif(Long id) {

    }

    @Override
    public Activite saveActivite(Long idSportif, Activite activite) {
        Optional<Sportif> sportif = sportifRepo.findById(idSportif);
        activite.setSportif(sportif.get());
        return activiteRepo.save(activite);
    }

    @Override
    public List<Activite> activiteHistory(Long idSportif) {
        System.out.println(idSportif);
        return activiteRepo.findBySportifId(idSportif);
    }

    @Override
    public Planning savePlanning(Planning planning) {
        return null;
    }

    @Override
    public Point savePoint(Point point) {
        return null;
    }
}
