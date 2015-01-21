package it.unical.ilBelloDelleDonne.ApplicationData;

import it.unical.ilBelloDelleDonne.Hibernate.Model.Product;
import it.unical.ilBelloDelleDonne.Hibernate.Model.ProductStock;
import it.unical.ilBelloDelleDonne.Hibernate.Model.Selling;
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

	public static void sendSellingConfirmation(User user, Selling selling) throws IOException{
		String email = user.getEmail();
		String title = "Confirm Buying";
		String products = "";
		for(Product ps:selling.getProducts()){
			products = products+" "+ps.getProductStock().toString()+"\n";
		}
		
		String message = "Hai appena effettuato un acquisto! \n "
						+"Riepilogo acquisto: "+products;
		
		send(email,title,message);
	}
	
	public static void sendAdminRegistration(User newUser) throws IOException{
	        	String title="Welcome!";
	        	String textmsg = "Sei stato registrato nel nostro sistema. Le tue credenziali d'accesso sono: {username= "+newUser.getAccount().getUsername()
	        						+" password="+newUser.getAccount().getPassword()+"}.";
	        	String email = newUser.getEmail();
	        	
	        	send(email,title,textmsg);
	  
	}
	
	public static void sendRegistrationEmail(User newUser) throws IOException{

        	String title = "Welcome!";
        	String textmsg = "Benvenuto sul nostro portale "+newUser.getName()+"! Da oggi potrai prenotare i nostri servizi ed acquistare i nostri prodotti.";
        	String email = newUser.getEmail();
        	send(email,title,textmsg);
	}
	
	
	private static void send(String mail, String tit, String msg) throws IOException {

        final String user = "ilBelloDelleDonne.help@gmail.com";
        final String password = "vi89giu89";    
  
        String to = mail;
        String title = tit;
        String textmsg = msg;
    
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
