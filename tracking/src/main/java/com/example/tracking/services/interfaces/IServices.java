package com.example.tracking.services.interfaces;

import com.example.tracking.entities.Activite;
import com.example.tracking.entities.Planning;
import com.example.tracking.entities.Point;
import com.example.tracking.entities.Sportif;

import java.util.List;

public interface IServices {
    Sportif saveSportif(Sportif sportif);

    List<Sportif> getAllSportifs();

    Sportif getSportif(Long id);

    void deleteSportif(Long id);

    Activite saveActivite(Long idSportif, Activite activite);

    List<Activite> activiteHistory(Long idSportif);

    Planning savePlanning(Planning planning);

    Point savePoint(Point point);


}
