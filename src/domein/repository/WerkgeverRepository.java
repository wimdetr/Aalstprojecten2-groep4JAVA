/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domein.repository;

import domein.Werkgever;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import persistentie.WerkgeverMapper;

/**
 *
 * @author wim
 */
public class WerkgeverRepository {

    private List<Werkgever> lijst;

    public List<Werkgever> getLijst() {
        return lijst;
    }
    private WerkgeverMapper werkgeverMapper;

    public WerkgeverRepository() {
        lijst = new ArrayList<>();
        werkgeverMapper = new WerkgeverMapper();
        lijst = werkgeverMapper.getAll();
    }

    public List<Werkgever> zoekNaam(String query) {
        return lijst.stream()
                .filter(p -> p.getNaam().toLowerCase().startsWith(query.toLowerCase()))
                .collect(Collectors.toList());
    }

    public List<Werkgever> zoekAfdeling(String query) {
        return lijst.stream()
 //               .filter(p -> p.getNaamAfdeling().toLowerCase().startsWith(query.toLowerCase()))
                .collect(Collectors.toList());
    }

    public List<Werkgever> zoekGemeente(String query) {
        return lijst.stream()
       //         .filter(p -> p.getGemeente().toLowerCase().startsWith(query.toLowerCase()))
                .collect(Collectors.toList());
    }
}
