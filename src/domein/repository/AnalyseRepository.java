/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domein.repository;

import domein.Analyse;
import java.util.ArrayList;
import java.util.List;
import persistentie.AnalyseMapper;

/**
 *
 * @author ~dreeki~
 */
public class AnalyseRepository {
    private List<Analyse> lijst;
    private AnalyseMapper analyseMapper;

    public AnalyseRepository() {
        lijst = new ArrayList<>();
        analyseMapper = new AnalyseMapper();
    }
}
