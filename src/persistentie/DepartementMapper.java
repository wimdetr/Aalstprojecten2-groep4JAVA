package persistentie;

import domein.Departement;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import util.JPAUtil;

/**
 *
 * @author wimde
 */
public class DepartementMapper {

    public List<Departement> getAll() {
        EntityManager em = JPAUtil.getEmf().createEntityManager();
        em.getTransaction().begin();
        TypedQuery<Departement> q = em.createNamedQuery("Departement.findAll", Departement.class);
        List<Departement> results = q.getResultList();
        em.getTransaction().commit();
        em.close();
        return results;
    }

}
