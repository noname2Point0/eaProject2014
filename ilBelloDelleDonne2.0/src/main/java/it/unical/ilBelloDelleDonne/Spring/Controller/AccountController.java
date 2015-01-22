package it.unical.ilBelloDelleDonne.Spring.Controller;

import it.unical.ilBelloDelleDonne.ApplicationData.ApplicationInfo;
import it.unical.ilBelloDelleDonne.ApplicationData.DataProvider;
import it.unical.ilBelloDelleDonne.ApplicationData.EmailType;
import it.unical.ilBelloDelleDonne.ApplicationData.SendEmail;
import it.unical.ilBelloDelleDonne.Hibernate.Dao.AccountDao;
import it.unical.ilBelloDelleDonne.Hibernate.Dao.UserDao;
import it.unical.ilBelloDelleDonne.Hibernate.Model.Account;
import it.unical.ilBelloDelleDonne.Hibernate.Model.Admin;
import it.unical.ilBelloDelleDonne.Hibernate.Model.Employee;
import it.unical.ilBelloDelleDonne.Hibernate.Model.User;
import it.unical.ilBelloDelleDonne.Hibernate.Utilities.AccountType;
import it.unical.ilBelloDelleDonne.Hibernate.Utilities.CredentialsManager;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.BeansException;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
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

		User user = DataProvider.getUser(applicationContext, appInfo.getUser().getAccount().getUsername());

		user.getAccount().setPassword("");

		String date = user.getBirth().toString().substring(0,10);

		model.addAttribute("user",user);
		model.addAttribute("userBirth",date);

		return "alterAccount";
	}

	@RequestMapping(value="/insertAccount", method=RequestMethod.GET)
	public String insertAccount(HttpSession session, HttpServletRequest request, Model model){

		return "insertAccount";
	}

	@RequestMapping(value="/updateAlterUser",method=RequestMethod.POST)
	public String updateAlterUser(Model model,
			HttpSession session,
			@Valid @ModelAttribute("updUser")User userAlter,
			BindingResult result,
			RedirectAttributes redirect){
		
		if(result.hasErrors()){
			return alterAccount(session, model);
		}
		
		ApplicationInfo appInfo = (ApplicationInfo) session.getAttribute("info");

		UserDao userDao = (UserDao) applicationContext.getBean("userDao");
		User user = userDao.retrieve(appInfo.getUser().getAccount().getUsername());

		user.setName(userAlter.getName());
		user.setSurname(userAlter.getSurname());
		user.setBirth(userAlter.getBirth());
		user.setCity(userAlter.getCity());
		user.setEmail(userAlter.getEmail());
		user.setStreetAddress(userAlter.getStreetAddress());
		user.setTelephoneNumber(userAlter.getTelephoneNumber());

		userDao.update(user);

		appInfo.setUser(user);
		redirect.addFlashAttribute("message","operazione eseguita con successo");

		model.addAttribute("user",user);
		String date = user.getBirth().toString().substring(0,10);
		model.addAttribute("userBirth",date);
		return "showAccountDetails";
	}

	@RequestMapping(value="/updateAlterAccount", method=RequestMethod.POST)
	public String updateAlterAccount(Model model,
			@RequestParam("currentPassword")String currPass,
			@Valid @ModelAttribute("updAccount")Account acc,
			BindingResult result,
			HttpSession session,
			RedirectAttributes redirect){

		ApplicationInfo appInfo = (ApplicationInfo) session.getAttribute("info");
		User user = DataProvider.getUser(applicationContext, appInfo.getUser().getAccount().getUsername());
		
		if(result.hasErrors()){
			user.getAccount().setPassword("");
			String date = user.getBirth().toString().substring(0,10);
			model.addAttribute("user",user);
			model.addAttribute("userBirth",date);
			
			return "alterAccount";
		}
		
		
		if(user.getAccount().getPassword().equals(currPass)){
			AccountDao accountDao = (AccountDao) applicationContext.getBean("accountDao");
			UserDao userDao =(UserDao) applicationContext.getBean("userDao");
			
			accountDao.update(acc);
			user.setAccount(acc);
			userDao.update(user);
			
			redirect.addFlashAttribute("message","operazione eseguita con successo");
		}else{
			redirect.addFlashAttribute("message", "spiacente, non è stato possibile cambiare la tua password, il valore inserito della password corrente non combacia");
		}

		model.addAttribute("user",user);
		return "showAccountDetails";
	}

	@RequestMapping(value="/insertNewAccount",method=RequestMethod.POST)
	public String insertNewAccount(Model model,
			RedirectAttributes redirect,
			HttpSession session,
			@Valid @ModelAttribute("insUser")User user,
			BindingResult result,
			@RequestParam("typeUs")String type){

		if(result.hasErrors()){
			model.addAttribute("user",user);
			return "insertAccount";
		}
		
		String username = CredentialsManager.generateUsername(applicationContext, user.getName(), user.getSurname());
		String pass = CredentialsManager.generatePassword(applicationContext, type);
		
		UserDao userDao = (UserDao) applicationContext.getBean("userDao");
		AccountDao accountDao = (AccountDao) applicationContext.getBean("accountDao");
		
		Account account = new Account(username,pass,type);
		accountDao.create(account);
		
		if(type.equals(AccountType.getAdminType())){
			Admin admin = new Admin();
			admin.copy(user);

			account.setUser(admin);
			admin.setAccount(account);
			userDao.create(admin);
		}else{
			Employee employee = new Employee();
			employee.copy(user);
			account.setUser(employee);
			employee.setAccount(account);
		
			if(type.equals(AccountType.getEmployeeSaloonType()))
				employee.setDepartment("saloon");
			else
				employee.setDepartment("warehouse");
			
			userDao.create(employee);
			
		}
		
		model.addAttribute("message","utente inserito con successo username e password saranno inviati al diretto interessato tramite mail specifica");
		model.addAttribute("user",user);
		user.setAccount(account);
		
		try{
			SendEmail.sendAdminRegistration(user);
		}catch(Exception e){
			
			//da gestire
		}
		
		return "showAccountDetails";
	}

	@RequestMapping(value="/showAccounts",method=RequestMethod.GET)
	public String showAccounts(Model model){

		List<User> userList = DataProvider.getUsers(applicationContext);

		model.addAttribute("userList",userList);
		return "showAccounts";
	}

	@RequestMapping(value="/accountDetails",method=RequestMethod.POST)
	public String getAccountDetails(Model model,@RequestParam("username") String username){
		User user = DataProvider.getUser(applicationContext,username);
		model.addAttribute("user",user);
		
		return "showAccountDetails";
	}
	@RequestMapping(value="/deleteAccounts",method=RequestMethod.GET)
	public String deleteAccounts(Model model){

		List<User> userList = DataProvider.getUsers(applicationContext);

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
		return "redirect:showAccounts";
	}

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext)throws BeansException {
		this.applicationContext = applicationContext;
	}

}
