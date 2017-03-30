/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domein;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author ~dreeki~
 */
@Entity
@Table(name = "analyse")
public class Analyse implements Serializable {

    private final long serialVersionUID = 1L;

    @Id
    @Column(name = "AnalyseId")
    private int id;
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<KostOfBaat> kostenEnBaten;
    @OneToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    private Werkgever werkgever;
    @Column(name = "LaatsteAanpasDatum")
    private Date laatsteAanpasDatum;

    //ManyToOne(bidirectioneel met dep)
    private Departement departement;

    public void setDepartement(Departement departement) {
        this.departement = departement;
    }

    public Departement getDepartement() {
        return departement;
    }

    protected Analyse() {
    }

    public Analyse(int id, Date date) {
        this.id = id;
        kostenEnBaten = new ArrayList<>();
        laatsteAanpasDatum = date;
    }

    public boolean controleerOfKostMetNummerAlIngevuldIs(int nummer) {
        return kostenEnBaten.stream().anyMatch(k -> k.getId() == nummer && k.getKostOfBaatEnum() == KOBEnum.Kost);
    }

    public KostOfBaat geefKostMetNummer(int nummer) {
        return kostenEnBaten.stream().filter(k -> k.getId() == nummer && k.getKostOfBaatEnum() == KOBEnum.Kost).findFirst().get();
    }

    public boolean controleerOfBaatMetNummerAlIngevuldIs(int nummer) {
        return kostenEnBaten.stream().anyMatch(b -> b.getId() == nummer && b.getKostOfBaatEnum() == KOBEnum.Baat);
    }

    public KostOfBaat geefBaatMetNummer(int nummer) {
        return kostenEnBaten.stream().filter(b -> b.getId() == nummer && b.getKostOfBaatEnum() == KOBEnum.Baat).findFirst().get();
    }

    public void slaKostMetNummerOp(KostOfBaat kost) {
        if (controleerOfKostMetNummerAlIngevuldIs(kost.getId())) {
            kostenEnBaten.remove(geefKostMetNummer(kost.getId()));
        }

        kostenEnBaten.add(kost);
    }

    public void slaBaatMetNummerOp(KostOfBaat baat) {
        if (controleerOfBaatMetNummerAlIngevuldIs(baat.getId())) {
            kostenEnBaten.remove(geefBaatMetNummer(baat.getId()));
        }

        kostenEnBaten.add(baat);
    }

    public Werkgever geefWerkgever() {
        return werkgever;
    }

    public void slaWerkgeverOp(Werkgever werkgever) {
        this.werkgever = werkgever;
    }

    public Werkgever getWerkgever() {
        return werkgever;
    }

    public int getId() {
        return id;
    }

    public double geefSubtotaalKosten() {
        //return kosten.values().stream().mapToDouble(k -> k.geefResultaat()).reduce(0, (value1, value2) -> value1 + value2);
        return 0;
    }

    public double geefSubtotaalBaten() {
        //return baten.values().stream().mapToDouble(b -> b.geefResultaat()).reduce(0, (value1, value2) -> value1 + value2);
        return 0;
    }

    public double geefResultaat() {
        return geefSubtotaalBaten() - geefSubtotaalKosten();
    }

    public void vernieuwDatum() {
        laatsteAanpasDatum = new Date();
    }

    public Date getLaatsteAanpasDatum() {
        return laatsteAanpasDatum;
    }

    public List<KostOfBaat> getBaten() {
        return kostenEnBaten.stream().filter(b -> b.getKostOfBaatEnum() == KOBEnum.Baat).collect(Collectors.toList());
    }

    public List<KostOfBaat> getKosten() {
        return kostenEnBaten.stream().filter(k -> k.getKostOfBaatEnum() == KOBEnum.Kost).collect(Collectors.toList());
    }

}
