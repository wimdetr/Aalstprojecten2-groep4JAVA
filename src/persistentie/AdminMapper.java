/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistentie;

import domein.Admin;
import java.util.HashMap;
import java.util.Map;
import util.JPAUtil;

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

    public Admin geefAdmin(String username) {
        return new Admin("De Witte", "Andreas", "dreeki");
    }

}
