/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domein;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author ~dreeki~
 */
public abstract class Persoon{


    private final StringProperty naam = new SimpleStringProperty();

    protected Persoon() {

    }

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