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
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author ~dreeki~
 */
@Entity
@Table(name = "kostofbaat")
public class KostOfBaat implements Serializable{
    private final long serialVersionUID = 1L;
    
    @Id
    @Column(name = "KostOfBaatId")
    private int id;
    @Id
    @Column(name = "KostOfBaatEnum")
    private KOBEnum kostOfBaatEnum;
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<KOBRij> rijen;
    @Column(name = "Formule")
    private Formule formule;
    private double resultaat;
    //rijen en vakken beginnen bij id == 1, niet 0!!!!

    protected KostOfBaat() {
    }

    public KostOfBaat(int id, KOBEnum kobEnum, Formule formule) {
        this.id = id;
        rijen = new ArrayList<>();
        kostOfBaatEnum = kobEnum;
        this.formule = formule;
        resultaat = 0;
    }
    
    public void vulKOBRijIn(KOBRij rij){
        if(controleerOfKOBRijMetNummerAlIngevuldIs(rij.getId())){
            rijen.remove(geefKOBRijMetNummer(rij.getId()));
        }
        rijen.add(rij);
    }
    
    public KOBRij geefKOBRijMetNummer(int nummer){
        return rijen.stream().filter(r -> r.getId() == nummer).findFirst().get();
    }
    
    public boolean controleerOfKOBRijMetNummerAlIngevuldIs(int nummer){
        return rijen.stream().anyMatch(r -> r.getId() == nummer);
    }

    public double getResultaat() {
        return resultaat;
    }
    
    public void berekenResultaat(){
        resultaat = rijen.stream().mapToDouble(v -> v.getResultaat()).reduce(0, (value1, value2) -> value1+value2);
    }

    public Formule getFormule() {
        return formule;
    }

    public void setFormule(Formule formule) {
        this.formule = formule;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<KOBRij> getRijen() {
        return rijen;
    }

    public KOBEnum getKostOfBaatEnum() {
        return kostOfBaatEnum;
    }

    public void setKostOfBaatEnum(KOBEnum kostOfBaatEnum) {
        this.kostOfBaatEnum = kostOfBaatEnum;
    }
    
    
}
