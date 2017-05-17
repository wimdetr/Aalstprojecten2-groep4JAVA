/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistentie;

import domein.Analyse;
import domein.KostOfBaat;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import util.JPAUtil;

/**
 *
 * @author ~dreeki~
 */
public class AnalyseMapper {

    public AnalyseMapper() {
    }

    public List<Analyse> getAll() {
        EntityManager em = JPAUtil.getEmf().createEntityManager();
        em.getTransaction().begin();
        TypedQuery<Analyse> q = em.createNamedQuery("Analyse.findAll", Analyse.class);
        List<Analyse> results = q.getResultList();
        em.getTransaction().commit();
        em.close();
        return results;
    }


}
