/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import domein.Formule;
import domein.KOBRij;
import domein.KostOfBaat;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author wimde
 */
public class KostOfBaatTest {

    private KostOfBaat kostOfBaat;
    private KOBRij rij1;
    private KOBRij rij2;
    private KOBRij rij3;

    @Before
    public void initialize() {
        kostOfBaat = new KostOfBaat();
        rij1 = new KOBRij(1);
        rij2 = new KOBRij(2);
        rij3 = new KOBRij(3);
        kostOfBaat.addKOBRij(rij1);
        kostOfBaat.addKOBRij(rij2);
        kostOfBaat.addKOBRij(rij3);

    }

    @Test
    public void Kost1() {
        kostOfBaat.setFormule(Formule.FORMULE_KOST1);
        rij1.setResultaat(1000);
        rij2.setResultaat(250);
        rij3.setResultaat(3800);

        kostOfBaat.berekenResultaat();
        Assert.assertEquals(60600, kostOfBaat.getResultaat(), 0.01);
    }

    @Test
    public void Kost1Punt1() {
        kostOfBaat.setFormule(Formule.FORMULE_GEEF_VAK2);
        rij1.setResultaat(1000);
        rij2.setResultaat(250);
        rij3.setResultaat(3800);

        kostOfBaat.berekenResultaat();
        Assert.assertEquals(5050, kostOfBaat.getResultaat(), 0.01);
    }

    @Test
    public void testFormuleGeefVak2() {
        kostOfBaat.setFormule(Formule.FORMULE_GEEF_VAK2);
        rij1.setResultaat(1000);
        rij2.setResultaat(250);
        rij3.setResultaat(3800);

        kostOfBaat.berekenResultaat();
        Assert.assertEquals(5050, kostOfBaat.getResultaat(), 0.01);
    }

    @Test
    public void testFormuleGeefVak1() {
        kostOfBaat.setFormule(Formule.FORMULE_GEEF_VAK1);
        rij1.setResultaat(5000);
        kostOfBaat.berekenResultaat();
        Assert.assertEquals(5000, kostOfBaat.getResultaat(), 0.01);
    }

    @Test
    public void testFormuleBaat3en4() {
        kostOfBaat.setFormule(Formule.FORMULE_BAAT_3_EN_4);
        rij1.setResultaat(1000);
        rij2.setResultaat(250);
        rij3.setResultaat(3800);

        kostOfBaat.berekenResultaat();
        Assert.assertEquals(5050, kostOfBaat.getResultaat(), 0.01);
    }
}
