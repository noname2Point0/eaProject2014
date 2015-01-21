package it.unical.ilBelloDelleDonne.Spring.Controller;

import it.unical.ilBelloDelleDonne.ApplicationData.ApplicationInfo;
import it.unical.ilBelloDelleDonne.ApplicationData.DataProvider;
import it.unical.ilBelloDelleDonne.Hibernate.Dao.AccountDao;
import it.unical.ilBelloDelleDonne.Hibernate.Dao.UserDao;
import it.unical.ilBelloDelleDonne.Hibernate.Model.Account;
import it.unical.ilBelloDelleDonne.Hibernate.Model.Customer;
import it.unical.ilBelloDelleDonne.Hibernate.Model.User;
import it.unical.ilBelloDelleDonne.Hibernate.Utilities.AccountType;

import java.text.SimpleDateFormat;
import java.util.Date;

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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class ApplicationAccessController implements ApplicationContextAware {

	private ApplicationContext applicationContext;

	@RequestMapping(value="/login", method=RequestMethod.GET )	
	public String getLogin(){
		return "login";
	}

	@RequestMapping(value="/login", method=RequestMethod.POST)
	public String postLogin(HttpSession session,
		Model model,
		@Valid @ModelAttribute("logAccount")Account logAccount,
		BindingResult result){
		
		if(result.hasErrors()){
			return "login";
		}else{
			User user = DataProvider.getUser(applicationContext,logAccount.getUsername());
			if(user != null && user.getAccount().getPassword().equals(logAccount.getPassword())){
				user.getAccount().setPassword("");
				ApplicationInfo appInfo = (ApplicationInfo) session.getAttribute("info");
				appInfo.setUser(user);
				
				if(appInfo.getSelling())
					return "redirect:sellingProducts";
				
				if(appInfo.getService() != null)
					return "redirect:reserveService";
				
				return "redirect:myAccount";
			}else{
				model.addAttribute("message","username or password incorrect");
				return "login";
			}
		}
	}
	
	@RequestMapping(value="/logout", method=RequestMethod.GET)
	public String logout(HttpSession session){
			session.setAttribute("info",new ApplicationInfo());
			return "redirect:/";
	}
	
	@RequestMapping(value="/signIn", method = RequestMethod.GET)
	public String getSignIn(){
		return "signIn";
	}
	
	@RequestMapping(value="/signIn", method=RequestMethod.POST)
	public String postSignIn(Model model,
			HttpSession session,
			@Valid @ModelAttribute("SignInUser")Customer user,
			BindingResult result,
			RedirectAttributes redirect){
		
		if(result.hasErrors()){
			model.addAttribute("user",user);
			return "signIn";
		}
		
		if(user.getAccount().getUsername().length() <5 || user.getAccount().getUsername().length()>10){
			
			model.addAttribute("message", "username size must be between 5 and 10");
			model.addAttribute("user",user);
			return "signIn";
		}
		
		if(user.getAccount().getUsername().length()<5 || user.getAccount().getUsername().length()>10){
			model.addAttribute("message", "password size must be between 5 and 10");
			model.addAttribute("user",user);
			return "signIn";
		}
		
		AccountDao accountDao = (AccountDao) applicationContext.getBean("accountDao");
		Account account = accountDao.retrieve(user.getAccount().getUsername());
		
		if(account != null){
			model.addAttribute("message","username alredy exists");
			model.addAttribute("user",user);
			return "signIn";
		}else{
			account = new Account(user.getAccount().getUsername(), user.getAccount().getPassword(), AccountType.getCustomerType());
			account.setUser(user);
			accountDao.create(account);
			
			user.setAccount(account);	
			
			UserDao userDao = (UserDao) applicationContext.getBean("userDao");
			userDao.create(user);
		}
		
		
		
		ApplicationInfo appInfo = (ApplicationInfo) session.getAttribute("info");
		appInfo.setUser(user);
		
		return "login";
	}

	@InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
    }

	@Override
	public void setApplicationContext( ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}
}
