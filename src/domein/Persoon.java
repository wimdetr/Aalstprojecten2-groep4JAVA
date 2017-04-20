/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domein;

import java.io.Serializable;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

/**
 *
 * @author ~dreeki~
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "persoon")
@DiscriminatorColumn(name = "Type")
public abstract class Persoon implements Serializable {

    private final long serialVersionUID = 1L;

    private final StringProperty naam = new SimpleStringProperty();

    protected Persoon() {

    }

    @Column(name = "Naam")
    public String getNaam() {
        return naam.get();
    }

    public void setNaam(String value) {
        naam.set(value);
    }

    public StringProperty naamProperty() {
        return naam;
    }
    private final StringProperty voornaam = new SimpleStringProperty();

    @Column(name = "Voornaam")
    public String getVoornaam() {
        return voornaam.get();
    }

    public void setVoornaam(String value) {
        voornaam.set(value);
    }

    public StringProperty voornaamProperty() {
        return voornaam;
    }

    private final StringProperty email = new SimpleStringProperty();

    @Id
    @Column(name = "Email")
    public String getEmail() {
        return email.get();
    }

    public void setEmail(String value) {
        email.set(value);
    }

    public StringProperty emailProperty() {
        return email;
    }

    public Persoon(String naam, String voornaam, String email) {
        setNaam(naam);
        setVoornaam(voornaam);
        setEmail(email);
    }

}
