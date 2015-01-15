package it.unical.ilBelloDelleDonne.Spring.Controller;

import java.util.Date;

import it.unical.ilBelloDelleDonne.Hibernate.Dao.BillingDao;
import it.unical.ilBelloDelleDonne.Hibernate.Dao.ReserveDao;
import it.unical.ilBelloDelleDonne.Hibernate.Model.Billing;
import it.unical.ilBelloDelleDonne.Hibernate.Model.Reserve;
import it.unical.ilBelloDelleDonne.Hibernate.Utilities.MyData;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class BillingController implements ApplicationContextAware{

	private ApplicationContext applicationContext;
	
	@RequestMapping(value="/billingReserve",method=RequestMethod.POST)
	public String billingReserve( @RequestParam("reserveId")int idReserve,
			RedirectAttributes redirect){
		
		Date date = MyData.getLocaleData();
		
		BillingDao billingDao = (BillingDao) applicationContext.getBean("billingDao");
		ReserveDao reserveDao = (ReserveDao) applicationContext.getBean("reserveDao");
		
		Reserve checkReserve = reserveDao.retrieve(idReserve);
		
		Billing billing = new Billing(date,checkReserve);
		
		billingDao.create(billing);
		
		checkReserve.setBilling(billing);
		reserveDao.update(checkReserve);
		
		redirect.addFlashAttribute("message","la prenotazione è stata fatturata dal sistema");
		
		return "redirect:myAccount";
	}
	
	@RequestMapping(value="/billingSelling",method=RequestMethod.POST)
	public String billingSelling(){
		
		return "redirect:myAccount";
	}
	
	@Override
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
			this.applicationContext = applicationContext;
	}
}
