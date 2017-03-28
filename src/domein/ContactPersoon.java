/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domein;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 *
 * @author ~dreeki~
 */
@Entity
@DiscriminatorValue(value = "ContactPersoon")
public class ContactPersoon extends Persoon{
    private final long serialVersionUID = 1L;

    protected ContactPersoon() {
    }
    
    

    public ContactPersoon(String naam, String voornaam, String email) {
        super(naam, voornaam, email);
    }
}
