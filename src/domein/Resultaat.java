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
public class Resultaat {
    private KostOfBaat kostOfBaat;
    private Analyse analyse;

    public Resultaat() {
    }
    
    public void geefParametersDoor(KostOfBaat kob, Analyse a){
        kostOfBaat = kob;
        analyse = a;
    }
    
    public void berekenEnSetResultaat(KOBRij mijnRij){
        int aantalWerkuren = analyse.getWerkgever().getAantalWerkuren();
        switch (kostOfBaat.getFormule()){
            case FORMULE_BAAT1:
                if(aantalWerkuren == 0){
                    //todo, eigen divide by zero exception schrijven
                    //throw 
                }
                KOBRij kostRij = geefRijVanKost(kostOfBaat.getId(), mijnRij.getId());
                if(kostRij == null){
                    mijnRij.setResultaat(mijnRij.geefKOBVakMetNummer(2).geefDataAlsDouble());
                }else{
                    berekenEnSetResultaat(kostRij);
                    double tussenWaarde;
                    String dataVak4 = kostRij.geefKOBVakMetNummer(4).getDataVak();
                    double dataVak3 = kostRij.geefKOBVakMetNummer(3).geefDataAlsDouble();
                    double dataVak2 = kostRij.geefKOBVakMetNummer(2).geefDataAlsDouble();
                    
                    if(dataVak4.equals(AnalyseDoelgroep.WN_MINDER_DAN_25_JAAR_LAAGGESCHOOLD.getNaam()) && dataVak3 < 2500){
                        tussenWaarde = AnalyseDoelgroep.WN_MINDER_DAN_25_JAAR_LAAGGESCHOOLD.getBedragDoelgroepVerminderingPerKwartaal()/aantalWerkuren*dataVak2/4;
                    }else if(dataVak4.equals(AnalyseDoelgroep.WN_MINDER_DAN_25_JAAR_MIDDENGESCHOOLD.getNaam()) && dataVak3 < 2500){
                        tussenWaarde = AnalyseDoelgroep.WN_MINDER_DAN_25_JAAR_MIDDENGESCHOOLD.getBedragDoelgroepVerminderingPerKwartaal()/aantalWerkuren*dataVak2/4;
                    }else if(dataVak4.equals(AnalyseDoelgroep.WN_MEER_OF_GELIJK_AAN_55__OF_MINDER_DAN_60_JAAR.getNaam()) && dataVak3 < 4466.66){
                        tussenWaarde = AnalyseDoelgroep.WN_MEER_OF_GELIJK_AAN_55__OF_MINDER_DAN_60_JAAR.getBedragDoelgroepVerminderingPerKwartaal()/aantalWerkuren*dataVak2/4;
                    }else if(dataVak4.equals(AnalyseDoelgroep.WN_MEER_OF_EVENVEEL_ALS_60_JAAR.getNaam()) && dataVak3 < 4466.66){
                        tussenWaarde = AnalyseDoelgroep.WN_MEER_OF_EVENVEEL_ALS_60_JAAR.getBedragDoelgroepVerminderingPerKwartaal()/aantalWerkuren*dataVak2/4;
                    }else if(dataVak4.equals(AnalyseDoelgroep.ANDER.getNaam())){
                        tussenWaarde = AnalyseDoelgroep.ANDER.getBedragDoelgroepVerminderingPerKwartaal()/aantalWerkuren*dataVak2;
                    }else{
                        tussenWaarde = 0;
                    }
                    
                    double tussenWaarde2 = (kostRij.getResultaat() - tussenWaarde) * kostRij.geefKOBVakMetNummer(5).geefDataAlsDouble()/100;
                    mijnRij.setResultaat((kostRij.getResultaat() - (tussenWaarde2 + tussenWaarde)) * (13.92 - mijnRij.geefKOBVakMetNummer(1).geefDataAlsDouble()) + mijnRij.geefKOBVakMetNummer(2).geefDataAlsDouble());
                }
                break;
            case FORMULE_KOST1:
                if(aantalWerkuren == 0){
                    //todo, eigen divide by zero exception schrijven
                    //throw 
                }
                mijnRij.setResultaat((mijnRij.geefKOBVakMetNummer(3).geefDataAlsDouble()/aantalWerkuren*mijnRij.geefKOBVakMetNummer(2).geefDataAlsDouble())*(1+(analyse.getWerkgever().getPatronaleBijdrage()/100)));
                break;
            case FORMULE_GEEF_VAK2:
                mijnRij.setResultaat(mijnRij.geefKOBVakMetNummer(2).geefDataAlsDouble());
                break;
            case FORMULE_KOST6:
                mijnRij.setResultaat(mijnRij.geefKOBVakMetNummer(1).geefDataAlsDouble()/152*mijnRij.geefKOBVakMetNummer(2).geefDataAlsDouble()*(1+(analyse.getWerkgever().getPatronaleBijdrage()/100)));
                break;
            case FORMULE_GEEF_VAK1:
                mijnRij.setResultaat(mijnRij.geefKOBVakMetNummer(1).geefDataAlsDouble());
                break;
            case FORMULE_BAAT_3_EN_4:
                if(mijnRij.geefKOBVakMetNummer(1).getDataVak().trim().equals("")){
                    mijnRij.setResultaat(0);
                }else{
                    mijnRij.setResultaat(mijnRij.geefKOBVakMetNummer(1).geefDataAlsDouble()/aantalWerkuren*mijnRij.geefKOBVakMetNummer(2).geefDataAlsDouble()*(1+(analyse.getWerkgever().getPatronaleBijdrage()/100)) * 13.92);
                }
                break;
            case FORMULE_SOM_VAK_1_EN_2:
                mijnRij.setResultaat(mijnRij.geefKOBVakMetNummer(1).geefDataAlsDouble() + mijnRij.geefKOBVakMetNummer(2).geefDataAlsDouble());
                break;
            case FORMULE_VERMENIGVULDIG_VAK_1_EN_2:
                mijnRij.setResultaat(mijnRij.geefKOBVakMetNummer(1).geefDataAlsDouble() * mijnRij.geefKOBVakMetNummer(2).geefDataAlsDouble());
                break;
            default:
                break;
        }
    }
    
    public KOBRij geefRijVanKost(int kostId, int rijId){
        if(analyse.controleerOfKostMetNummerAlIngevuldIs(kostId)){
            KostOfBaat tempKOB = analyse.geefKostMetNummer(kostId);
            if(tempKOB.controleerOfKOBRijMetNummerAlIngevuldIs(rijId)){
                return tempKOB.geefKOBRijMetNummer(rijId);
            }else{
                return null;
            }
        }else{
            return null;
        }
    }
}
