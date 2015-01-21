package it.unical.ilBelloDelleDonne.Spring.Controller;

import it.unical.ilBelloDelleDonne.ApplicationData.DataProvider;
import it.unical.ilBelloDelleDonne.Hibernate.Dao.BillingDao;
import it.unical.ilBelloDelleDonne.Hibernate.Dao.ReserveDao;
import it.unical.ilBelloDelleDonne.Hibernate.Dao.SellingDao;
import it.unical.ilBelloDelleDonne.Hibernate.Model.Billing;
import it.unical.ilBelloDelleDonne.Hibernate.Model.Reserve;
import it.unical.ilBelloDelleDonne.Hibernate.Model.Selling;
import it.unical.ilBelloDelleDonne.Hibernate.Utilities.MyData;

import java.util.Date;
import java.util.List;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
		
		return "redirect:checkOutReserve";
	}
	
	@RequestMapping(value="/billingSelling",method=RequestMethod.POST)
	public String billingSelling(Model model,@RequestParam("sellingId") int sellingId,
			RedirectAttributes redirect){
		

		Date date = MyData.getLocaleData();
		
		BillingDao billingDao = (BillingDao) applicationContext.getBean("billingDao");
		SellingDao sellingDao = (SellingDao) applicationContext.getBean("sellingDao");
		
		Selling checkSelling = sellingDao.retrieve(sellingId);
		
		Billing billing = new Billing(date,checkSelling);
		
		billingDao.create(billing);
		
		checkSelling.setBilling(billing);
		sellingDao.update(checkSelling);
		
		redirect.addFlashAttribute("message","la fatturazione è andata a buon fine");
		
		return "redirect:checkOutSelling";
	}
	
	@RequestMapping(value="/showBilling",method=RequestMethod.GET)
	public String getShowBilling(Model model){
		List<Billing> billingList = DataProvider.getBillingList(applicationContext);
		
		model.addAttribute("billingList",billingList);
		return "showBilling";
	}
	
	@Override
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
			this.applicationContext = applicationContext;
	}
}
