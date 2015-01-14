package it.unical.ilBelloDelleDonne.Spring.Controller;

import it.unical.ilBelloDelleDonne.ApplicationData.ApplicationInfo;
import it.unical.ilBelloDelleDonne.Hibernate.Dao.AccountDao;
import it.unical.ilBelloDelleDonne.Hibernate.Dao.UserDao;
import it.unical.ilBelloDelleDonne.Hibernate.Model.Account;
import it.unical.ilBelloDelleDonne.Hibernate.Model.User;
import it.unical.ilBelloDelleDonne.Hibernate.Utilities.CredentialsVerification;
import it.unical.ilBelloDelleDonne.Hibernate.Utilities.QueryFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.http.HttpSession;

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
public class AccountController implements ApplicationContextAware{
	
	private ApplicationContext applicationContext;
	
	@RequestMapping(value="/alterAccount", method=RequestMethod.GET)
	public String alterAccount(HttpSession session, Model model){
		ApplicationInfo appInfo = (ApplicationInfo) session.getAttribute("info");
		model.addAttribute("user", appInfo.getUser());
		
		return "alterAccount";
	}
	
	@RequestMapping(value="/insertAccount", method=RequestMethod.GET)
	public String insertAccount(HttpSession session, Model model){
		
		return "insertAccount";
	}
	
	@RequestMapping(value="/updateAlterUser",method=RequestMethod.POST)
	public String updateAlterUser(HttpSession session,
			@ModelAttribute("updUser")User userAlter,
			@RequestParam("birthString")String birth,
			RedirectAttributes redirect){
	
		try {
			userAlter.setBirth(new SimpleDateFormat("yyyy-mm-dd").parse(birth));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		ApplicationInfo appInfo = (ApplicationInfo) session.getAttribute("info");
		User user = appInfo.getUser();
		
		user.setName(userAlter.getName());
		user.setSurname(userAlter.getSurname());
		user.setBirth(userAlter.getBirth());
		user.setCity(userAlter.getCity());
		user.setEmail(user.getEmail());
		user.setStreetAddress(user.getStreetAddress());
		user.setTelephoneNumber(userAlter.getTelephoneNumber());
		
		UserDao userDao = (UserDao) applicationContext.getBean("userDao");
		userDao.update(user);
		
		redirect.addFlashAttribute("message","operazione eseguita con successo");
		
		return "redirect:/myAccount";
	}
	
	@RequestMapping(value="/updateAlterAccount", method=RequestMethod.POST)
	public String updateAlterAccount(@ModelAttribute("updAccount")Account account,
			HttpSession session,
			RedirectAttributes redirect){
		
		AccountDao accountDao = (AccountDao) applicationContext.getBean("accountDao");
		
		accountDao.update(account);
		
		ApplicationInfo appInfo = (ApplicationInfo) session.getAttribute("info");
		
		appInfo.getUser().setAccount(account);
		redirect.addFlashAttribute("message","operazione eseguita con successo");
		return "redirect:myAccount";
	}

	@RequestMapping(value="/insertNewAccount",method=RequestMethod.POST)
	public String insertNewAccount(RedirectAttributes redirect,
			HttpSession session,
			@ModelAttribute("insUser")User user,
			@RequestParam("birthString")String birth,
			@RequestParam("typeUs")String type){
		
		String username = CredentialsVerification.generateUsername(applicationContext,user.getName(),user.getSurname());
		
		Account account = new Account(username,type, type);
		
		try {
			user.setBirth(new SimpleDateFormat("yyyy-mm-dd").parse(birth));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		user.setAccount(account);
		
		account.setUser(user);
		
		
		AccountDao accountDao = (AccountDao) applicationContext.getBean("accountDao");
		accountDao.create(account);

		UserDao userDao = (UserDao) applicationContext.getBean("userDao");
	
		userDao.create(user);
		
		redirect.addFlashAttribute("message","account generato con successo: username="+account.getUsername()+" password="+account.getPassword());
			
		return "redirect:myAccount";
	}
	
	@RequestMapping(value="/showAccounts",method=RequestMethod.GET)
	public String showAccounts(Model model){
		List<User> userList = QueryFactory.create(applicationContext, "from User");
		model.addAttribute("userList",userList);
	
		return "showAccounts";
	}

	@RequestMapping(value="/showAccount",method=RequestMethod.POST)
	public String showAccount(Model model,
		@RequestParam("identifier") String ident, RedirectAttributes redirect){
			
			UserDao userDao = (UserDao) applicationContext.getBean("userDao");
			
			User user = userDao.retrieve(ident);
			model.addAttribute("user",user);
			return "showAccount";
	}
	
	@RequestMapping(value="/deleteAccounts",method=RequestMethod.GET)
	public String deleteAccounts(Model model){
		List<User> userList = QueryFactory.create(applicationContext, "from User");
		model.addAttribute("userList",userList);
		
		return "deleteAccounts";
	}
	
	@RequestMapping(value="/deleteAccount", method=RequestMethod.POST)
	public String deleteAccount(@RequestParam("identifier") String ident, RedirectAttributes redirect){
		
		UserDao userDao = (UserDao) applicationContext.getBean("userDao");
		
		User user = userDao.retrieve(ident);
		userDao.delete(user);
		
		String message="user eliminato correttamente dal sistema";
		redirect.addFlashAttribute("message",message);
		return "redirect:myAccount";
	}
	
	@Override
	public void setApplicationContext(ApplicationContext applicationContext)throws BeansException {
		this.applicationContext = applicationContext;
	}

}
