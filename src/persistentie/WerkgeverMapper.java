/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistentie;

import domein.Werkgever;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import util.JPAUtil;

/**
 *
 * @author wim
 */
public class WerkgeverMapper {

    public List<Werkgever> getAll() {
        EntityManager em = JPAUtil.getEmf().createEntityManager();
        em.getTransaction().begin();
        TypedQuery<Werkgever> q = em.createNamedQuery("Werkgever.findAll", Werkgever.class);
        List<Werkgever> results = q.getResultList();
        em.getTransaction().commit();
        em.close();
        return results;
    }

}
