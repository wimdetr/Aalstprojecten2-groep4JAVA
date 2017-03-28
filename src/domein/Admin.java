/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domein;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.OneToMany;
import javax.persistence.FetchType;
import javax.persistence.Entity;

/**
 *
 * @author ~dreeki~
 */
@Entity
@DiscriminatorValue(value = "Admin")
public class Admin extends Persoon{
    private final long serialVersionUID = 1L;
    
    @OneToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    private List<JobCoach> coaches;

    protected Admin() {
    }

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
    
    public boolean controleerOfCoachMetEmailBestaat(String email){
        return coaches.stream().anyMatch(c -> c.getEmail().equals(email));
    }
    
    public JobCoach geefCoachMetEmail(String email){
        return coaches.stream().filter(c -> c.getEmail().equals(email)).findFirst().get();
    }
}
