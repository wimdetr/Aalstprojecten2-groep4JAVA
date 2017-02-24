/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domein;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author ~dreeki~
 */
public class KostOfBaat {
    
    private int id;
    private Map<Integer, KOBRij> rijen;
    private KOBEnum kostOfBaatEnum;
    private Formule formule;
    private double resultaat;
    //rijen en vakken beginnen bij id == 1, niet 0!!!!

    public KostOfBaat(int id, KOBEnum kobEnum, Formule formule) {
        this.id = id;
        rijen = new HashMap<>();
        kostOfBaatEnum = kobEnum;
        this.formule = formule;
        resultaat = 0;
    }
    
    public void vulKOBRijIn(int nummer, KOBRij rij){
        rijen.put(rij.getId(), rij);
    }
    
    public KOBRij geefKOBRijMetNummer(int nummer){
        return rijen.get(nummer);
    }
    
    public boolean controleerOfKOBRijMetNummerAlIngevuldIs(int nummer){
        return rijen.keySet().stream().anyMatch(k -> k == nummer);
    }

    public double getResultaat() {
        return resultaat;
    }
    
    public void berekenResultaat(){
        resultaat = rijen.values().stream().mapToDouble(v -> v.getResultaat()).reduce(0, (value1, value2) -> value1+value2);
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

    public Map<Integer, KOBRij> getRijen() {
        return rijen;
    }
    
    
}
