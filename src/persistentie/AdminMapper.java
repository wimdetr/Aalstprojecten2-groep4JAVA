/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistentie;

import domein.Admin;
import domein.AdminMail;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javafx.application.Platform;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import org.apache.commons.lang.RandomStringUtils;
import util.JPAUtil;
import util.MailVerzender;

/**
 *
 * @author ~dreeki~
 */
public class AdminMapper {

    public AdminMapper() {

    }

    public boolean controleerOfAdminKanInloggen(String username, String wachtwoord) {
        Map<String, String> properties = new HashMap<>();
        properties.put("javax.persistence.jdbc.user", username);
        properties.put("javax.persistence.jdbc.password", wachtwoord);
        try {
            JPAUtil.prepareEmf(properties);
        } catch (Exception e) { // if user is not in DB
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public void updateEmailsVoorAdmin(Admin a) {
        EntityManager em = JPAUtil.getEmf().createEntityManager();
        em.getTransaction().begin();
        TypedQuery<AdminMail> q = em.createNamedQuery("AdminMail.findAll", AdminMail.class);
        q.setParameter("ontvanger", a);
        List<AdminMail> mails = q.getResultList();
        em.getTransaction().commit();
        em.close();
        a.setMails(mails);
    }

    public Admin geefAdmin(String username) {
        EntityManager em = JPAUtil.getEmf().createEntityManager();
        TypedQuery<Admin> q = em.createNamedQuery("Admin.findAdmin", Admin.class);
        q.setParameter("name", username);
        Admin results = q.getSingleResult();
        em.close();
        return results;
    }

    public List<Admin> getAdmins() {
        EntityManager em = JPAUtil.getEmf().createEntityManager();
        TypedQuery<Admin> q = em.createNamedQuery("Admin.findAll", Admin.class);
        List<Admin> results = q.getResultList();
        em.close();
        return results;
    }

    /*
    TODO: Make this multithreaded!
    Currently the code does NOT run completely off the javaFX thread so the gui blocks sometimes.
     */
    public void addAdmin(Admin a) {
        EntityManager em = JPAUtil.getEmf().createEntityManager();
        em.getTransaction().begin();

        /*
        Send mail
         */
        String password = RandomStringUtils.randomAlphanumeric(8);
        String subject = "Logingegevens Administratietool";
        StringBuilder sb = new StringBuilder();
        sb.append("Gebruik volgende gegevens om in te loggen in de administratietool: ");
        sb.append("\n\n");
        sb.append("Username: ");
        sb.append(a.getEmail());
        sb.append("\n");
        sb.append("Wachtwoord: ");
        sb.append(password);
        sb.append("\n\n");
        sb.append("Dit is een geautomatiseerde mail. Indien u problemen ondervindt kan u gerust terugmailen naar ons.");
        String content = sb.toString();
        Thread t = new Thread(() -> {
            MailVerzender.sendMail(a.getEmail(), subject, content);
        });
        Platform.runLater(t);

        /*
        Insert into mysql.users
        Niet sql injection safe (het werkte anders niet, navragen bij V. Herreweghe)
         */
        Query create = em.createNativeQuery("CREATE USER " + "'" + a.getEmail() + "'" + "@'%' IDENTIFIED BY " + "'" + password + "'");
        Query globalgrant = em.createNativeQuery("GRANT CREATE USER ON *.* TO " + "'" + a.getEmail() + "'" + "WITH GRANT OPTION");
        Query grant = em.createNativeQuery("GRANT DELETE,EXECUTE,INSERT,GRANT OPTION,SELECT, UPDATE ON kairos.* TO " + "'" + a.getEmail() + "'" + "@'%'" + "WITH GRANT OPTION");

        create.executeUpdate();
        globalgrant.executeUpdate();
        grant.executeUpdate();
        em.persist(a);
        em.getTransaction().commit();
        em.close();
    }

    public void deleteAdmin(Admin a) {

        EntityManager em = JPAUtil.getEmf().createEntityManager();
        em.getTransaction().begin();
        em.remove(em.contains(a) ? a : em.merge(a));
        Query drop = em.createNativeQuery("DROP USER " + "'" + a.getEmail() + "'" + "@'%'");
        drop.executeUpdate();
        em.getTransaction().commit();
        em.close();
    }

    public void changePasswordForCurrentUser(String password) {
        EntityManager em = JPAUtil.getEmf().createEntityManager();
        em.getTransaction().begin();
        Query changePw = em.createNativeQuery("SET PASSWORD = " + "?1");
        changePw.setParameter(1, password);
        changePw.executeUpdate();
        em.getTransaction().commit();
        em.close();
    }

    public void deleteMail(AdminMail m) {
        EntityManager em = JPAUtil.getEmf().createEntityManager();
        em.getTransaction().begin();
        em.remove(em.contains(m) ? m : em.merge(m));
        em.getTransaction().commit();
        em.close();
    }

    public void modifyMail(AdminMail m) {
        EntityManager em = JPAUtil.getEmf().createEntityManager();
        em.getTransaction().begin();
        em.merge(m);
        em.getTransaction().commit();
        em.close();
    }

}
