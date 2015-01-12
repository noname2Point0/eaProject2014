package it.unical.ilBelloDelleDonne.Spring.Controller;

import it.unical.ilBelloDelleDonne.Hibernate.Dao.AccountDao;
import it.unical.ilBelloDelleDonne.Hibernate.Dao.UserDao;
import it.unical.ilBelloDelleDonne.Hibernate.Model.Account;
import it.unical.ilBelloDelleDonne.Hibernate.Model.Customer;
import it.unical.ilBelloDelleDonne.Hibernate.Utilities.AccountType;
import it.unical.ilBelloDelleDonne.Hibernate.Utilities.CredentialsVerification;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class SignInController implements ApplicationContextAware{

	private ApplicationContext applicationContext;

	@RequestMapping(value="/signIn", method = RequestMethod.GET)
	public String signIn(){
		return "signIn";
	}

	@RequestMapping(value="/signIn", method=RequestMethod.POST)
	public String signInPost(Model model,
			@RequestParam("nome") String name,
			@RequestParam("cognome") String surname,
			@RequestParam("cittaR") String city,
			@RequestParam("viaR") String streetAddress,
			@RequestParam("recTelefono") String telephoneNumber,
			@RequestParam("dataNascita") String birth,
			@RequestParam("pIva_cf") String pIva_cf,
			@RequestParam("email") String email,
			@RequestParam("usernameS") String username,
			@RequestParam("passwordS") String password,
			RedirectAttributes redirectToSignIn){
		
			Date dateB = new Date();
			
			try {
				dateB= new SimpleDateFormat("yyyy-MM-dd").parse(birth);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
			
			System.out.println(dateB); // Sat Jan 02 00:00:00 GMT 2010
			
			

		if(CredentialsVerification.isAnExistingUser(applicationContext, username)){
			
			String message = new String("Utente già esistente");
			redirectToSignIn.addFlashAttribute("message",message);
			return "redirect:/signIn";
		} 
		
		AccountDao accountDao = (AccountDao) applicationContext.getBean("accountDao");
		UserDao userDao = (UserDao) applicationContext.getBean("userDao");

		Account account = new Account(username, password, AccountType.getCustomerType());
		accountDao.create(account);
		
		userDao.create(new Customer(name, surname, city, streetAddress, telephoneNumber, dateB, pIva_cf, email, account));
		
		return "redirect:/login";

	}

	@Override
	public void setApplicationContext( ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}
}