package it.unical.ilBelloDelleDonne.Spring.Controller;

import it.unical.ilBelloDelleDonne.ApplicationData.ApplicationInfo;
import it.unical.ilBelloDelleDonne.Hibernate.Dao.AccountDao;
import it.unical.ilBelloDelleDonne.Hibernate.Model.Account;
import it.unical.ilBelloDelleDonne.Hibernate.Model.User;

import javax.servlet.http.HttpSession;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AccountOptionController implements ApplicationContextAware{
	
	private ApplicationContext applicationContext;
	
	@RequestMapping(value="/alterAccount", method=RequestMethod.GET)
	public String alterAccount(HttpSession session, Model model){
		System.out.println("ciao");
		
		ApplicationInfo appInfo = (ApplicationInfo) session.getAttribute("info");
		model.addAttribute("user", appInfo.getUser());
		
		return "alterAccount";
	}
	
	@RequestMapping(value="/updateAlterUser",method=RequestMethod.POST)
	public String updateAlterUser(@ModelAttribute("updUser") User user,@RequestParam("data")String  data){
		System.out.println(user.getName());
		
		return "redirect:myAccount";
	}
	
	@RequestMapping(value="/updateAlterAccount", method=RequestMethod.POST)
	public String updateAlterAccount(@ModelAttribute("updAccount")Account account,HttpSession session){
		System.out.println(account.getUsername());
		System.out.println(account.getPassword());
		System.out.println(account.getType());
		
		AccountDao accountDao = (AccountDao) applicationContext.getBean("accountDao");
		
		accountDao.update(account);
		
		ApplicationInfo appInfo = (ApplicationInfo) session.getAttribute("info");
		
		appInfo.getUser().setAccount(account);
		
		return "redirect:myAccount";
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext)throws BeansException {
		this.applicationContext = applicationContext;
	}

}
