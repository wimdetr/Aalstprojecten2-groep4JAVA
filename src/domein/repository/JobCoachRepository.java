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
    }
    
    
}
