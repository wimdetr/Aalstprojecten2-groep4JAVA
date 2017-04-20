/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domein;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author ~dreeki~
 */
@Entity
@Table(name = "werkgever")
public class Werkgever implements Serializable {

    private final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "WerkgeverId")
    private int id;
    @Column(name = "Naam")
    private String naam;
    @Column(name = "Straat")
    private String straat;
    @Column(name = "Nummer")
    private String nummer;
    @Column(name = "Postcode")
    private int postcode;
    @Column(name = "Gemeente")
    private String gemeente;
    @Column(name = "AantalWerkuren")
    private int aantalWerkuren;
    @Column(name = "PatronaleBijdrage")
    private int patronaleBijdrage;
    @Column(name = "LinkNaarLogoPrent")
    private String linkNaarLogoPrent;
    @Column(name = "NaamAfdeling")
    private String naamAfdeling;

    // OneToMany met departments (bidirectioneel)
    private List<Departement> departementen;

    public List<Departement> getDepartementen() {
        return departementen;
    }

    @Column(name = "Bus")
    private String bus;

    private static final int DEFAULT_PATRONALE_BIJDRAGE = 35;

    protected Werkgever() {
    }

    public Werkgever(String naam, int postcode, String gemeente) {
        patronaleBijdrage = DEFAULT_PATRONALE_BIJDRAGE;
        this.naam = naam;
        this.gemeente = gemeente;
        departementen = new ArrayList<>();
    }

    public String getNaamAfdeling() {
        return naamAfdeling;
    }

    public void setNaamAfdeling(String naamAfdeling) {
        this.naamAfdeling = naamAfdeling;
    }

    public String getBus() {
        return bus;
    }

    public void setBus(String bus) {
        this.bus = bus;
    }

    public String getLinkNaarLogoPrent() {
        return linkNaarLogoPrent;
    }

    public void setLinkNaarLogoPrent(String linkNaarLogoPrent) {
        this.linkNaarLogoPrent = linkNaarLogoPrent;
    }

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public String getStraat() {
        return straat;
    }

    public void setStraat(String straat) {
        this.straat = straat;
    }

    public String getNummer() {
        return nummer;
    }

    public void setNummer(String nummer) {
        this.nummer = nummer;
    }

    public int getPostcode() {
        return postcode;
    }

    public void setPostcode(int postcode) {
        this.postcode = postcode;
    }

    public String getGemeente() {
        return gemeente;
    }

    public void setGemeente(String gemeente) {
        this.gemeente = gemeente;
    }

    public int getAantalWerkuren() {
        return aantalWerkuren;
    }

    public void setAantalWerkuren(int aantalWerkuren) {
        this.aantalWerkuren = aantalWerkuren;
    }

    public int getPatronaleBijdrage() {
        return patronaleBijdrage;
    }

    public void setPatronaleBijdrage(int patronaleBijdrage) {
        this.patronaleBijdrage = patronaleBijdrage;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void addDepartement(Departement d) {
        departementen.add(d);
    }
}
