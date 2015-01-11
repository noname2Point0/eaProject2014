package it.unical.ilBelloDelleDonne.Spring.Controller;

import it.unical.ilBelloDelleDonne.ApplicationData.ApplicationInfo;
import it.unical.ilBelloDelleDonne.Hibernate.Dao.ServiceDao;
import it.unical.ilBelloDelleDonne.Hibernate.Model.Service;

import it.unical.ilBelloDelleDonne.Hibernate.Model.User;
import it.unical.ilBelloDelleDonne.Hibernate.Utilities.CredentialsVerification;

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

	private ApplicationContext applicationContext;

	@RequestMapping(value="/login", method=RequestMethod.GET )
	
	public String login(Model model){
		return "login";
	}

	@RequestMapping(value="/login", method=RequestMethod.POST)
	public String loginPost(HttpSession session,Model model,
			HttpServletRequest request,RedirectAttributes redirect,
			@RequestParam("username") String username,
			@RequestParam("password") String password){
		
		User user = new User();
		boolean verification = CredentialsVerification.loginVerification(applicationContext,user,username,password);
	
		if(verification){
			
			System.out.println("user exist upda in session");
			System.out.println(user.getName());
			
			ApplicationInfo appInfo = (ApplicationInfo) session.getAttribute("info");
			appInfo.setUser(user);
			
			String after = request.getParameter("after");
			
			
			if(after != null){
				if(after.equals(new String("/reserveService"))){
				
				int id = Integer.valueOf(request.getParameter("service"));
				
				ServiceDao serviceDao = (ServiceDao) applicationContext.getBean("serviceDao");
				Service service = serviceDao.retrieve(id);
				
				System.out.println(service.getDescription());
				redirect.addFlashAttribute("service",service);
				
				}
				return "redirect:"+after;
			
			}
			else{
				return "redirect:/myAccount";
			}
			
		}else{
			System.out.println("error");
			redirect.addFlashAttribute("message","username or password incorrect");
			return "redirect:/login";
		}
	}

	@Override
	public void setApplicationContext( ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}
}