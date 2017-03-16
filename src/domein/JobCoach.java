/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domein;

import java.util.ArrayList;
import java.util.List;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author ~dreeki~
 */
public class JobCoach extends Persoon {

    private List<Analyse> analyses;

    private final StringProperty organisatie = new SimpleStringProperty();

    public String getOrganisatie() {
        return organisatie.get();
    }

    public void setOrganisatie(String value) {
        organisatie.set(value);
    }

    public StringProperty organisatieProperty() {
        return organisatie;
    }
    private final StringProperty straat = new SimpleStringProperty();

    public String getStraat() {
        return straat.get();
    }

    public void setStraat(String value) {
        straat.set(value);
    }
    private final StringProperty huisnummer = new SimpleStringProperty();

    public String getHuisnummer() {
        return huisnummer.get();
    }

    public void setHuisnummer(String value) {
        huisnummer.set(value);
    }

    public StringProperty huisnummerProperty() {
        return huisnummer;
    }
    
    
    
    public StringProperty straatProperty() {
        return straat;
    }
    private final IntegerProperty postcode = new SimpleIntegerProperty();

    public int getPostcode() {
        return postcode.get();
    }

    public void setPostcode(int value) {
        postcode.set(value);
    }

    public IntegerProperty postcodeProperty() {
        return postcode;
    }
    private final StringProperty gemeente = new SimpleStringProperty();

    public String getGemeente() {
        return gemeente.get();
    }

    public void setGemeente(String value) {
        gemeente.set(value);
    }

    public StringProperty gemeenteProperty() {
        return gemeente;
    }

    public JobCoach(String naam, String voornaam, String email, String naamOrganisatie, String straatBedrijf, String nummerBedrijf, int postcodeBedrijf, String gemeenteBedrijf) {
        super(naam, voornaam, email);
        setOrganisatie(naamOrganisatie);
        setStraat(straatBedrijf);
        setHuisnummer(nummerBedrijf);
        setPostcode(postcodeBedrijf);
        setGemeente(gemeenteBedrijf);
        analyses = new ArrayList<>();

    }

    public List<Analyse> getAnalyses() {
        return analyses;
    }

    public void voegAnalyseToe(Analyse a) {
        analyses.add(a);
    }

    public boolean controleerOfAnalyseAanwezigIs(int id) {
        return analyses.stream().anyMatch(a -> a.getId() == id);
    }

    public Analyse geefAnalyseMetId(int id) {
        return analyses.stream().filter(a -> a.getId() == id).findFirst().get();
    }
}
