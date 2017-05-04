/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import com.mysql.jdbc.Connection;
import java.util.HashMap;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author wimde
 */
public class JPAUtil {

    private static EntityManagerFactory emf;

    public static void prepareEmf(Map<String, String> properties) {
        if (emf == null) {
            emf = Persistence.createEntityManagerFactory("kairos", properties);
        }
    }

    public static EntityManagerFactory getEmf() {
        return emf;
    }

    public static void destroyConnection(){
        emf.close();
        emf = null;
    }
    private JPAUtil() {
    }
}
