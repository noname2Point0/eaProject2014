package it.unical.ilBelloDelleDonne.Spring.Controller;

import it.unical.ilBelloDelleDonne.ApplicationData.ApplicationInfo;
import it.unical.ilBelloDelleDonne.ApplicationData.ProductCustom;
import it.unical.ilBelloDelleDonne.ApplicationData.ProductCustomList;
import it.unical.ilBelloDelleDonne.Hibernate.Dao.ProductDao;
import it.unical.ilBelloDelleDonne.Hibernate.Dao.SellingDao;
import it.unical.ilBelloDelleDonne.Hibernate.Model.Customer;
import it.unical.ilBelloDelleDonne.Hibernate.Model.Product;
import it.unical.ilBelloDelleDonne.Hibernate.Model.Reserve;
import it.unical.ilBelloDelleDonne.Hibernate.Model.Selling;
import it.unical.ilBelloDelleDonne.Hibernate.Utilities.CurrentData;
import it.unical.ilBelloDelleDonne.Hibernate.Utilities.QueryFactory;

import java.util.ArrayList;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class SellingController implements ApplicationContextAware{

	private ApplicationContext applicationContext;


	@RequestMapping(value="/sellingProducts",method=RequestMethod.GET)
	public String orderProducts(HttpSession session, Model model,RedirectAttributes redirect){

		ApplicationInfo appInfo = (ApplicationInfo) session.getAttribute("info");

		if(appInfo.isUserLogged()){

			ProductCustomList productCustomList = new ProductCustomList();
			productCustomList.setProductsCustom(appInfo.getShoppingCart().getProductsIn());

			model.addAttribute("productCustomList",productCustomList);

			model.addAttribute("user", appInfo.getUser());
			return "sellingProducts";
		}else{

			String message = new String("devi accedere al sistema prima di poter effettuare l'acquisto, riempi il seguente form oppure registrati");

			redirect.addFlashAttribute("before",new String("/sellingProducts"));
			redirect.addFlashAttribute("message",message);

			return "redirect:/login";

		}

	}

	@RequestMapping(value="/confirmSelling",method=RequestMethod.GET)
	public String orderProductsPost(
			@ModelAttribute("productsCustomList") ProductCustomList productsCustomList,
			HttpSession session,
			RedirectAttributes redirect){


		ApplicationInfo appInfo = (ApplicationInfo) session.getAttribute("info");

		List<ProductCustom> productCustom = productsCustomList.getProductsCustom();

		ArrayList<Product> products = new ArrayList<Product>();

		for(int i=0; i < productCustom.size(); i++){

			String query = new String("from Product p where p.description='"+productCustom.get(i).getDescription()+"' and p.brand='"+productCustom.get(i).getBrand()+"' and p.type='"+productCustom.get(i).getType()+"'");
			List<Product> p =(List<Product>) QueryFactory.create(applicationContext, query);

			for(int j=0; j < productCustom.get(i).getQuantity(); j++){
				products.add(p.get(j));
			}
		}


		String query = new String("from Customer c where c.account.username='"+appInfo.getUser().getAccount().getUsername()+"'");	
		Customer customer = QueryFactory.getCustomerByUser(applicationContext, query);

		Date dateOrder = CurrentData.getLocaleData();
		Date dateConsignment = null;

		double sellingCost = 0.0;

		SellingDao sellingDao = (SellingDao) applicationContext.getBean("sellingDao");
		ProductDao productDao = (ProductDao) applicationContext.getBean("productDao");

		Selling selling = new Selling(customer, dateOrder, dateConsignment, 0.0, null);
		sellingDao.create(selling);

		for(int i=0; i<products.size(); i++){
			products.get(i).setSelling(selling);
			productDao.update(products.get(i));

			sellingCost+= products.get(i).getPrice();
		}

		selling.setProducts(products);
		selling.setSellingCost(sellingCost);
		sellingDao.update(selling);	

		appInfo.getShoppingCart().getProductsIn().clear();

		redirect.addFlashAttribute("selling",selling);
		redirect.addFlashAttribute("stockList",productsCustomList.getProductsCustom());
		return "redirect:/reviewSellingSuccess";

	}

	@RequestMapping(value="/reviewSellingSuccess",method=RequestMethod.GET)
	public String reviewSellingSuccess(HttpSession session,Model model){

		ApplicationInfo appInfo = (ApplicationInfo) session.getAttribute("info");
		if(appInfo.isUserLogged())
			model.addAttribute("user", appInfo.getUser());

		return "reviewSellingSuccess";
	}

	
	@RequestMapping(value="/showSelling",method=RequestMethod.GET)
	public String showSelling(Model model){

		List<Selling> sellings = (List<Selling>)QueryFactory.create(applicationContext,"from Selling");
		
		model.addAttribute("sellings",sellings);
		
		return "showSelling";
	}

	@RequestMapping(value="/checkOutSelling",method=RequestMethod.GET)
	 public String checkOutAppointments(Model model){
		 

		 	String query = new String("from Selling s where s.billing=billing");
			List<Reserve> reserveList = (List<Reserve>)QueryFactory.getSellingByParameter(applicationContext, query, null);
			
			model.addAttribute("reserveList",reserveList);
		
		 return "checkOutAppointments";
	 }


	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;

	}


}
