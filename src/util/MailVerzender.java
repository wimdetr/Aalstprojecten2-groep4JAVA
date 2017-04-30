/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author ~dreeki~
 */
public class MailVerzender {

    /*
    for security purposes the DB should hold the username and password,imo
    will keep it this way just for fastness
    
    een link met de JAR in bijlage zou handig zijn.
     */
    private static final Properties PROPS = new Properties();
    private static final String USERNAME = "ITSolutions.Kairos@gmail.com";
    private static final String WACHTWOORD = "ITSolutions123";

    public MailVerzender() {

    }

    private static void prepareProperties() {
        PROPS.put("mail.smtp.auth", "true");
        PROPS.put("mail.smtp.starttls.enable", "true");
        PROPS.put("mail.smtp.host", "smtp.gmail.com");
        PROPS.put("mail.smtp.port", "587");
    }

    public static void sendMail(String recipient, String subject, String content) {
        if (PROPS.isEmpty()) {
            prepareProperties();
        }

        Session session = Session.getInstance(PROPS, new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(USERNAME, WACHTWOORD);
            }
        });
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(USERNAME));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipient));
            message.setSubject(subject);
            message.setText(content);
            Transport.send(message);
        }
        catch(MessagingException e){
            e.printStackTrace();
        }
    }

  
}
