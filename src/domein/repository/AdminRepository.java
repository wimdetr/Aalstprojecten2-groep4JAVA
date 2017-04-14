/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domein.repository;

import domein.Admin;
import java.util.ArrayList;
import java.util.List;
import persistentie.AdminMapper;

/**
 *
 * @author ~dreeki~
 */
public class AdminRepository {

    private List<Admin> lijst;

    public List<Admin> getLijst() {
        return lijst;
    }
    private final AdminMapper adminMapper;

    public AdminRepository() {
        lijst = new ArrayList<>();
        adminMapper = new AdminMapper();
        fillUpWithDummyData();
    }

    public boolean controleerOfAdminKanInloggen(String username, String wachtwoord) {
        return adminMapper.controleerOfAdminKanInloggen(username, wachtwoord);
    }

    public Admin geefAdmin(String username) {
        return adminMapper.geefAdmin(username);
    }

    private void fillUpWithDummyData() {
        lijst.add(new Admin("De Troyer", "Wim", "wimdetroyer@gmail.com"));
        lijst.add(new Admin("De Witte", "Andreas", "andreasdewitte@gmail.com"));
        lijst.add(new Admin("De Bruyne", "Niels", "nielsdebruyne@gmail.com"));

    }

}
