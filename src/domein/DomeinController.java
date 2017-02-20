/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domein;

import domein.repository.AdminRepository;
import domein.repository.AnalyseRepository;
import domein.repository.WerkgeverRepository;
import domein.repository.JobCoachRepository;

/**
 *
 * @author ~dreeki~
 */
public class DomeinController {
    private Admin admin;
    private final AdminRepository adminRepo;
    private final JobCoachRepository jobCoachRepo;
    private final AnalyseRepository analyseRepo;
    private final WerkgeverRepository bedrijfRepo;

    public DomeinController() {
        adminRepo = new AdminRepository();
        jobCoachRepo = new JobCoachRepository();
        analyseRepo = new AnalyseRepository();
        bedrijfRepo = new WerkgeverRepository();
    }

    public Admin getAdmin() {
        return admin;
    }
    
    public boolean controleerOfAdminKanInloggen(String username, String wachtwoord){
        return adminRepo.controleerOfAdminKanInloggen(username, wachtwoord);
    }
    
    public void logAdminIn(String username){
        admin = adminRepo.geefAdmin(username);
    }
}
