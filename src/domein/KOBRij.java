/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domein;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ~dreeki~
 */
public class KOBRij {
    private int id;
    private List<KOBVak> vakken;
    private double resultaat;

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
