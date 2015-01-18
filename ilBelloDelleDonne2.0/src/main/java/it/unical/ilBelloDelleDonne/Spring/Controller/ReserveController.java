package it.unical.ilBelloDelleDonne.Spring.Controller;

import it.unical.ilBelloDelleDonne.ApplicationData.ApplicationInfo;
import it.unical.ilBelloDelleDonne.ApplicationData.DataProvider;
import it.unical.ilBelloDelleDonne.Hibernate.Dao.ReserveDao;
import it.unical.ilBelloDelleDonne.Hibernate.Model.Customer;
import it.unical.ilBelloDelleDonne.Hibernate.Model.Reserve;
import it.unical.ilBelloDelleDonne.Hibernate.Model.Service;
import it.unical.ilBelloDelleDonne.Hibernate.Utilities.AccountType;
import it.unical.ilBelloDelleDonne.Hibernate.Utilities.MyData;
import it.unical.ilBelloDelleDonne.Hibernate.Utilities.QueryFactory;
import it.unical.ilBelloDelleDonne.Hibernate.Utilities.ReserveSchedule;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

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

		ApplicationInfo appInfo = (ApplicationInfo) session.getAttribute("info");

		Date dateService = new Date();

		try{
			dateService = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH).parse(data);		
		}
		catch (Exception e) {
			e.addSuppressed(e.getCause());
		}

		if(ReserveSchedule.isAnAvailableReserve(applicationContext, data, time)){

			Reserve reserve = new Reserve();

			Date dateOrder = MyData.getLocaleData();

			ReserveDao reserveDao = (ReserveDao) applicationContext.getBean("reserveDao");

			Customer customer = (Customer) DataProvider.getUser(applicationContext, appInfo.getUser().getAccount().getUsername());

			reserve.setCustomer(customer);
			reserve.setDateOrder(dateOrder);
			reserve.setDateService(dateService);
			reserve.setTime(time);
			reserve.setService(service);

			reserveDao.create(reserve);

			redirect.addFlashAttribute("reserve",reserve);

			return "redirect:/reviewReserveSuccess";
		}
		else{

			String message = "non è possibile effettuare la prenotazione, scegli un altra data o orario";
			
			if(appInfo.isUserLogged()){
				model.addAttribute("user", appInfo.getUser());
				model.addAttribute("message",message);
			}

			return "reserveService";
		}

	}

	@RequestMapping(value="/reviewReserveSuccess",method=RequestMethod.GET)
	public String reviewReserve(Model model, HttpSession session){

		ApplicationInfo appInfo = (ApplicationInfo) session.getAttribute("info");
		if(appInfo.isUserLogged())
			model.addAttribute("user", appInfo.getUser());

		return "reviewReserveSuccess";
	}
	

	@RequestMapping(value="/showAppointments",method=RequestMethod.GET)
	public String showAppointmenst(Model model, HttpSession session){
	
		ApplicationInfo appInfo = (ApplicationInfo) session.getAttribute("info");
		String accType = appInfo.getUser().getAccount().getType();
		
		List<Reserve> reserveList = null;
		if(AccountType.isAdmin(accType)){
			reserveList = DataProvider.getReserveList(applicationContext);
		}
		
		if(AccountType.isCustomer(accType)){
			reserveList=DataProvider.getCustomerReserveList(applicationContext,appInfo.getUser().getAccount().getUsername());
		}
		
		if(AccountType.isEmployeeSaloon(accType)){
			reserveList=DataProvider.getEmployeeReserveList(applicationContext);
		}
		
		model.addAttribute("reserveList",reserveList);
		
		return "showAppointments";
	}
	
	 @RequestMapping(value="/checkOutAppointments",method=RequestMethod.GET)
	 public String checkOutAppointments(Model model){
			 	
		 	List<Reserve> reserveList = (List<Reserve>) DataProvider.getReserveListNoBilling(applicationContext); 	
			
			model.addAttribute("reserveList",reserveList);
		
		 return "checkOutAppointments";
	 }
	 
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;

	}

}