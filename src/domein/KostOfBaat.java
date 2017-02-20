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

    public KostOfBaat(int id, KOBEnum kobEnum) {
        this.id = id;
        rijen = new HashMap<>();
        kostOfBaatEnum = kobEnum;
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
    
    public double geefResultaat(){
        return rijen.values().stream().mapToDouble(r -> r.geefResultaat()).reduce(0, (value1, value2) -> value1 + value2);
    }
}
