/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistentie;

import domein.Doelgroep;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import util.JPAUtil;

/**
 *
 * @author wimde
 */
public class DoelgroepMapper {

    public List<Doelgroep> getAll() {
        EntityManager em = JPAUtil.getEmf().createEntityManager();
        em.getTransaction().begin();
        TypedQuery<Doelgroep> q = em.createNamedQuery("Doelgroep.findAll", Doelgroep.class);
        em.getTransaction().commit();
        return q.getResultList();
    }

    public void addDoelgroep(Doelgroep d) {
        EntityManager em = JPAUtil.getEmf().createEntityManager();
        em.getTransaction().begin();
        em.persist(d);
        em.getTransaction().commit();
        em.close();

    }

    public void modifyDoelgroep(Doelgroep d) {
        EntityManager em = JPAUtil.getEmf().createEntityManager();
        em.getTransaction().begin();
        em.merge(d);
        em.getTransaction().commit();
        em.close();

    }

}
