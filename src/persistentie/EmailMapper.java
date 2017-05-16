/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistentie;

import domein.Email;
import domein.EmailJobCoach;
import domein.JobCoach;
import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.EntityManager;
import util.JPAUtil;

/**
 *
 * @author wimde
 */
public class EmailMapper {

    public void sendMail(Email email, List<JobCoach> ontvangers) {
        EntityManager em = JPAUtil.getEmf().createEntityManager();
        em.getTransaction().begin();
        List<EmailJobCoach> ontvangerAdapter = ontvangers.stream()
                .map(e -> new EmailJobCoach(email, e)).collect(Collectors.toList());
        email.setOntvangers(ontvangerAdapter);
        em.persist(em.contains(email) ? email : em.merge(email));
        em.getTransaction().commit();
        em.close();
    }
}
