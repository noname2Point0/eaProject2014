package it.unical.ilBelloDelleDonne.Spring.Controller;

import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class HomeLoggedController {
		
	@RequestMapping(value="/homeLogged",method=RequestMethod.GET)
	public String getlogged(){
		return "homeLogged";
	}
	
	@RequestMapping(value="/homeLogged", method=RequestMethod.POST)
	public String postlogged(Model model,
			@RequestParam("username") String user,
			@RequestParam("userPass") String pass){
		
		/*
		if(UserStatic.existUser(user,pass)){

		model.addAttribute("name",UserStatic.NAME);
		model.addAttribute("surname",UserStatic.SURNAME);
		model.addAttribute("role",UserStatic.ROLE);
		
		logger.info("post logged");
			
			return "logged";
		}else{
			return "home";
		}
		*/
		
		return "homeLogged";
	}
	
	@RequestMapping(value="/logContent",method=RequestMethod.GET)
	public String logContent(Model model){
		return "logContent";
	}
	

	
}
