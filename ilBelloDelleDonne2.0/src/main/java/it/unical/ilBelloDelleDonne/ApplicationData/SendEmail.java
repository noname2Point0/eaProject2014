package it.unical.ilBelloDelleDonne.ApplicationData;

import java.io.IOException;
import java.util.*;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;



public abstract class SendEmail {

	public static void send(String emailType, String newUser, String recipient) throws IOException {

        final String user = "ilBelloDelleDonne.help@gmail.com";
        final String password = "vi89giu89";
        String to = recipient;
        String title = "";
        String textmsg = "";
        
        if(emailType.equals("registration")){
        	title = "Welcome!";
        	textmsg = "Benvenuto sul nostro portale "+newUser+"! \n Da oggi potrai prenotare i nostri servizi ed acquistare i nostri prodotti.";
        }
  
        Properties properties = System.getProperties();
        properties.setProperty("mail.transport.protocol", "smtp");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.setProperty("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.auth", "true");

        Session session = Session.getDefaultInstance(properties,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(user, password);
                    }
                });

        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(user, "IlBelloDelleDonne Team"));

            message.addRecipient(Message.RecipientType.TO,
                    new InternetAddress(to));
            message.setSubject(title);
            message.setContent(textmsg, "text/html");

            Transport.send(message);
        } catch (MessagingException ex) {
            ex.printStackTrace();
        }
    }

}
