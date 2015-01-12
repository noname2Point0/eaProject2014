package it.unical.ilBelloDelleDonne.Spring.Controller;

import it.unical.ilBelloDelleDonne.ApplicationData.ApplicationInfo;
import it.unical.ilBelloDelleDonne.Hibernate.Dao.ReserveDao;
import it.unical.ilBelloDelleDonne.Hibernate.Model.Customer;
import it.unical.ilBelloDelleDonne.Hibernate.Model.Reserve;
import it.unical.ilBelloDelleDonne.Hibernate.Model.Service;
import it.unical.ilBelloDelleDonne.Hibernate.Utilities.QueryFactory;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class ReserveController implements ApplicationContextAware {

	
	private ApplicationContext applicationContext;
	
	@RequestMapping(value="/reserveService", method=RequestMethod.POST)
	public String reserve(Model model,HttpSession session, 
			@ModelAttribute("service") Service service, 
			RedirectAttributes redirect){
		
		ApplicationInfo appInfo = (ApplicationInfo) session.getAttribute("info");
		
		if(appInfo.isUserLogged()){
			model.addAttribute("user", appInfo.getUser());
			model.addAttribute("service",service);
			return"reserveService";
			
		}else{
			String message = new String("devi accedere al sistema prima di prenotare un servizio, riempi il seguente form oppure registrati");
			
			redirect.addFlashAttribute("before",new String("/reserveService"));
			redirect.addFlashAttribute("message",message);
			redirect.addFlashAttribute("service",service.getId());
			
			return "redirect:/login";
		}
	}
	
	@RequestMapping(value="/reserveService", method=RequestMethod.GET)
	public String getReserve(Model model,HttpSession session){
		ApplicationInfo appInfo = (ApplicationInfo) session.getAttribute("info");
		if(appInfo.isUserLogged())
			model.addAttribute("user", appInfo.getUser());
		
		return "reserveService";
	}
	
	
	@RequestMapping(value="/confirmReserve", method=RequestMethod.POST)
	public String confirmReserve(HttpSession session, Model model, 
			@RequestParam("data") String data,
			@RequestParam("time") String time,
			@ModelAttribute("service") Service service,
			RedirectAttributes redirect){
		
		Reserve reserve = new Reserve();
		
		ApplicationInfo appInfo = (ApplicationInfo) session.getAttribute("info");
		
		Date dateOrder = new Date();
		Date dateService = new Date();
		
		GregorianCalendar gc = new GregorianCalendar();
		dateOrder = gc.getTime();

		try{
			dateService = new SimpleDateFormat("yyyy-MM-dd").parse(data);			
		}
		catch (Exception e) {
			e.addSuppressed(e.getCause());
		}

		ReserveDao reserveDao = (ReserveDao) applicationContext.getBean("reserveDao");
		
		String query = new String("from Customer c where c.account.username='"+appInfo.getUser().getAccount().getUsername()+"'");
		
		Customer customer = QueryFactory.getCustomerByUser(applicationContext, query);
		
		reserve.setCustomer(customer);
		reserve.setDateOrder(dateOrder);
		reserve.setDateService(dateService);
		reserve.setService(service);
		
		reserveDao.create(reserve);
		
		redirect.addFlashAttribute("reserve",reserve);
		
		return "redirect:/reviewReserveSuccess";
}

	@RequestMapping(value="/reviewReserveSuccess",method=RequestMethod.GET)
	public String reviewReserve(Model model, HttpSession session){
		
		ApplicationInfo appInfo = (ApplicationInfo) session.getAttribute("info");
		if(appInfo.isUserLogged())
			model.addAttribute("user", appInfo.getUser());
		
		return "reviewReserveSuccess";
	}
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
		
	}

}