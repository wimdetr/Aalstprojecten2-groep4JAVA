/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domein;

import domein.repository.AdminRepository;
import domein.repository.AnalyseRepository;
import domein.repository.DoelgroepRepository;
import domein.repository.WerkgeverRepository;
import domein.repository.JobCoachRepository;

/**
 *
 * @author ~dreeki~
 */
public class DomeinController {

    private Admin admin;

    private AdminRepository adminRepo;

    private JobCoachRepository jobCoachRepo;
    private AnalyseRepository analyseRepo;

    private WerkgeverRepository bedrijfRepo;
    private DoelgroepRepository doelgroepRepo;
    private Resultaat resultaat;

    public WerkgeverRepository getBedrijfRepo() {
        return bedrijfRepo;
    }

    public DoelgroepRepository getDoelgroepRepo() {
        return doelgroepRepo;
    }

    public Resultaat getResultaat() {
        return resultaat;
    }

    public DomeinController() {
        adminRepo = new AdminRepository();
    }

    public Admin getAdmin() {
        return admin;
    }

    public JobCoachRepository getJobCoachRepo() {
        return jobCoachRepo;
    }

    public AnalyseRepository getAnalyseRepo() {
        return analyseRepo;
    }

    public AdminRepository getAdminRepo() {
        return adminRepo;
    }

    public boolean controleerOfAdminKanInloggen(String username, String wachtwoord) {
        return adminRepo.controleerOfAdminKanInloggen(username, wachtwoord);
    }

    public void logAdminIn(String username) {
        admin = adminRepo.geefAdmin(username);
        adminRepo.setLijst(adminRepo.getAdminMapper().getAdmins());
        jobCoachRepo = new JobCoachRepository();
        analyseRepo = new AnalyseRepository();
        bedrijfRepo = new WerkgeverRepository();
        doelgroepRepo = new DoelgroepRepository();
        resultaat = new Resultaat(doelgroepRepo.getLijst());
        berekenResultaten();
    }

    public void logAdminUit() {
        admin = null;
    }

    public void berekenResultaten() {
        // todo; mss ipv allemaal uit te rekenen per 10 of 20? (performance wise)
        analyseRepo.getLijst().forEach((a) -> {
            resultaat.berekenAnalyse(a);
        });
    }
}
