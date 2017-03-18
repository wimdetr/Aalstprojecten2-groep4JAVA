/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domein;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author ~dreeki~
 */
public class Analyse {
    
    private int id;
    private List<KostOfBaat> kosten;
    private List<KostOfBaat> baten;
    private Werkgever werkgever;
    private Date laatsteAanpasDatum;

    public Analyse(int id, Date date) {
        this.id = id;
        kosten = new ArrayList<>();
        baten = new ArrayList<>();
        laatsteAanpasDatum = date;
    }
    
    public boolean controleerOfKostMetNummerAlIngevuldIs(int nummer){
        return kosten.stream().anyMatch(k -> k.getId() == nummer);
    }
    
    public KostOfBaat geefKostMetNummer(int nummer){
        return kosten.stream().filter(k -> k.getId() == nummer).findFirst().get();
    }
    
    public boolean controleerOfBaatMetNummerAlIngevuldIs(int nummer){
        return baten.stream().anyMatch(b -> b.getId() == nummer);
    }
    
    public KostOfBaat geefBaatMetNummer(int nummer){
        return baten.stream().filter(b -> b.getId() == nummer).findFirst().get();
    }
    
    public void slaKostMetNummerOp(KostOfBaat kost){
        if(controleerOfKostMetNummerAlIngevuldIs(kost.getId())){
            kosten.remove(geefKostMetNummer(kost.getId()));
        }
        
        kosten.add(kost);
    }
    
    public void slaBaatMetNummerOp(KostOfBaat baat){
        if(controleerOfBaatMetNummerAlIngevuldIs(baat.getId())){
            baten.remove(geefBaatMetNummer(baat.getId()));
        }
        
        baten.add(baat);
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
        //return kosten.values().stream().mapToDouble(k -> k.geefResultaat()).reduce(0, (value1, value2) -> value1 + value2);
          return 0;      
    }
    
    public double geefSubtotaalBaten(){
        //return baten.values().stream().mapToDouble(b -> b.geefResultaat()).reduce(0, (value1, value2) -> value1 + value2);
        return 0;
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

    public List<KostOfBaat> getBaten() {
        return baten;
    }

    public List<KostOfBaat> getKosten() {
        return kosten;
    }
    
    
}
