/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domein;

import java.io.Serializable;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 *
 * @author wim
 */
@Entity
@Table(name = "administrator")
@Access(AccessType.FIELD)
@NamedQueries({
    @NamedQuery(name = "Admin.findAll", query = "SELECT p FROM Admin p")
    ,
    @NamedQuery(name = "Admin.findAdmin", query = "SELECT p from Admin p WHERE p.email = :name")
}
)
public class Admin implements Serializable {

    private static final long serialVersionUID = 1L;

    protected Admin() {
    }

    @Transient
    private final StringProperty email = new SimpleStringProperty(this,"email");

    @Id
    @Column(name = "Email")
    @Access(AccessType.PROPERTY)
    public String getEmail() {
        return email.get();
    }

    public void setEmail(String value) {
        email.set(value);
    }

    public StringProperty emailProperty() {
        return email;
    }

    @Transient
    private final StringProperty naam = new SimpleStringProperty();

    @Column(name = "Naam")
    @Access(AccessType.PROPERTY)
    public String getNaam() {
        return naam.get();
    }

    public void setNaam(String value) {
        naam.set(value);
    }

    public StringProperty naamProperty() {
        return naam;
    }

    @Transient
    private final StringProperty voornaam = new SimpleStringProperty();

    @Column(name = "Voornaam")
    @Access(AccessType.PROPERTY)
    public String getVoornaam() {
        return voornaam.get();
    }

    public void setVoornaam(String value) {
        voornaam.set(value);
    }

    public StringProperty voornaamProperty() {
        return voornaam;
    }

    public Admin(String naam, String voornaam, String email) {
        setNaam(naam);
        setVoornaam(voornaam);
        setEmail(email);
    }

    @Access(AccessType.FIELD)
    @Column(name = "superadmin")
    private boolean superAdmin;

    public boolean isSuperAdmin() {
        return superAdmin;
    }

    public void setSuperAdmin(boolean superAdmin) {
        this.superAdmin = superAdmin;
    }

    @Transient
    private BooleanProperty checked = new SimpleBooleanProperty(false);

    public BooleanProperty isChecked() {
        return checked;
    }

    public void setChecked(Boolean checked) {
        this.checked.set(checked);
    }

    boolean controleerOfCoachMetEmailBestaat(String jobCoachMail) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    JobCoach geefCoachMetEmail(String jobCoachMail) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String toString() {
        return "persistence.Persoon[ email=" + email + " ]";
    }

}
