/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domein.repository;

import domein.Doelgroep;
import java.util.ArrayList;
import java.util.List;
import persistentie.DoelgroepMapper;

/**
 *
 * @author wimde
 */
public class DoelgroepRepository {

    private List<Doelgroep> lijst;
    private final DoelgroepMapper mapper;

    public List<Doelgroep> getLijst() {
        return lijst;
    }

    public DoelgroepRepository() {
        lijst = new ArrayList<>();
        mapper = new DoelgroepMapper();
        lijst = mapper.getAll();
    }

    public void addDoelgroep(Doelgroep d) {
        mapper.addDoelgroep(d);
        lijst.add(d);
    }

    public void modifyDoelgroep(Doelgroep d){
       mapper.modifyDoelgroep(d);
    }
}
