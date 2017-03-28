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
@Table(name = "kostofbaatrij")
public class KOBRij implements Serializable{
    private final long serialVersionUID = 1L;
    
    @Id
    @Column(name = "KOBRijId")
    private int id;
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<KOBVak> vakken;
    private double resultaat;

    protected KOBRij(){
        
    }
    
    public KOBRij(int id) {
        this.id = id;
        vakken = new ArrayList<>();
        resultaat = 0;
    }

    public int getId() {
        return id;
    }
    
    public void vulKOBVakIn(KOBVak vak){
        if(controleerOfKOBVakMetNummerAlIngevuldIs(vak.getId())){
            vakken.remove(geefKOBVakMetNummer(vak.getId()));
        }
        vakken.add(vak);
    }
    
    public KOBVak geefKOBVakMetNummer(int nummer){
        return vakken.stream().filter(v -> v.getId() == nummer).findFirst().get();
    }
    
    public boolean controleerOfKOBVakMetNummerAlIngevuldIs(int nummer){
        return vakken.stream().anyMatch(v -> v.getId() == nummer);
    }

    public double getResultaat() {
        return resultaat;
    }

    public void setResultaat(double resultaat) {
        this.resultaat = resultaat;
    }
}
