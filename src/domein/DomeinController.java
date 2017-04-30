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
    private AdminRepository adminRepo;

    private JobCoachRepository jobCoachRepo;
    private AnalyseRepository analyseRepo;

    private WerkgeverRepository bedrijfRepo;
    private Resultaat resultaat;

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
        resultaat = new Resultaat();
    }

    public void logAdminUit() {
        admin = null;
    }

    public void berekenResultaatVanKostOfBaatMetId(String jobCoachMail, int analyseId, int kostOfBaatId, KOBEnum kob) {
        JobCoach coach = null;
        if (admin.controleerOfCoachMetEmailBestaat(jobCoachMail)) {
            coach = admin.geefCoachMetEmail(jobCoachMail);
        }
        Analyse analyse = null;
        if (coach != null && coach.controleerOfAnalyseAanwezigIs(analyseId)) {
            analyse = coach.geefAnalyseMetId(analyseId);
        }
        KostOfBaat kostOfBaat = null;
        if (analyse != null && kob == KOBEnum.Baat) {
            if (analyse.controleerOfBaatMetNummerAlIngevuldIs(kostOfBaatId)) {
                kostOfBaat = analyse.geefBaatMetNummer(kostOfBaatId);
            }
        } else if (analyse != null && kob == KOBEnum.Kost) {
            if (analyse.controleerOfKostMetNummerAlIngevuldIs(kostOfBaatId)) {
                kostOfBaat = analyse.geefKostMetNummer(kostOfBaatId);
            }
        }
        if (kostOfBaat != null) {
            for (KOBRij rij : kostOfBaat.getRijen()) {
                resultaat.berekenEnSetResultaat(rij);
            }
            kostOfBaat.berekenResultaat();
        }
    }

    public void berekenAlleResultatenVanAnalyse(String jobCoachMail, int analyseId) {
        JobCoach coach = null;
        if (admin.controleerOfCoachMetEmailBestaat(jobCoachMail)) {
            coach = admin.geefCoachMetEmail(jobCoachMail);
        }
        Analyse analyse = null;
        if (coach != null && coach.controleerOfAnalyseAanwezigIs(analyseId)) {
            analyse = coach.geefAnalyseMetId(analyseId);
        }
        if (analyse != null) {
            for (KostOfBaat kob : analyse.getBaten()) {
                kob.getRijen().forEach((rij) -> {
                    resultaat.berekenEnSetResultaat(rij);
                });
                kob.berekenResultaat();
            }
            for (KostOfBaat kob : analyse.getKosten()) {
                kob.getRijen().forEach((rij) -> {
                    resultaat.berekenEnSetResultaat(rij);
                });
                kob.berekenResultaat();
            }
        }
    }
}
