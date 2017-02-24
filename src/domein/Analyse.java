/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domein;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author ~dreeki~
 */
public class Analyse {
    private int id;
    private Map<Integer, KostOfBaat> kosten;
    private Map<Integer, KostOfBaat> baten;
    private Werkgever werkgever;
    private Date laatsteAanpasDatum;

    public Analyse(int id, Date date) {
        this.id = id;
        kosten = new HashMap<>();
        baten = new HashMap<>();
        laatsteAanpasDatum = date;
    }
    
    public boolean controleerOfKostMetNummerAlIngevuldIs(int nummer){
        return kosten.keySet().stream().anyMatch(k -> k == nummer);
    }
    
    public KostOfBaat geefKostMetNummer(int nummer){
        return kosten.get(nummer);
    }
    
    public boolean controleerOfBaatMetNummerAlIngevuldIs(int nummer){
        return baten.keySet().stream().anyMatch(k -> k == nummer);
    }
    
    public KostOfBaat geefBaatMetNummer(int nummer){
        return baten.get(nummer);
    }
    
    public void slaKostMetNummerOp(int nummer, KostOfBaat kost){
        kosten.put(nummer, kost);
    }
    
    public void slaBaatMetNummerOp(int nummer, KostOfBaat baat){
        baten.put(nummer, baat);
    }
    
    public Werkgever geefWerkgever(){
        return werkgever;
    }
    
    public void slaWerkgeverOp(Werkgever werkgever){
        this.werkgever = werkgever;
    }

    public Werkgever getWerkgever() {
        return werkgever;
    }

    public int getId() {
        return id;
    }
    
    public double geefSubtotaalKosten(){
        return kosten.values().stream().mapToDouble(k -> k.geefResultaat()).reduce(0, (value1, value2) -> value1 + value2);
                
    }
    
    public double geefSubtotaalBaten(){
        return baten.values().stream().mapToDouble(b -> b.geefResultaat()).reduce(0, (value1, value2) -> value1 + value2);
    }
    
    public double geefResultaat(){
        return geefSubtotaalBaten() - geefSubtotaalKosten();
    }
    
    public void vernieuwDatum(){
        laatsteAanpasDatum = new Date();
    }

    public Date getLaatsteAanpasDatum() {
        return laatsteAanpasDatum;
    }
    
    
}
