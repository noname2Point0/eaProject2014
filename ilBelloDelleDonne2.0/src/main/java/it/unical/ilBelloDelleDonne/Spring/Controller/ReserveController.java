package it.unical.ilBelloDelleDonne.Spring.Controller;

import it.unical.ilBelloDelleDonne.ApplicationData.ApplicationInfo;
import it.unical.ilBelloDelleDonne.ApplicationData.DataProvider;
import it.unical.ilBelloDelleDonne.Hibernate.Dao.ReserveDao;
import it.unical.ilBelloDelleDonne.Hibernate.Dao.ServiceDao;
import it.unical.ilBelloDelleDonne.Hibernate.Model.Customer;
import it.unical.ilBelloDelleDonne.Hibernate.Model.Reserve;
import it.unical.ilBelloDelleDonne.Hibernate.Model.Service;
import it.unical.ilBelloDelleDonne.Hibernate.Utilities.AccountType;
import it.unical.ilBelloDelleDonne.Hibernate.Utilities.MyData;
import it.unical.ilBelloDelleDonne.Hibernate.Utilities.ReserveSchedule;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.BeansException;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
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

		if(!appInfo.isUserLogged()){
			appInfo.setService(service);
			redirect.addFlashAttribute("message","devi accedere al sistema prima di prenotare un servizio");
			return "redirect:/login";
		}
		
		model.addAttribute("user",appInfo.getUser());
		model.addAttribute("service",service);
		return"reserveService";
	}
	

	@RequestMapping(value="/reserveService", method=RequestMethod.GET)
	public String getReserve(Model model,HttpSession session){
		
		ApplicationInfo appInfo = (ApplicationInfo) session.getAttribute("info");
	
		Service service = appInfo.getService();
		appInfo.setService(null);
		
		model.addAttribute("user", appInfo.getUser());
		model.addAttribute("service",service);
		return "reserveService";
	}


	@RequestMapping(value="/confirmReserve", method=RequestMethod.POST)
	public String confirmReserve(HttpSession session, Model model, 
			@Valid @ModelAttribute("reserve")Reserve reserve,
			BindingResult result,
			@RequestParam("serviceId")int id,
			@RequestParam("dateService")String date,
			RedirectAttributes redirect){
		
		ServiceDao serviceDao = (ServiceDao) applicationContext.getBean("serviceDao");
		Service service = serviceDao.retrieve(id);
	
		ApplicationInfo appInfo = (ApplicationInfo) session.getAttribute("info");
		if(result.hasErrors()){
			model.addAttribute("service",service);
			model.addAttribute("user",appInfo.getUser());
			return "reserveService";
		}
		
		if(ReserveSchedule.isAnAvailableReserve(applicationContext,date, reserve.getTime().toString())){
			
			Customer customer = (Customer) DataProvider.getUser(applicationContext, appInfo.getUser().getAccount().getUsername());	
			reserve.setCustomer(customer);
			reserve.setDateOrder(MyData.getLocaleData());
			reserve.setService(service);
			
			ReserveDao reserveDao = (ReserveDao) applicationContext.getBean("reserveDao");
			reserveDao.create(reserve);
			redirect.addFlashAttribute("reserve",reserve);
			return "redirect:/reviewReserveSuccess";
		}
		else{
			String message = "non è possibile effettuare la prenotazione, scegli un orario o una data differente";
			model.addAttribute("service",service);
			model.addAttribute("message",message);
			model.addAttribute("user",appInfo.getUser());
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
	 
	 @InitBinder
	    public void initBinder(WebDataBinder binder) {
	        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	        dateFormat.setLenient(false);
	        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
	    }
	 
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;

	}

}