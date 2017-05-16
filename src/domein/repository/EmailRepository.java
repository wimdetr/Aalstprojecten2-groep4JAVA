/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domein.repository;

import domein.Email;
import domein.JobCoach;
import java.util.ArrayList;
import java.util.List;
import persistentie.EmailMapper;

/**
 *
 * @author wimde
 */
public class EmailRepository {

    private List<Email> lijst;
    private EmailMapper mapper;

    public EmailRepository() {
        lijst = new ArrayList<>();
        mapper = new EmailMapper();
    }

    public List<Email> getLijst() {
        return lijst;
    }

    public void sendMail(Email email, List<JobCoach> ontvangers) {
        mapper.sendMail(email, ontvangers);
    }

}
