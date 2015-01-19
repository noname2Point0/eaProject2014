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


	public static void send2(){
		System.out.println("eccomi sono nel metodo send------------------");
		// Recipient's email ID needs to be mentioned. giucristiano89@gmail.com
		String to = "algieriv@gmail.com";

		// Sender's email ID needs to be mentioned
		String from = "algieriv@gmail.com";

		// Assuming you are sending email from localhost
		String host = "localhost";

		// Get system properties
		Properties properties = System.getProperties();

		// Setup mail server
		properties.setProperty("mail.smtp.host", host);

		// Get the default Session object.
		Session session = Session.getDefaultInstance(properties);


		try{
			// Create a default MimeMessage object.
			MimeMessage message = new MimeMessage(session);

			// Set From: header field of the header.
			message.setFrom(new InternetAddress(from));

			// Set To: header field of the header.
			message.addRecipient(Message.RecipientType.TO,
					new InternetAddress(to));

			// Set Subject: header field
			message.setSubject("This is the Subject Line!");

			// Now set the actual message
			message.setText("This is actual message");

			// Send message
			Transport.send(message);
			System.out.println("Sent message successfully....");
		}catch (MessagingException mex) {
			System.out.println("---------------------------------------------------------");
			mex.printStackTrace();
		}

	}

	public static void send3(){
		Properties props = new Properties();
		props.put("mail.smtp.starttls.enable", "true");
		props.setProperty("mail.transport.protocol", "smtp");
		props.setProperty("mail.smtp.host", "smtp.gmail.com");
		props.setProperty("mail.user", "algieriv@gmail.com");
		props.setProperty("mail.password", "dragonlfy");
		props.put("mail.smtp.auth", "true");
		//    props.setProperty("mail.password", "");

		try{
			Session mailSession = Session.getDefaultInstance(props, null);
			Transport transport = mailSession.getTransport();

			MimeMessage message = new MimeMessage(mailSession);
			message.setSubject("Testing javamail plain");
			message.setContent("This is a test", "text/plain");
			message.addRecipient(Message.RecipientType.TO,
					new InternetAddress("giucristiano89@gmail.com"));

			transport.connect();
			transport.sendMessage(message,message.getRecipients(Message.RecipientType.TO));
			transport.close();
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public static void send(String emailType, String newUser, String recipient) throws IOException {

        final String user = "ilBelloDelleDonne.help@gmail.com";
        final String password = "vi89giu89";
        String to = recipient;
        String title = "";
        String textmsg = "";
        
        if(emailType.equals("registration")){
        	title = "Welcome!";
        	textmsg = "Benvenuto sul nostro portale "+newUser+"! Da oggi potrai prenotare i nostri servizi ed acquistare i nostri prodotti.";
        }
      
       
    //    String emp_id = "vincenzo";

    //    String host = "localhost";

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
