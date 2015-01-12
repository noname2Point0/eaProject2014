package it.unical.ilBelloDelleDonne.Spring.Controller;

import it.unical.ilBelloDelleDonne.ApplicationData.ApplicationInfo;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class AccountOptionController{
	
	@RequestMapping(value="/alterAccount", method=RequestMethod.GET)
	public String alterAccount(HttpSession session, Model model){
		System.out.println("ciao");
		
		ApplicationInfo appInfo = (ApplicationInfo) session.getAttribute("info");
		model.addAttribute("user", appInfo.getUser());
		
		return "alterAccount";
	}

}
