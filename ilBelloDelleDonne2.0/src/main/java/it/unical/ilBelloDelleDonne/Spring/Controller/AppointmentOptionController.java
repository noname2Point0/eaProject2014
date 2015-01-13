
package it.unical.ilBelloDelleDonne.Spring.Controller;

import it.unical.ilBelloDelleDonne.ApplicationData.ApplicationInfo;
import it.unical.ilBelloDelleDonne.Hibernate.Model.Reserve;
import it.unical.ilBelloDelleDonne.Hibernate.Utilities.AccountType;
import it.unical.ilBelloDelleDonne.Hibernate.Utilities.CurrentData;
import it.unical.ilBelloDelleDonne.Hibernate.Utilities.QueryFactory;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class AppointmentOptionController implements ApplicationContextAware{

	private ApplicationContext applicationContext;

	@RequestMapping(value="/showAppointments",method=RequestMethod.GET)
	public String showAppointmenst(Model model, HttpSession session){
	
		ApplicationInfo appInfo = (ApplicationInfo) session.getAttribute("info");
		String accType = appInfo.getUser().getAccount().getType();
		
		List<Reserve> reserveList = null;
		if(AccountType.isAdmin(accType))
		reserveList = reserveAdmin();
		
		if(AccountType.isCustomer(accType))
		reserveList = reserveCustomer(appInfo.getUser().getAccount().getUsername());
			
		if(AccountType.isEmployeeSaloon(accType))
		reserveList = reserveEmployeeSaloon();	
			
		model.addAttribute("reserveList",reserveList);
		
		return "showAppointments";
	}
	
	private List<Reserve> reserveCustomer(String username){
		
		String query = new String("from Reserve r where r.customer.account.username='"+username+"' order by  r.dateService ASC");
		return (List<Reserve>)QueryFactory.create(applicationContext, query);
	}
	
	private List<Reserve> reserveEmployeeSaloon(){
		Date date = CurrentData.getLocaleData();
		
		String query = new String("from Reserve r where r.dateService="+date+" order by  r.dateService ASC");
		return (List<Reserve>)QueryFactory.create(applicationContext, query);
		
	}
	
	private List<Reserve> reserveAdmin(){
		String query = new String("from Reserve r order by  r.dateService ASC");
		return (List<Reserve>)QueryFactory.create(applicationContext, query);
		
	}
	
	 @RequestMapping(value="/checkOutAppointments",method=RequestMethod.GET)
	 public String checkOutAppointments(Model model){
		 	GregorianCalendar gc = new GregorianCalendar();
		 	
		 	Date date = CurrentData.getLocaleData();

		 	/**
		 	 * fai la query che prende tutte le reserve per cui billing è null
		 	 */
		 	
		 	String query = new String("from Reserve r where r.billing=billing");
			List<Reserve> reserveList = (List<Reserve>)QueryFactory.getReserveByParameter(applicationContext, query, null);
			
			model.addAttribute("reserveList",reserveList);
		
		 return "checkOutAppointments";
	 }
	
	@Override
	public void setApplicationContext(ApplicationContext applicationContext)throws BeansException {
		this.applicationContext = applicationContext;
	}
	
	
	
	
}
