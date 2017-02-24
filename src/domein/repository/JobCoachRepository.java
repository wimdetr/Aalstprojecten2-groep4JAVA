/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domein.repository;

import domein.JobCoach;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import persistentie.JobCoachMapper;

/**
 *
 * @author ~dreeki~
 */
public class JobCoachRepository {

    private List<JobCoach> lijst;

    private JobCoachMapper jobCoachMapper;

    public JobCoachRepository() {
        lijst = new ArrayList<>();
        jobCoachMapper = new JobCoachMapper();
        fillUpWithDummyData();
    }

    public void fillUpWithDummyData() {
        JobCoach jos = new JobCoach("Vermeulen", "Jos", "joske@gmail.com", "Dalificom NV",
                "Gaston Berghmansstraat", "12", 9300, "Aalst");
        JobCoach anita = new JobCoach("Peron", "Anita", "anita@gmail.com", "BVBA Argentina",
                "Juan Peronstraat", "1", 1200, "Buenos Aires");
        JobCoach alan = new JobCoach("Turing", "Alan", "alan@gmail.com", "Enigma Inc.",
                "Great Britainstraat", "1", 1000, "London");
        lijst.add(jos);
        lijst.add(anita);
        lijst.add(alan);

    }

    public List<JobCoach> getLijst() {
        return lijst;
    }

    public List<JobCoach> zoekVoornaam(String query) {
        return lijst.stream()
                .filter(p -> p.getVoornaam().contains(query))
                .collect(Collectors.toList());
    }

    public List<JobCoach> zoekNaam(String query) {
        return lijst.stream()
                .filter(p -> p.getNaam().contains(query))
                .collect(Collectors.toList());
    }

    public List<JobCoach> zoekOrganisatie(String query) {
        return lijst.stream()
                .filter(p -> p.getOrganisatie().contains(query))
                .collect(Collectors.toList());
    }

    public List<JobCoach> zoekEmail(String query) {
        return lijst.stream()
                .filter(p -> p.getEmail().contains(query))
                .collect(Collectors.toList());
    }

    public List<JobCoach> zoekPostCode(String query) {
        return lijst.stream()
                .filter(p -> Integer.toString(p.getPostcode()).equals(query))
                .collect(Collectors.toList());
    }

    public List<JobCoach> zoekGemeente(String query) {
        return lijst.stream()
                .filter(p -> p.getGemeente().contains(query))
                .collect(Collectors.toList());
    }
}
