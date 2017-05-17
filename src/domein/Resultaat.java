/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domein;

import java.util.List;

/**
 *
 * @author ~dreeki~
 */
public class Resultaat {

    private KostOfBaat kostOfBaat;
    private Analyse analyse;
    private List<Doelgroep> doelgroepen;

    public Resultaat(List<Doelgroep> doelgroepen) {
        this.doelgroepen = doelgroepen;
    }

    public void berekenAnalyse(Analyse a) {
        for (KostOfBaat kob : a.getKostenEnBaten()) {
            geefParametersDoor(kob, a);
            for (KOBRij rij : kob.getRijen()) {
                berekenRij(rij);
            }
            kob.berekenResultaat();
        }
        a.berekenVolledigResultaat();
    }

    public void geefParametersDoor(KostOfBaat kob, Analyse a) {
        kostOfBaat = kob;
        analyse = a;
    }

    public void clear() {
        kostOfBaat = null;
        analyse = null;
    }

    public void berekenRij(KOBRij mijnRij) {
        int aantalWerkuren = analyse.getDepartement().getAantalWerkuren();
        switch (kostOfBaat.getFormule()) {
            case FORMULE_BAAT1:
                if (aantalWerkuren == 0) {
                    //todo, eigen divide by zero exception schrijven
                    //throw 
                }
                KOBRij kostRij = geefRijVanKost(kostOfBaat.getVraagId(), mijnRij.getkOBRijId());
                if (kostRij == null) {
                    mijnRij.setResultaat(mijnRij.geefKOBVakMetNummer(2).geefDataAlsDouble());
                } else {
                    herberekenKost1(kostRij, aantalWerkuren);
                    double tussenWaarde = 0;
                    String dataVak4 = kostRij.geefKOBVakMetNummer(4).getDataVak();
                    double dataVak3 = kostRij.geefKOBVakMetNummer(3).geefDataAlsDouble();
                    double dataVak2 = kostRij.geefKOBVakMetNummer(2).geefDataAlsDouble();

                    Doelgroep doelgroep = doelgroepen.stream().filter(d -> d.getDoelgroepText().equals(dataVak4)).findFirst().orElse(null);

                    if (dataVak3 < doelgroep.getDoelgroepMaxLoon()) {
                        tussenWaarde = doelgroep.getDoelgroepWaarde() / aantalWerkuren * dataVak2 / 4;
                    }

                    double tussenWaarde2 = (kostRij.getResultaat() - tussenWaarde) * kostRij.geefKOBVakMetNummer(5).geefDataAlsDouble() / 100;
                    mijnRij.setResultaat((kostRij.getResultaat() - (tussenWaarde2 + tussenWaarde)) * (13.92 - mijnRij.geefKOBVakMetNummer(1).geefDataAlsDouble()) + mijnRij.geefKOBVakMetNummer(2).geefDataAlsDouble());
                }
                break;
            case FORMULE_KOST1:
                if (aantalWerkuren == 0) {
                    //todo, eigen divide by zero exception schrijven
                    //throw 
                }
                mijnRij.setResultaat((mijnRij.geefKOBVakMetNummer(3).geefDataAlsDouble() / aantalWerkuren * mijnRij.geefKOBVakMetNummer(2).geefDataAlsDouble()) * (1 + ((double) analyse.getDepartement().getWerkgever().getPatronaleBijdrage() / 100)));
                break;
            case FORMULE_GEEF_VAK2:
                mijnRij.setResultaat(mijnRij.geefKOBVakMetNummer(2).geefDataAlsDouble());
                break;
            case FORMULE_KOST6:
                mijnRij.setResultaat(mijnRij.geefKOBVakMetNummer(1).geefDataAlsDouble() / 152 * mijnRij.geefKOBVakMetNummer(2).geefDataAlsDouble() * (1 + ((double) analyse.getDepartement().getWerkgever().getPatronaleBijdrage() / 100)));
                break;
            case FORMULE_GEEF_VAK1:
                mijnRij.setResultaat(mijnRij.geefKOBVakMetNummer(1).geefDataAlsDouble());
                break;
            case FORMULE_BAAT_3_EN_4:
                if (mijnRij.geefKOBVakMetNummer(1).getDataVak().trim().equals("")) {
                    mijnRij.setResultaat(0);
                } else {
                    mijnRij.setResultaat(mijnRij.geefKOBVakMetNummer(1).geefDataAlsDouble() / aantalWerkuren * mijnRij.geefKOBVakMetNummer(2).geefDataAlsDouble() * (1 + ((double) analyse.getDepartement().getWerkgever().getPatronaleBijdrage() / 100)) * 13.92);
                }
                break;
            case FORMULE_SOM_VAK_1_EN_2:
                mijnRij.setResultaat(mijnRij.geefKOBVakMetNummer(1).geefDataAlsDouble() + mijnRij.geefKOBVakMetNummer(2).geefDataAlsDouble());
                break;
            case FORMULE_VERMENIGVULDIG_VAK_1_EN_2:
                mijnRij.setResultaat(mijnRij.geefKOBVakMetNummer(1).geefDataAlsDouble() * mijnRij.geefKOBVakMetNummer(2).geefDataAlsDouble() / 100);
                break;
            default:
                mijnRij.setResultaat(0);
                break;
        }
    }

    public void herberekenKost1(KOBRij rij, int aantalWerkuren) {
        if (aantalWerkuren == 0) {
            //??
        }
        rij.setResultaat(rij.geefKOBVakMetNummer(3).geefDataAlsDouble()
                / aantalWerkuren * rij.geefKOBVakMetNummer(2).geefDataAlsDouble()
                * (1 + (double) analyse.getDepartement().getWerkgever().getPatronaleBijdrage() / 100));
    }

    public KOBRij geefRijVanKost(int kostId, int rijId) {
        if (analyse.controleerOfVraagNummerAlIngevuldIs(KOBEnum.Kost, kostId)) {
            KostOfBaat tempKOB = analyse.geefKostOfBaatMetNummer(KOBEnum.Kost, kostId);
            if (tempKOB.controleerOfKOBRijMetNummerAlIngevuldIs(rijId)) {
                return tempKOB.geefKOBRijMetNummer(rijId);
            }
        }
        return null;

    }
}
