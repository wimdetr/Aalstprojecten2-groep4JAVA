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
public class KOBRij {
    private int id;
    private Map<Integer, KOBVak> vakken;
    private double resultaat;

    public KOBRij(int id) {
        this.id = id;
        vakken = new HashMap<>();
        resultaat = 0;
    }

    public int getId() {
        return id;
    }
    
    public void vulKOBVakIn(int nummer, KOBVak vak){
        vakken.put(nummer, vak);
    }
    
    public KOBVak geefKOBVakMetNummer(int nummer){
        return vakken.get(nummer);
    }
    
    public boolean controleerOfKOBVakMetNummerAlIngevuldIs(int nummer){
        return vakken.keySet().stream().anyMatch(k -> k == nummer);
    }

    public double getResultaat() {
        return resultaat;
    }

    public void setResultaat(double resultaat) {
        this.resultaat = resultaat;
    }
}
