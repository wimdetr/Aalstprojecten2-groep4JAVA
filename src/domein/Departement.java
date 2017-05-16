/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domein;

import java.io.Serializable;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ObservableBooleanValue;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 *
 * @author wimde
 */
@Entity
@Table(name = "departementen")
@NamedQueries({
    @NamedQuery(name = "Departement.findAll", query = "SELECT d FROM Departement d")})
public class Departement implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "DepartementId")
    private Integer departementId;
    @Column(name = "AantalWerkuren")
    private int aantalWerkuren;
    @Column(name = "Bus")
    private String bus;
    @Column(name = "ContactPersoonEmail")
    private String contactPersoonEmail;
    @Column(name = "ContactPersoonNaam")
    private String contactPersoonNaam;
    @Column(name = "ContactPersoonVoornaam")
    private String contactPersoonVoornaam;
    @Column(name = "Gemeente")
    private String gemeente;
    @Column(name = "Naam")
    private String naam;
    @Column(name = "Nummer")
    private int nummer;
    @Column(name = "Postcode")
    private int postcode;
    @Column(name = "Straat")
    private String straat;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "WerkgeverId")
    private Werkgever werkgever;

    public Werkgever getWerkgever() {
        return werkgever;
    }

    public void setWerkgever(Werkgever werkgever) {
        this.werkgever = werkgever;
    }

    @OneToOne
    @JoinColumn(name = "AnalyseId")
    private Analyse analyse;

    public Analyse getAnalyse() {
        return analyse;
    }

    public void setAnalyse(Analyse analyse) {
        this.analyse = analyse;
    }

    public Departement() {
    }

    public Departement(Integer departementId) {
        this.departementId = departementId;
    }

    public Departement(Integer departementId, int aantalWerkuren, String gemeente, String naam, int nummer, int postcode, String straat) {
        this.departementId = departementId;
        this.aantalWerkuren = aantalWerkuren;
        this.gemeente = gemeente;
        this.naam = naam;
        this.nummer = nummer;
        this.postcode = postcode;
        this.straat = straat;
    }

    public Integer getDepartementId() {
        return departementId;
    }

    public void setDepartementId(Integer departementId) {
        this.departementId = departementId;
    }

    public int getAantalWerkuren() {
        return aantalWerkuren;
    }

    public void setAantalWerkuren(int aantalWerkuren) {
        this.aantalWerkuren = aantalWerkuren;
    }

    public String getBus() {
        return bus;
    }

    public void setBus(String bus) {
        this.bus = bus;
    }

    public String getContactPersoonEmail() {
        return contactPersoonEmail;
    }

    public void setContactPersoonEmail(String contactPersoonEmail) {
        this.contactPersoonEmail = contactPersoonEmail;
    }

    public String getContactPersoonNaam() {
        return contactPersoonNaam;
    }

    public void setContactPersoonNaam(String contactPersoonNaam) {
        this.contactPersoonNaam = contactPersoonNaam;
    }

    public String getContactPersoonVoornaam() {
        return contactPersoonVoornaam;
    }

    public void setContactPersoonVoornaam(String contactPersoonVoornaam) {
        this.contactPersoonVoornaam = contactPersoonVoornaam;
    }

    public String getGemeente() {
        return gemeente;
    }

    public void setGemeente(String gemeente) {
        this.gemeente = gemeente;
    }

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public int getNummer() {
        return nummer;
    }

    public void setNummer(int nummer) {
        this.nummer = nummer;
    }

    public int getPostcode() {
        return postcode;
    }

    public void setPostcode(int postcode) {
        this.postcode = postcode;
    }

    public String getStraat() {
        return straat;
    }

    public void setStraat(String straat) {
        this.straat = straat;
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
    public int hashCode() {
        int hash = 0;
        hash += (departementId != null ? departementId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Departement)) {
            return false;
        }
        Departement other = (Departement) object;
        if ((this.departementId == null && other.departementId != null) || (this.departementId != null && !this.departementId.equals(other.departementId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "domein.Departementen[ departementId=" + departementId + " ]";
    }

}
