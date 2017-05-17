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
import java.util.Comparator;
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

    public void getAll() {
        lijst = analyseMapper.getAll();
    }

    public List<Analyse> getNthMostRecent(int n) {
        if (n >= lijst.size()) {
            n = lijst.size();
        }
        return lijst.stream().sorted(Comparator.reverseOrder()).limit(n).collect(Collectors.toList());
    }
}
