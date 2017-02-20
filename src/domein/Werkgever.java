/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domein;

/**
 *
 * @author ~dreeki~
 */
public class Werkgever {
    private String naam;
    private String straat;
    private String nummer;
    private int postcode;
    private String gemeente;
    private int aantalWerkuren;
    private int patronaleBijdrage;
    private String linkNaarLogoPrent;
    private ContactPersoon contactPersoon;
    private String naamAfdeling;
    
    private static final int DEFAULT_PATRONALE_BIJDRAGE = 35;

    public Werkgever(String naam, int postcode, String gemeente) {
        patronaleBijdrage = DEFAULT_PATRONALE_BIJDRAGE;
        this.naam = naam;
        this.gemeente = gemeente;
    }

    public String getNaamAfdeling() {
        return naamAfdeling;
    }

    public void setNaamAfdeling(String naamAfdeling) {
        this.naamAfdeling = naamAfdeling;
    }
    
    

    public ContactPersoon getContactPersoon() {
        return contactPersoon;
    }

    public void setContactPersoon(ContactPersoon contactPersoon) {
        this.contactPersoon = contactPersoon;
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

    
    
}
