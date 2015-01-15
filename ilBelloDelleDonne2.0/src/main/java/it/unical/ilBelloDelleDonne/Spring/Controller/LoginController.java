package it.unical.ilBelloDelleDonne.Spring.Controller;

import it.unical.ilBelloDelleDonne.ApplicationData.ApplicationInfo;
import it.unical.ilBelloDelleDonne.Hibernate.Dao.ServiceDao;
import it.unical.ilBelloDelleDonne.Hibernate.Dao.UserDao;
import it.unical.ilBelloDelleDonne.Hibernate.Model.Service;
import it.unical.ilBelloDelleDonne.Hibernate.Model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
public class LoginController implements ApplicationContextAware{

	/*
	 * Controller che gestisce la fase di login
	 */
	private ApplicationContext applicationContext;

	@RequestMapping(value="/login", method=RequestMethod.GET )	
	public String getLogin(Model model){
		/*
		 * restituisce la view login
		 */
		System.out.println(model.asMap());
		return "login";
	}

	@RequestMapping(value="/login", method=RequestMethod.POST)
	public String postLogin(HttpSession session,Model model,
			HttpServletRequest request,RedirectAttributes redirect,
			@RequestParam("username") String username,
			@RequestParam("password") String password,
			@RequestParam(value="service" , required=false) String serviceId,
			@RequestParam(value= "after" , required=false)String after){
		
		UserDao userDao = (UserDao) applicationContext.getBean("userDao");
		User user = userDao.retrieve(username);
		
		if(user != null && user.getAccount().getPassword().equals(password)){//se l'utente esiste e la password combacia	
			
			ApplicationInfo appInfo = (ApplicationInfo) session.getAttribute("info");
			appInfo.setUser(user);
			
			if(after !=null){//se after non è null devo reindirizzarmi a qualche pagina.
				
				if(serviceId != null){//se after è /reserveService devo reindirizzarmi alla prenotazione di un servizio dunque devo prelevare il service e reindirizzarlo
					
					ServiceDao serviceDao = (ServiceDao) applicationContext.getBean("serviceDao");
					Service service = serviceDao.retrieve(Integer.valueOf(serviceId));
					
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

	@Override
	public void setApplicationContext( ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}
}