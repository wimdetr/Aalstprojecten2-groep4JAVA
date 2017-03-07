/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistentie;

import domein.Admin;

/**
 *
 * @author ~dreeki~
 */
public class AdminMapper {

    public AdminMapper() {
    }
    
    public boolean controleerOfAdminKanInloggen(String username, String wachtwoord){
        return true;
        //return username.equals("admin@gmail.com") && wachtwoord.equals("test");
    }
    
    public Admin geefAdmin(String username){
        return new Admin("De Witte", "Andreas", "dreeki");
    }

}
