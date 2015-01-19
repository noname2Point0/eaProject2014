package it.unical.ilBelloDelleDonne.Spring.Controller;

import it.unical.ilBelloDelleDonne.ApplicationData.ApplicationInfo;
import it.unical.ilBelloDelleDonne.ApplicationData.DataProvider;
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


import org.springframework.beans.BeansException;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
	public String updateAlterUser(HttpSession session,
			@ModelAttribute("updUser")User userAlter,
			RedirectAttributes redirect){

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

		return "redirect:/myAccount";
	}

	@RequestMapping(value="/updateAlterAccount", method=RequestMethod.POST)
	public String updateAlterAccount(@RequestParam("password")String password,
			@RequestParam("currentPassword") String curPass,
			HttpSession session,
			RedirectAttributes redirect){


		ApplicationInfo appInfo = (ApplicationInfo) session.getAttribute("info");

		AccountDao accountDao = (AccountDao) applicationContext.getBean("accountDao");
		Account acc = accountDao.retrieve(appInfo.getUser().getAccount().getUsername());

		if(acc.getPassword().equals(curPass)){
			acc.setPassword(password);
			accountDao.update(acc);

			appInfo.getUser().setAccount(acc);

			redirect.addFlashAttribute("message","operazione eseguita con successo");
		}else{
			redirect.addFlashAttribute("message", "spiacente, non è stato possibile cambiare la tua password, il valore inserito della password corrente non combacia");
		}

		return "redirect:myAccount";
	}

	@RequestMapping(value="/insertNewAccount",method=RequestMethod.POST)
	public String insertNewAccount(Model model,
			RedirectAttributes redirect,
			HttpSession session,
			@ModelAttribute("insUser")User user,
			@RequestParam("typeUs")String type){

		String username = CredentialsManager.generateUsername(applicationContext, user.getName(), user.getSurname());
		String pass = CredentialsManager.generatePassword(applicationContext, type);

		UserDao userDao = (UserDao) applicationContext.getBean("userDao");
		AccountDao accountDao = (AccountDao) applicationContext.getBean("accountDao");

		Account account = new Account(username,pass,type);
		accountDao.create(account);

		if(type.equals(AccountType.getAdminType())){
			Admin admin = new Admin();
			admin.copy(user);

			admin.setAccount(account);
			userDao.create(admin);

		}else{
			Employee employee = new Employee();
			employee.copy(user);

			if(type.equals(AccountType.getEmployeeSaloonType()))
				employee.setDepartment("saloon");
			else
				employee.setDepartment("warehouse");

			employee.setAccount(account);
			userDao.create(employee);
		}

		redirect.addFlashAttribute("message","utente inserito con successo USERNAME: "+username+" PASSWORD: "+pass+"");

		return "redirect:myAccount";
	}

	@RequestMapping(value="/showAccounts",method=RequestMethod.GET)
	public String showAccounts(Model model){

		List<User> userList = DataProvider.getUsers(applicationContext);

		model.addAttribute("userList",userList);
		return "showAccounts";
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
		return "redirect:myAccount";
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
