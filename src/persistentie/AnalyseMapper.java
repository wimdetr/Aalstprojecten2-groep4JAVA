/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistentie;

import domein.Analyse;
import domein.KostOfBaat;
import java.util.List;
import javax.persistence.CacheRetrieveMode;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import org.eclipse.persistence.config.CascadePolicy;
import org.eclipse.persistence.config.HintValues;
import org.eclipse.persistence.config.QueryHints;
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
        em.flush();
        TypedQuery<Analyse> q = em.createNamedQuery("Analyse.findAll", Analyse.class);
        q.setHint(QueryHints.REFRESH, HintValues.TRUE);
        q.setHint(QueryHints.REFRESH_CASCADE, CascadePolicy.CascadeAllParts);
        List<Analyse> results = q.setHint(QueryHints.CACHE_RETRIEVE_MODE, CacheRetrieveMode.BYPASS).getResultList();
        em.getTransaction().commit();
        em.close();
        return results;
    }


}
