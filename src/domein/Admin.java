/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domein;

import java.util.ArrayList;
import java.util.List;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.OneToMany;
import javax.persistence.FetchType;
import javax.persistence.Entity;
import javax.persistence.Transient;

/**
 *
 * @author ~dreeki~
 */

public class Admin extends Persoon {

    private final long serialVersionUID = 1L;

    @Transient
    private BooleanProperty checked = new SimpleBooleanProperty(false);


    private boolean superAdmin;

    protected Admin() {
    }

    public BooleanProperty isChecked() {
        return checked;
    }

    public void setChecked(Boolean checked) {
        this.checked.set(checked);
    }

    public Admin(String naam, String voornaam, String email) {
        super(naam, voornaam, email);
    }

    public boolean isSuperAdmin() {
        return superAdmin;
    }

    boolean controleerOfCoachMetEmailBestaat(String jobCoachMail) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    JobCoach geefCoachMetEmail(String jobCoachMail) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


}
