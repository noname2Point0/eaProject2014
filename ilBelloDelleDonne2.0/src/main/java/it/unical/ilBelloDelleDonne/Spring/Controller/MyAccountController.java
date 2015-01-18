package it.unical.ilBelloDelleDonne.Spring.Controller;

import it.unical.ilBelloDelleDonne.ApplicationData.ApplicationInfo;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MyAccountController {

	@RequestMapping(value="/myAccount",method=RequestMethod.GET)
	public String myAccount(Model model,HttpSession session){
		
		ApplicationInfo appInfo = (ApplicationInfo) session.getAttribute("info");
		System.out.println(model.asMap());
		if(appInfo.isUserLogged())
			model.addAttribute("user", appInfo.getUser());
		
		return "myAccount";
	}
}
