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
	private boolean firstInvocation = true;
	private ApplicationContext applicationContext;
	
	@RequestMapping(value="/",method = RequestMethod.GET)
	public String getHome(HttpSession session){
		
		if(firstInvocation){
			FillDBFactory.create(applicationContext);
			firstInvocation = false;
		}
		
		ApplicationInfo appInfo = (ApplicationInfo) session.getAttribute("info");
		
		if(appInfo == null){
			appInfo = new ApplicationInfo();
			session.setAttribute("info", appInfo);
		}
		
		return "home";
	}

	@RequestMapping(value="/home",method=RequestMethod.GET)
	public String getHome(){
		
		return "homeTemplate";
	}
	
	@RequestMapping(value="/chiSiamo", method=RequestMethod.GET)
	public String getChiSiamo(){
		return "chiSiamoTemplate";
	}

	@RequestMapping(value="/navigationBar",method=RequestMethod.POST)
	public String getNavigationBar(Model model,HttpSession session){
		
		ApplicationInfo appInfo = (ApplicationInfo) session.getAttribute("info");
		if(appInfo.isUserLogged())
			model.addAttribute("user", appInfo.getUser());

		return "navigationBar";
	}
	
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}

}
