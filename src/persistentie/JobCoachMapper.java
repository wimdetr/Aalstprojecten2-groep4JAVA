/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistentie;

import domein.JobCoach;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import util.JPAUtil;

/**
 *
 * @author ~dreeki~
 */
public class JobCoachMapper {

    public JobCoachMapper() {
    }

    public List<JobCoach> getAll() {
        EntityManager em = JPAUtil.getEmf().createEntityManager();
        Query q = em.createNamedQuery("JobCoach.findAll", JobCoach.class);
        List<JobCoach> results = q.getResultList();
        em.close();
        return results;
    }

    public void modifyJobCoach(JobCoach j) {
        EntityManager em = JPAUtil.getEmf().createEntityManager();
        em.getTransaction().begin();
        em.merge(j);
        em.getTransaction().commit();
        em.close();
    }

    public void delete(JobCoach j) {
        EntityManager em = JPAUtil.getEmf().createEntityManager();
        em.getTransaction().begin();
        em.remove(em.contains(j) ? j : em.merge(j));
        Query q  = (Query) em.createNativeQuery("DELETE FROM aspnetusers WHERE username = ?1");
        q.setParameter(1,j.getEmail());
        q.executeUpdate();
        em.getTransaction().commit();
        em.close();
    }

}
