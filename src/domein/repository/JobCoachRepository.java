/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domein.repository;

import domein.JobCoach;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
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
        jobCoachMapper.getAll();
    }

    public void fillUpWithDummyData() {
        JobCoach jos = new JobCoach("Vermeulen", "Jos", "joske@gmail.com", "Dalificom NV",
                "Gaston Berghmansstraat", "12", 9300, "Aalst","");
        JobCoach anita = new JobCoach("Peron", "Anita", "anita@gmail.com", "BVBA Argentina",
                "Juan Peronstraat", "1", 1200, "Buenos Aires","");
        JobCoach alan = new JobCoach("Turing", "Alan", "alan@gmail.com", "Enigma Inc.",
                "Great Britainstraat", "1", 1000, "London","");
        JobCoach jos2 = new JobCoach("Vermeulen", "Jos", "joske@gmail.com", "Dalificom NV",
                "Gaston Berghmansstraat", "12", 9300, "Aalst","");
        JobCoach anita2 = new JobCoach("Peron", "Anita", "anita@gmail.com", "BVBA Argentina",
                "Juan Peronstraat", "1", 1200, "Buenos Aires","");
        JobCoach alan2 = new JobCoach("Turing", "Alan", "alan@gmail.com", "Enigma Inc.",
                "Great Britainstraat", "1", 1000, "London","");
        JobCoach jos3 = new JobCoach("Vermeulen", "Jos", "joske@gmail.com", "Dalificom NV",
                "Gaston Berghmansstraat", "12", 9300, "Aalst","A");
        JobCoach anita3 = new JobCoach("Peron", "Anita", "anita@gmail.com", "BVBA Argentina",
                "Juan Peronstraat", "1", 1200, "Buenos Aires","");
        JobCoach alan3 = new JobCoach("Turing", "Alan", "alan@gmail.com", "Enigma Inc.",
                "Great Britainstraat", "1", 1000, "London","");
        lijst.add(jos);
        lijst.add(anita);
        lijst.add(alan);
        lijst.add(jos2);
        lijst.add(anita2);
        lijst.add(alan2);
        lijst.add(jos3);
        lijst.add(anita3);
        lijst.add(alan3);
    }

    public List<JobCoach> getLijst() {
        return lijst;
    }

    public List<JobCoach> zoekVoornaam(String query) {
        return lijst.stream()
                .filter(p -> p.getVoornaam().toLowerCase().startsWith(query.toLowerCase()))
                .collect(Collectors.toList());
    }

    public List<JobCoach> zoekNaam(String query) {
        return lijst.stream()
                .filter(p -> p.getNaam().toLowerCase().startsWith(query.toLowerCase()))
                .collect(Collectors.toList());
    }

    public List<JobCoach> zoekStraat(String query) {
        return lijst.stream()
                .filter(p -> p.getStraat().toLowerCase().startsWith(query.toLowerCase()))
                .collect(Collectors.toList());
    }

    public List<JobCoach> zoekOrganisatie(String query) {
        return lijst.stream()
                .filter(p -> p.getOrganisatie().toLowerCase().startsWith(query.toLowerCase()))
                .collect(Collectors.toList());
    }

    public List<JobCoach> zoekEmail(String query) {
        return lijst.stream()
                .filter(p -> p.getEmail().toLowerCase().startsWith(query.toLowerCase()))
                .collect(Collectors.toList());
    }

    public List<JobCoach> zoekPostCode(String query) {
        return lijst.stream()
                .filter(p -> Integer.toString(p.getPostcode()).startsWith(query))
                .collect(Collectors.toList());
    }

    public List<JobCoach> zoekGemeente(String query) {
        return lijst.stream()
                .filter(p -> p.getGemeente().toLowerCase().startsWith(query.toLowerCase()))
                .collect(Collectors.toList());
    }
}
