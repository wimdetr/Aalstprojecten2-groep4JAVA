/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domein.repository;

import domein.Analyse;
import domein.Werkgever;
import java.time.Instant;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import persistentie.AnalyseMapper;

/**
 *
 * @author ~dreeki~
 */
public class AnalyseRepository {

    private List<Analyse> lijst;

    public List<Analyse> getLijst() {
        return lijst;
    }
    private AnalyseMapper analyseMapper;

    public AnalyseRepository() {
        lijst = new ArrayList<>();
        analyseMapper = new AnalyseMapper();
        getAll();
    }

    
    public void getAll(){
        lijst = analyseMapper.getAll();
    }
    
    public List<Analyse> getNthMostRecent(int n){
        if(n >= lijst.size()){
            n = lijst.size();
        }
        return lijst.stream().sorted().limit(n).collect(Collectors.toList());
    }
    private void fillUpWithDummyData() {

        Analyse ana1 = new Analyse(1, new Date());
        Werkgever werk1 = new Werkgever("Colruyt", 9300, "Aalst");
        Analyse ana2 = new Analyse(7, new Date());
        Werkgever werk2 = new Werkgever("Delhaize", 9300, "Aalst");

        ana2.slaWerkgeverOp(werk2);
        ana1.slaWerkgeverOp(werk1);

        Analyse ana3 = new Analyse(4, new Date());
        Analyse ana4 = new Analyse(9, new Date());
        Analyse ana5 = new Analyse(16, new Date());

        Analyse ana6 = new Analyse(21, new Date());
        Analyse ana7 = new Analyse(22, new Date());
        Analyse ana8 = new Analyse(37, new Date());

        werk2.setNaamAfdeling("Supermarkt");
        Werkgever werk3 = new Werkgever("Jan De Nul", 9300, "Aalst");
        werk3.setNaamAfdeling("IT-department");
        ana3.slaWerkgeverOp(werk3);
        Werkgever werk4 = new Werkgever("HoGent", 9300, "Aalst");
        werk4.setNaamAfdeling("Opleidingsdepartement");
        ana4.slaWerkgeverOp(werk4);
        Werkgever werk5 = new Werkgever("Vandemoortele", 9000, "Gent");
        werk5.setNaamAfdeling("Marketing");
        ana5.slaWerkgeverOp(werk5);
        Werkgever werk6 = new Werkgever("Unilever", 1000, "Brussel");
        werk6.setNaamAfdeling("Management");
        ana6.slaWerkgeverOp(werk6);
        Werkgever werk7 = new Werkgever("De Nationale Loterij", 1000, "Brussel");
        werk7.setNaamAfdeling("Financiën");
        ana7.slaWerkgeverOp(werk7);
        Werkgever werk8 = new Werkgever("Oxfam", 9300, "Aalst");
        werk8.setNaamAfdeling("Human Resources");
        ana8.slaWerkgeverOp(werk8);

        lijst.add(ana1);
        lijst.add(ana2);
        lijst.add(ana3);
        lijst.add(ana4);
        lijst.add(ana5);
        lijst.add(ana6);
        lijst.add(ana7);
        lijst.add(ana8);
    }
}
