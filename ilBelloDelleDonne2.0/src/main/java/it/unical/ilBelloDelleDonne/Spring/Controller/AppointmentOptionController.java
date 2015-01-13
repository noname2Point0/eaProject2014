package it.unical.ilBelloDelleDonne.Spring.Controller;

import it.unical.ilBelloDelleDonne.Hibernate.Model.Reserve;
import it.unical.ilBelloDelleDonne.Hibernate.Utilities.CurrentData;
import it.unical.ilBelloDelleDonne.Hibernate.Utilities.QueryFactory;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

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
	public String showAppointmenst(Model model){
	
		String query = new String("from Reserve r order by  r.dateService ASC");
		List<Reserve> reserveList = (List<Reserve>)QueryFactory.create(applicationContext, query);
		
		model.addAttribute("reserveList",reserveList);
		
		return "showAppointments";
	}
	
	 @RequestMapping(value="/checkOutAppointments",method=RequestMethod.GET)
	 public String checkOutAppointments(Model model){
		 	GregorianCalendar gc = new GregorianCalendar();
		 	
		 	Date date = CurrentData.getLocaleData();

		 	/**
		 	 * fai la query che prende tutte le reserve per cui billing è null
		 	 */
		 	
		 	String query = new String();
			List<Reserve> reserveList = (List<Reserve>)QueryFactory.create(applicationContext, query);
			
			model.addAttribute("reserveList",reserveList);
		
		 return "checkOutAppointments";
	 }
	
	@Override
	public void setApplicationContext(ApplicationContext applicationContext)throws BeansException {
		this.applicationContext = applicationContext;
	}
	
	
	
	
}
