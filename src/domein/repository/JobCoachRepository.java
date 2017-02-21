/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domein.repository;

import domein.JobCoach;
import java.util.ArrayList;
import java.util.List;
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
}
