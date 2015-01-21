package it.unical.ilBelloDelleDonne.ApplicationData;

import it.unical.ilBelloDelleDonne.Hibernate.Model.User;

import java.io.IOException;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;



public abstract class SendEmail {

	public static void send(String emailType, User newUser) throws IOException {

        final String user = "ilBelloDelleDonne.help@gmail.com";
        final String password = "vi89giu89";
        String to = newUser.getEmail();
        String title = "";
        String textmsg = "";
        
        if(emailType.equals("registration")){
        	title = "Welcome!";
        	textmsg = "Benvenuto sul nostro portale "+newUser.getName()+"! Da oggi potrai prenotare i nostri servizi ed acquistare i nostri prodotti.";
        }
        
        else if(emailType.equals("admin registration")){
        	System.err.println(newUser.getName()+newUser.getAccount().getPassword());
        	title="Welcome!";
        	textmsg = "Sei stato registrato nel nostro sistema. Le tue credenziali d'accesso sono: {username= "+newUser.getAccount().getUsername()
        						+" password="+newUser.getAccount().getPassword()+"}.";
        }
        else if(emailType.equals("confirm selling")){
        	title = "confirm selling";
        	textmsg = "Hai appena effettuato una vendita.\n Riepilogo vendita: "
        			 +"";
        	
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
