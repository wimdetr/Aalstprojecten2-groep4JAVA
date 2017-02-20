/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domein;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ~dreeki~
 */
public class Admin extends Persoon{
    private List<JobCoach> coaches;

    public Admin(String naam, String voornaam, String email) {
        super(naam, voornaam, email);
        coaches = new ArrayList<>();
    }

    public List<JobCoach> getCoaches() {
        return coaches;
    }
    
    public void setCoaches(List<JobCoach> lijst){
        coaches = lijst;
    }
    
    public JobCoach geefCoachMetEmail(String email){
        return coaches.stream().filter(c -> c.getEmail().equals(email)).findFirst().get();
    }
}
