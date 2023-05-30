package com.example.tracking;

import com.example.tracking.entities.Activite;
import com.example.tracking.entities.Sportif;
import com.example.tracking.services.interfaces.IServices;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class TrackingApplication {

    public static void main(String[] args) {
        SpringApplication.run(TrackingApplication.class, args);
    }

    @Bean
    CommandLineRunner start(IServices iServices) {
        return args -> {
            /**
             iServices.saveSportif(new Sportif(null, "Diallo", "Bassirou", "diallo@gmail.com", "toor", 30, 20.0));
             iServices.saveSportif(new Sportif(null, "Ba", "Aziz", "aziz@gmail.com", "toor", 30, 20.0));
             iServices.saveSportif(new Sportif(null, "Gueye", "Mohamed", "momo@gmail.com", "toor", 30, 20.0));

             iServices.getAllSportifs().forEach(sportif -> {
             iServices.saveActivite(sportif.getId(), new Activite(null, "Vélo"));
             });**/
            iServices.activiteHistory(1L).forEach(System.out::println);
        };
    }

}
