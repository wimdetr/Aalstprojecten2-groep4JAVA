/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import domein.Analyse;
import domein.KOBEnum;
import domein.KostOfBaat;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author wimde
 */
public class AnalyseTest {

    private Analyse analyse;
    private KostOfBaat kost1;
    private KostOfBaat kost1Punt1;
    private KostOfBaat kost2;
    private KostOfBaat kost3;
    private KostOfBaat kost4;
    private KostOfBaat kost5;
    private KostOfBaat kost6;
    private KostOfBaat kost7;

    private KostOfBaat baat1;
    private KostOfBaat baat2;
    private KostOfBaat baat3;
    private KostOfBaat baat4;
    private KostOfBaat baat5;
    private KostOfBaat baat6;
    private KostOfBaat baat7;
    private KostOfBaat baat8;
    private KostOfBaat baat9;
    private KostOfBaat baat10;
    private KostOfBaat baat11;

    @Before
    public void initialize() {
        analyse = new Analyse();
        kost1 = new KostOfBaat(1, 1000, KOBEnum.Kost);
        kost1Punt1 = new KostOfBaat(8, 1000, KOBEnum.Kost);
        kost1 = new KostOfBaat(1, 1000, KOBEnum.Kost);
        kost2 = new KostOfBaat(2, 1000, KOBEnum.Kost);
        kost3 = new KostOfBaat(3, 1000, KOBEnum.Kost);
        kost4 = new KostOfBaat(4, 1000, KOBEnum.Kost);
        kost5 = new KostOfBaat(5, 1000, KOBEnum.Kost);
        kost6 = new KostOfBaat(6, 1000, KOBEnum.Kost);
        kost7 = new KostOfBaat(7, 1000, KOBEnum.Kost);
        baat1 = new KostOfBaat(1, 1000, KOBEnum.Baat);
        baat2 = new KostOfBaat(2, 1000, KOBEnum.Baat);
        baat3 = new KostOfBaat(3, 1000, KOBEnum.Baat);
        baat4 = new KostOfBaat(4, 1000, KOBEnum.Baat);
        baat5 = new KostOfBaat(5, 1000, KOBEnum.Baat);
        baat6 = new KostOfBaat(6, 1000, KOBEnum.Baat);
        baat7 = new KostOfBaat(7, 1000, KOBEnum.Baat);
        baat8 = new KostOfBaat(8, 1000, KOBEnum.Baat);
        baat9 = new KostOfBaat(9, 1000, KOBEnum.Baat);
        baat10 = new KostOfBaat(10, 1000, KOBEnum.Baat);
        baat11 = new KostOfBaat(11, 1000, KOBEnum.Baat);

        analyse.slaKostOfBaatOp(kost1);
        analyse.slaKostOfBaatOp(kost1Punt1);
        analyse.slaKostOfBaatOp(kost2);
        analyse.slaKostOfBaatOp(kost3);
        analyse.slaKostOfBaatOp(kost4);
        analyse.slaKostOfBaatOp(kost5);
        analyse.slaKostOfBaatOp(kost6);
        analyse.slaKostOfBaatOp(kost7);
        analyse.slaKostOfBaatOp(baat1);
        analyse.slaKostOfBaatOp(baat2);
        analyse.slaKostOfBaatOp(baat3);
        analyse.slaKostOfBaatOp(baat4);
        analyse.slaKostOfBaatOp(baat5);
        analyse.slaKostOfBaatOp(baat6);
        analyse.slaKostOfBaatOp(baat7);
        analyse.slaKostOfBaatOp(baat8);
        analyse.slaKostOfBaatOp(baat9);
        analyse.slaKostOfBaatOp(baat10);
        analyse.slaKostOfBaatOp(baat11);
        analyse.berekenVolledigResultaat();
    }

    @Test
    public void testSubTotaalBaten() {
        Assert.assertEquals(11000, analyse.getResultaatBaten(), 0.001);

    }

    @Test
    public void testSubTotaalKosten() {
        Assert.assertEquals(8000, analyse.getResultaatKosten(), 0.001);

    }

    @Test
    public void testTotaal() {
        Assert.assertEquals(3000, analyse.getResultaat(), 0.001);

    }
}
