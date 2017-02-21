/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilities;

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
    private final Properties PROPS;
    //private static final String USERNAME = "colorettorecovery@gmail.com";
    //private static final String WACHTWOORD = "colorettohogent2016";
    private final String USERNAME;
    private final String WACHTWOORD;
    
    public MailVerzender(String username, String wachtwoord){
        USERNAME = username;
        WACHTWOORD = wachtwoord;
        this.PROPS = new Properties();
        PROPS.put("mail.smtp.auth", "true");
        PROPS.put("mail.smtp.starttls.enable", "true");
        PROPS.put("mail.smtp.host", "smtp.gmail.com");
        PROPS.put("mail.smtp.port", "587");
    }
    
    public void sendMail(String wachtwoord, String mailAdres) throws MessagingException{
        Session session = Session.getInstance(PROPS, new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication(){
                return new PasswordAuthentication(USERNAME, WACHTWOORD);
            }
        });
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(USERNAME));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(mailAdres));
        message.setSubject("Wachtwoord Recovery");
        message.setText("Geachte,\n\nU meldde dat u uw wachtwoord vergeten bent, u kan opnieuw een eerste maal inloggen met het wachtwoord " + wachtwoord + ".\nU zal na het inloggen uw nieuw zelfgekozen wachtwoord moeten ingeven.\n\nMet vriendelijke groeten,\nKairos");
        
        Transport.send(message);
    }
}
