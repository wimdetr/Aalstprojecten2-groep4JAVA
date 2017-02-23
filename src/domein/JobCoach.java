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

    private String naamBedrijf;
    private String straatBedrijf;
    private String nummerBedrijf;
    private int postcodeBedrijf;
    private String gemeenteBedrijf;
    private List<Analyse> analyses;


    private final StringProperty bedrijf = new SimpleStringProperty();

    public String getBedrijf() {
        return bedrijf.get();
    }

    public void setBedrijf(String value) {
        bedrijf.set(value);
    }

    public StringProperty bedrijfProperty() {
        return bedrijf;
    }
    private final StringProperty straat = new SimpleStringProperty();

    public String getStraat() {
        return straat.get();
    }

    public void setStraat(String value) {
        straat.set(value);
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

    public JobCoach(String naam, String voornaam, String email, String naamBedrijf, String straatBedrijf, String nummerBedrijf, int postcodeBedrijf, String gemeenteBedrijf) {
        super(naam,voornaam,email);
        setBedrijf(naamBedrijf);
        setStraat(straatBedrijf + " " + nummerBedrijf);
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
}
