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
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

/**
 *
 * @author ~dreeki~
 */
@Entity
@DiscriminatorValue(value = "JobCoach")
public class JobCoach extends Persoon {

    private final long serialVersionUID = 1L;

    private final StringProperty organisatie = new SimpleStringProperty();

    @Column(name = "NaamBedrijf")
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

    @Column(name = "StraatBedrijf")
    public String getStraat() {
        return straat.get();
    }

    public void setStraat(String value) {
        straat.set(value);
    }
    private final StringProperty huisnummer = new SimpleStringProperty();

    @Column(name = "NummerBedrijf")
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

    @Column(name = "PostcodeBedrijf")
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

    @Column(name = "GemeenteBedrijf")
    public String getGemeente() {
        return gemeente.get();
    }

    public void setGemeente(String value) {
        gemeente.set(value);
    }

    public StringProperty gemeenteProperty() {
        return gemeente;
    }
    private final StringProperty bus = new SimpleStringProperty();

    @Column(name = "BusBedrijf")
    public String getBus() {
        return bus.get();
    }

    public void setBus(String value) {
        bus.set(value);
    }

    public StringProperty busProperty() {
        return bus;
    }

    protected JobCoach() {

    }

    public JobCoach(String naam, String voornaam, String email, String naamOrganisatie, String straatBedrijf, String nummerBedrijf, int postcodeBedrijf, String gemeenteBedrijf, String busBedrijf) {
        super(naam, voornaam, email);
        setOrganisatie(naamOrganisatie);
        setStraat(straatBedrijf);
        setHuisnummer(nummerBedrijf);
        setPostcode(postcodeBedrijf);
        setGemeente(gemeenteBedrijf);
        setBus(busBedrijf);
        analyses = new ArrayList<>();

    }

    private List<Analyse> analyses;

    public void setAnalyses(List<Analyse> analyses) {
        this.analyses = analyses;
    }

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "JobCoachEmail", referencedColumnName = "Email")
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
