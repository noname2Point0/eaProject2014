package it.unical.ilBelloDelleDonne.Spring.Controller;

import it.unical.ilBelloDelleDonne.ApplicationData.ApplicationInfo;
import it.unical.ilBelloDelleDonne.ApplicationData.DataProvider;
import it.unical.ilBelloDelleDonne.Hibernate.Dao.ServiceDao;
import it.unical.ilBelloDelleDonne.Hibernate.Model.Service;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
public class SaloonController implements ApplicationContextAware{

	private ApplicationContext applicationContext;

	@RequestMapping(value="/servicesList",method=RequestMethod.GET)
	public String services(Model model, HttpSession session){

		ApplicationInfo appInfo = (ApplicationInfo) session.getAttribute("info");
		if(appInfo.isUserLogged())
			model.addAttribute("user", appInfo.getUser());
		
		List<Service> serviceList = DataProvider.getServiceList(applicationContext);
		
		model.addAttribute("serviceList", serviceList);

		return "servicesList";
	}
	
	
	
		
	@RequestMapping(value="/insertService",method=RequestMethod.GET)
	public String getInsertService(){
		return "insertService";
	}
	
	@RequestMapping(value="/showService",method=RequestMethod.GET)
	public String getShowService(Model model, HttpSession session){
		
		ApplicationInfo appInfo = (ApplicationInfo) session.getAttribute("info");
		if(appInfo.isUserLogged())
			model.addAttribute("user", appInfo.getUser());

		
		List<Service> serviceList = DataProvider.getServiceList(applicationContext);
		model.addAttribute("serviceList",serviceList);
		return "servicesList";
		
	}
	
	@RequestMapping(value="/alterService",method=RequestMethod.GET)
	public String postAlterService(Model model){
		List<Service> serviceList = DataProvider.getServiceList(applicationContext);
		model.addAttribute("serviceList",serviceList);
		return "alterService";
	}
	
	@RequestMapping(value="/setAlterService",method=RequestMethod.GET)
	public String getSetAlterService(@ModelAttribute("altService")Service service,
			Model model){
		model.addAttribute("service",service);
		return "setAlterService";
	}
	
	@RequestMapping(value="/setAlterService",method=RequestMethod.POST)
	public String postSetAlterService(@Valid @ModelAttribute("altService") Service service,
			BindingResult result,
			Model model,
			RedirectAttributes redirect){
		
		ServiceDao serviceDao = (ServiceDao) applicationContext.getBean("serviceDao");
		if(result.hasErrors()){
			model.addAttribute("service",serviceDao.retrieve(service.getId()));
			return "setAlterService";
		}
		
		Service s = serviceDao.retrieve(service.getId());
		
		s.setDescription(service.getDescription());
		s.setPrice(service.getPrice());
		
		serviceDao.update(s);
		
		redirect.addFlashAttribute("message","servizio modificato correttamente");
		return "redirect:servicesList";
	}
	
	@RequestMapping(value="/deleteService",method=RequestMethod.GET)
	public String getRemoveService(Model model){
		List<Service> serviceList = DataProvider.getServiceList(applicationContext);
		
		model.addAttribute("serviceList", serviceList);
		
		return "deleteService";
	}
	
	@RequestMapping(value="/deleteService",method=RequestMethod.POST)
	public String postRemoveService(@ModelAttribute("removeService") Service service,
			RedirectAttributes redirect){
		
		ServiceDao serviceDao = (ServiceDao)applicationContext.getBean("serviceDao");
		serviceDao.delete(service);
		
		redirect.addFlashAttribute("message","servizio eliminato correttamente");
		return "redirect:servicesList";
		
	}
	
	@RequestMapping(value="/insertNewService",method=RequestMethod.POST)
	public String postInsertService(@ModelAttribute("insService") Service service, RedirectAttributes redirect){
		
		ServiceDao serviceDao = (ServiceDao) applicationContext.getBean("serviceDao");
		serviceDao.create(service);
		
		redirect.addFlashAttribute("message","servizio inserito con successo");
		
		return "redirect:servicesList";
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}
}
