/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domein.repository;

import domein.Admin;
import domein.AdminMail;
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

    public AdminMapper getAdminMapper() {
        return adminMapper;
    }

    public AdminRepository() {
        lijst = new ArrayList<>();
        adminMapper = new AdminMapper();
    }
    public void updateEmailsVoorAdmin(Admin a) {
        adminMapper.updateEmailsVoorAdmin(a);
    }

    public boolean controleerOfAdminKanInloggen(String username, String wachtwoord) {
        return adminMapper.controleerOfAdminKanInloggen(username, wachtwoord);
    }

    public Admin geefAdmin(String username) {
        return adminMapper.geefAdmin(username);
    }

    public void setLijst(List<Admin> a) {
        lijst = a;
    }

    public void addAdmin(Admin a) {
        adminMapper.addAdmin(a);
        lijst.add(a);
    }

    public void deleteAdmin(Admin a) {
        adminMapper.deleteAdmin(a);
    }

    public void modifyMail(AdminMail m) {
        adminMapper.modifyMail(m);
    }

    public void deleteMail(AdminMail m) {
        adminMapper.deleteMail(m);
    }

    public void changePasswordForCurrentUser(String password) {
        adminMapper.changePasswordForCurrentUser(password);
    }

    private void fillUpWithDummyData() {
        /*
        Werd gebruikt voor dummydata zonder DB connectie.
         */
        lijst.add(new Admin("De Troyer", "Wim", "wimdetroyer@gmail.com"));
        lijst.add(new Admin("De Witte", "Andreas", "andreasdewitte@gmail.com"));
        lijst.add(new Admin("De Bruyne", "Niels", "nielsdebruyne@gmail.com"));

    }

}
