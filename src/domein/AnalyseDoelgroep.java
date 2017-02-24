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
public enum AnalyseDoelgroep {
    WN_MINDER_DAN_25_JAAR_LAAGGESCHOOLD("wn minder dan 25 jaar laaggeschoold", 1550.00),
    WN_MINDER_DAN_25_JAAR_MIDDENGESCHOOLD("wn minder dan 25 jaar middengeschoold", 1000.00),
    WN_MEER_OF_GELIJK_AAN_55__OF_MINDER_DAN_60_JAAR("wn meer of gelijk aan 55 of minder dan 60 jaar", 1150.00),
    WN_MEER_OF_EVENVEEL_ALS_60_JAAR("wn meer of evenveel als 60 jaar", 1500.00),
    ANDER("ander", 0);
    
    private final String naam;
    private final double bedragDoelgroepVerminderingPerKwartaal;

    private AnalyseDoelgroep(String naam, double bedragDoelgroepVerminderingPerKwartaal) {
        this.naam = naam;
        this.bedragDoelgroepVerminderingPerKwartaal = bedragDoelgroepVerminderingPerKwartaal;
    }

    public double getBedragDoelgroepVerminderingPerKwartaal() {
        return bedragDoelgroepVerminderingPerKwartaal;
    }

    public String getNaam() {
        return naam;
    }
    
    
}
