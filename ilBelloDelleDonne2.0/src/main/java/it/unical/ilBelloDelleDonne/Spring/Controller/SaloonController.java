package it.unical.ilBelloDelleDonne.Spring.Controller;

import it.unical.ilBelloDelleDonne.ApplicationData.ApplicationInfo;
import it.unical.ilBelloDelleDonne.ApplicationData.DataProvider;
import it.unical.ilBelloDelleDonne.ApplicationData.ServiceList;
import it.unical.ilBelloDelleDonne.Hibernate.Dao.ServiceDao;
import it.unical.ilBelloDelleDonne.Hibernate.Model.Service;
import it.unical.ilBelloDelleDonne.Hibernate.Utilities.QueryFactory;

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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
public class SaloonController implements ApplicationContextAware{

	private ApplicationContext applicationContext;

	@RequestMapping(value="/services",method=RequestMethod.GET)
	public String services(Model model, HttpSession session){

		ApplicationInfo appInfo = (ApplicationInfo) session.getAttribute("info");
		if(appInfo.isUserLogged())
			model.addAttribute("user", appInfo.getUser());
		
		ServiceList serviceList = new ServiceList();
		serviceList.setServices((List<Service>) QueryFactory.create(applicationContext, new String("from Service")));
		
		model.addAttribute("serviceList", serviceList);

		return "services";
	}
	
	
	
		
	@RequestMapping(value="/insertService",method=RequestMethod.GET)
	public String getInsertService(){
		return "insertService";
	}
	
	@RequestMapping(value="/showService",method=RequestMethod.GET)
	public String getShowService(Model model){
		
		List<Service> serviceList = DataProvider.getServiceList(applicationContext);
		model.addAttribute("serviceList",serviceList);
		return "showService";
		
	}
	
	@RequestMapping(value="/alterService",method=RequestMethod.POST)
	public String postAlterService(Model model){
		
		List<Service> serviceList = DataProvider.getServiceList(applicationContext);
		model.addAttribute("serviceList",serviceList);
		return "alterService";
	}
	
	
	@RequestMapping(value="/insertNewService",method=RequestMethod.POST)
	public String postInsertService(@ModelAttribute("insService") Service service, RedirectAttributes redirect){
		
		ServiceDao serviceDao = (ServiceDao) applicationContext.getBean("serviceDao");
		serviceDao.create(service);
		
		redirect.addFlashAttribute("message","servizio inserito con successo");
		
		return "redirect:myAccount";
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}
}
