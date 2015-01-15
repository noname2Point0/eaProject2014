package it.unical.ilBelloDelleDonne.Spring.Controller;

import it.unical.ilBelloDelleDonne.ApplicationData.ApplicationInfo;
import it.unical.ilBelloDelleDonne.Hibernate.Utilities.FillDBFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ApplicationController implements ApplicationContextAware{
	/*
	 * Controller che gestisce la navigazione delle pagine statice
	 */
	private boolean firstInvocation = true;
	private ApplicationContext applicationContext;
	
	
	@RequestMapping(value="/",method = RequestMethod.GET)
	public String getHome(HttpSession session,Model model){
		
		/*
		 * inserisce alcuni dati nel db;
		 * se non esiste crea un oggetto applicationInfo nella sessione
		 */
		
		if(firstInvocation){
			FillDBFactory.create(applicationContext);
			firstInvocation = false;
		}
		
		ApplicationInfo appInfo = (ApplicationInfo) session.getAttribute("info");
		
		if(appInfo == null){
			appInfo = new ApplicationInfo();
			session.setAttribute("info", appInfo);
		}else{
			if(appInfo.isUserLogged())
				model.addAttribute("user", appInfo.getUser());
		}
		
		return "home";
	}

	@RequestMapping(value="/home",method=RequestMethod.GET)
	public String getHome(Model model,HttpSession session){
		
		/*
		 * semplice get di home
		 */
		
		ApplicationInfo appInfo = (ApplicationInfo) session.getAttribute("info");
		if(appInfo.isUserLogged())
			model.addAttribute("user", appInfo.getUser());
	
		return "home";
	}
	
	@RequestMapping(value="/chiSiamo", method=RequestMethod.GET)
	public String getChiSiamo(Model model,HttpSession session){
		/*
		 * semplice get di chi siamo
		 */
		
		ApplicationInfo appInfo = (ApplicationInfo) session.getAttribute("info");
		if(appInfo.isUserLogged())
			model.addAttribute("user", appInfo.getUser());
	
		return "chiSiamo";
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}

}
