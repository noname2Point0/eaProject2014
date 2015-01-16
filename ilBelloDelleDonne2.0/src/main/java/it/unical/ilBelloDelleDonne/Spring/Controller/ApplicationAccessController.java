package it.unical.ilBelloDelleDonne.Spring.Controller;

import it.unical.ilBelloDelleDonne.ApplicationData.ApplicationInfo;
import it.unical.ilBelloDelleDonne.ApplicationData.DataProvider;
import it.unical.ilBelloDelleDonne.Hibernate.Dao.AccountDao;
import it.unical.ilBelloDelleDonne.Hibernate.Dao.UserDao;
import it.unical.ilBelloDelleDonne.Hibernate.Model.Account;
import it.unical.ilBelloDelleDonne.Hibernate.Model.Customer;
import it.unical.ilBelloDelleDonne.Hibernate.Model.Service;
import it.unical.ilBelloDelleDonne.Hibernate.Model.User;
import it.unical.ilBelloDelleDonne.Hibernate.Utilities.AccountType;
import java.text.SimpleDateFormat;
import java.util.Date;
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
public class ApplicationAccessController implements ApplicationContextAware {

	private ApplicationContext applicationContext;

	@RequestMapping(value="/login", method=RequestMethod.GET )	
	public String getLogin(Model model){
		
		return "login";
	}

	@RequestMapping(value="/login", method=RequestMethod.POST)
	public String postLogin(HttpSession session,Model model,
			HttpServletRequest request,RedirectAttributes redirect,
			@RequestParam("username") String username,
			@RequestParam("password") String password,
			@RequestParam(value="service" , required=false) String serviceId,
			@RequestParam(value= "after" , required=false)String after){
		
		User user = DataProvider.getUser(applicationContext, username);
		if(user != null && user.getAccount().getPassword().equals(password)){	
			
			ApplicationInfo appInfo = (ApplicationInfo) session.getAttribute("info");
			appInfo.setUser(user);
			
			if(after !=null){
				
				if(serviceId != null){
					
					Service service = DataProvider.getService(applicationContext, Integer.valueOf(serviceId));
					
					redirect.addFlashAttribute("service",service);
				}
				
				return"redirect:"+after;
			
			}else{
				return "redirect:myAccount";
			}
			
		}else{
			
			if(after != null)
				redirect.addFlashAttribute("after",after);
			
			if(serviceId!=null)
				redirect.addFlashAttribute("service",serviceId);
			
			redirect.addFlashAttribute("message","username or password incorrect!");
			return "redirect:/login";
		}
	}
	
	@RequestMapping(value="/logout", method=RequestMethod.GET)
	public String logout(HttpSession session){
		
			session.setAttribute("info",new ApplicationInfo());
			
			return "redirect:/home";
	}
	
	@RequestMapping(value="/signIn", method = RequestMethod.GET)
	public String getSignIn(Model model, 
			@RequestParam(value="after",required=false)String after,
			@RequestParam(value="service",required=false)String serviceId){
		
		if(after != null)
			model.addAttribute("after",after);
		
		if(serviceId != null)
			model.addAttribute("service",serviceId);
		
		return "signIn";
	}
	
	@RequestMapping(value="/signIn", method=RequestMethod.POST)
	public String postSignIn(HttpServletRequest request,
			Model model,
			@Valid @ModelAttribute("newUser")Customer user,
			BindingResult result,
			RedirectAttributes redirect){
		
		String after = request.getParameter("after");
		String serviceId = request.getParameter("service");
		
		if(result.hasErrors()){
			model.addAttribute("user",user);
			model.addAttribute("message","errore nel riempimento dei campi");
			
			if(after != null)
				model.addAttribute("after", after);
			
			if(serviceId != null)
				model.addAttribute("service",serviceId);
			
			return "signIn";
		}
		
		String username = (String)request.getParameter("username");	
		String password = (String)request.getParameter("password");
	
		AccountDao accountDao = (AccountDao) applicationContext.getBean("accountDao");
		Account account = accountDao.retrieve(username);
			
		if( account == null ){
			account = new Account();
			account.setUsername(username);
			account.setPassword(password);
			account.setUser(user);
			account.setType(AccountType.getCustomerType());
			accountDao.create(account);
			user.setAccount(account);
				
			UserDao userDao = (UserDao) applicationContext.getBean("userDao");
			userDao.create(user);
			
			redirect.addFlashAttribute("before",after);
			if(serviceId!=null)
				redirect.addFlashAttribute("service",serviceId);
			
			return "redirect:login";
				
		}else{
			model.addAttribute("user",user);
			model.addAttribute("message","Accont già esistente, scegli un altro username.");
			
			if(after != null)
				model.addAttribute("after", after);
			
			if(serviceId != null)
				model.addAttribute("service",serviceId);
			
			return "signIn";
		}
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
