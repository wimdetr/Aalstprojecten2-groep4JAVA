/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domein;

import domein.Analyse;
import java.io.Serializable;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ObservableBooleanValue;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

/**
 *
 * @author wimde
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "Werkgever.findAll", query = "SELECT w FROM Werkgever w")})
public class Werkgever implements Serializable {

    protected Werkgever() {

    }

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "WerkgeverId")
    private int id;

    @Column(name = "AantalWerkuren")
    private int aantalWerkuren;

    public Werkgever(String bedrijf, int i, String gemeente) {
        naam = bedrijf;
        id = i;
        this.gemeente = gemeente;
    }

    public int getAantalWerkuren() {
        return aantalWerkuren;
    }

    public void setAantalWerkuren(int aantalWerkuren) {
        this.aantalWerkuren = aantalWerkuren;
    }

    @Column(name = "Gemeente")
    private String gemeente;

    public String getGemeente() {
        return gemeente;
    }

    public void setGemeente(String gemeente) {
        this.gemeente = gemeente;
    }

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "AnalyseId")
    private Analyse analyse;

    public Analyse getAnalyse() {
        return analyse;
    }

    public void setAnalyse(Analyse analyse) {
        this.analyse = analyse;
    }

    @Column(name = "ContactPersoonNaam")
    private String contactPersoonNaam;

    public String getContactPersoonNaam() {
        return contactPersoonNaam;
    }

    public void setContactPersoonNaam(String contactPersoonNaam) {
        this.contactPersoonNaam = contactPersoonNaam;
    }

    @Column(name = "ContactPersoonVoornaam")
    private String contactPersoonVoornaam;

    public String getContactPersoonVoornaam() {
        return contactPersoonVoornaam;
    }

    @Column(name = "linkNaarLogoPrent")
    private String linkNaarLogoPrent;

    public String getLinkNaarLogoPrent() {
        return linkNaarLogoPrent;
    }

    public void setLinkNaarLogoPrent(String linkNaarLogoPrent) {
        this.linkNaarLogoPrent = linkNaarLogoPrent;
    }

    public void setContactPersoonVoornaam(String contactPersoonVoornaam) {
        this.contactPersoonVoornaam = contactPersoonVoornaam;
    }

    @Column(name = "ContactPersoonEmail")
    private String contactPersoonEmail;

    public String getContactPersoonEmail() {
        return contactPersoonEmail;
    }

    public void setContactPersoonEmail(String contactPersoonEmail) {
        this.contactPersoonEmail = contactPersoonEmail;
    }

    @Column(name = "Naam")
    private String naam;

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    @Column(name = "NaamAfdeling")
    private String naamAfdeling;

    public String getNaamAfdeling() {
        return naamAfdeling;
    }

    public void setNaamAfdeling(String naamAfdeling) {
        this.naamAfdeling = naamAfdeling;
    }

    @Column(name = "Nummer")
    private int nummer;

    public int getNummer() {
        return nummer;
    }

    public void setNummer(int nummer) {
        this.nummer = nummer;
    }

    @Column(name = "PatronaleBijdrage")
    private int patronaleBijdrage;

    public int getPatronaleBijdrage() {
        return patronaleBijdrage;
    }

    public void setPatronaleBijdrage(int patronaleBijdrage) {
        this.patronaleBijdrage = patronaleBijdrage;
    }

    @Column(name = "Postcode")
    private int postcode;

    public int getPostcode() {
        return postcode;
    }

    public void setPostcode(int postcode) {
        this.postcode = postcode;
    }

    @Column(name = "Straat")
    private String straat;

    public String getStraat() {
        return straat;
    }

    public void setStraat(String straat) {
        this.straat = straat;
    }

    @Column(name = "bus")
    private String bus;

    public String getBus() {
        return bus;
    }

    public void setBus(String bus) {
        this.bus = bus;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) id;
        return hash;
    }

       @Transient
    private BooleanProperty checked = new SimpleBooleanProperty(false);

    public ObservableBooleanValue isChecked() {
        return checked;
    }

    public void setChecked(Boolean checked) {
        this.checked.set(checked);
    }
    
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Werkgever)) {
            return false;
        }
        Werkgever other = (Werkgever) object;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "persistence.Werkgever[ id=" + id + " ]";
    }

}
