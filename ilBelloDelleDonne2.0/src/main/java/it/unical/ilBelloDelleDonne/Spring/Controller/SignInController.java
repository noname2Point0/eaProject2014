package it.unical.ilBelloDelleDonne.Spring.Controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import it.unical.ilBelloDelleDonne.Hibernate.Dao.AccountDao;
import it.unical.ilBelloDelleDonne.Hibernate.Dao.UserDao;
import it.unical.ilBelloDelleDonne.Hibernate.Model.Account;
import it.unical.ilBelloDelleDonne.Hibernate.Model.Customer;
import it.unical.ilBelloDelleDonne.Hibernate.Utilities.CredentialsVerification;
import it.unical.ilBelloDelleDonne.Hibernate.Utilities.QueryFactory;
import it.unical.ilBelloDelleDonne.Hibernate.Utilities.AccountType;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class SignInController implements ApplicationContextAware{

	private ApplicationContext applicationContext;

	@RequestMapping(value="/signIn", method = RequestMethod.GET)
	public String signIn(){
		System.out.println("SONO NEL METODO GET DI SIGNIN CONTROLLER");
		return "signIn";
	}

	@RequestMapping(value="/signIn", method=RequestMethod.POST)
	public String signInPost(Model model,
			@RequestParam("nome") String name,
			@RequestParam("cognome") String surname,
			@RequestParam("cittaR") String city,
			@RequestParam("viaR") String streetAddress,
			@RequestParam("recTelefono") String telephoneNumber,
			@RequestParam("dataNascita") Date birth,
			@RequestParam("pIva_cf") String pIva_cf,
			@RequestParam("email") String email,
			@RequestParam("usernameS") String username,
			@RequestParam("passwordS") String password,
			@RequestParam("passwordSR") String ripetiPassword,
			RedirectAttributes redirectToSignIn){

		String query = "from User user where user.account.username='"+username+"'";
		if(CredentialsVerification.isAnExistingUser(applicationContext, query)){
			
			String message = new String("Utente già esistente");
			redirectToSignIn.addFlashAttribute("message",message);
			return "redirect:/signIn";
		} 
		
		AccountDao accountDao = (AccountDao) applicationContext.getBean("accountDao");
		UserDao userDao = (UserDao) applicationContext.getBean("userDao");

		Account account = new Account(username, password, AccountType.getCustomerType());
		accountDao.create(account);
		System.out.println("creo un account");

		userDao.create(new Customer(name, surname, city, streetAddress, telephoneNumber, birth, pIva_cf, email, account));
		System.out.println("ho appena registrato un customer");

		return "redirect:/login";

	}

	@Override
	public void setApplicationContext( ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}
}