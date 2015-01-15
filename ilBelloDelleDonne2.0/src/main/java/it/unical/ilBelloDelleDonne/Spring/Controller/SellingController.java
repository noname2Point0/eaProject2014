package it.unical.ilBelloDelleDonne.Spring.Controller;

import it.unical.ilBelloDelleDonne.ApplicationData.ApplicationInfo;
import it.unical.ilBelloDelleDonne.ApplicationData.DataProvider;
import it.unical.ilBelloDelleDonne.ApplicationData.ProductCustomList;
import it.unical.ilBelloDelleDonne.Hibernate.Dao.ProductDao;
import it.unical.ilBelloDelleDonne.Hibernate.Dao.SellingDao;
import it.unical.ilBelloDelleDonne.Hibernate.Dao.UserDao;
import it.unical.ilBelloDelleDonne.Hibernate.Model.Customer;
import it.unical.ilBelloDelleDonne.Hibernate.Model.Product;
import it.unical.ilBelloDelleDonne.Hibernate.Model.ProductStock;
import it.unical.ilBelloDelleDonne.Hibernate.Model.Reserve;
import it.unical.ilBelloDelleDonne.Hibernate.Model.Selling;
import it.unical.ilBelloDelleDonne.Hibernate.Model.User;
import it.unical.ilBelloDelleDonne.Hibernate.Utilities.MyData;
import it.unical.ilBelloDelleDonne.Hibernate.Utilities.QueryFactory;

import java.util.ArrayList;
import java.util.Date;
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
			productCustomList.setProductsStock(appInfo.getShoppingCart().getProductsIn());

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
		ArrayList<Product> products = new ArrayList<Product>();

		Double sum = 0.0;
		for(ProductStock productStock :productsCustomList.getProductsStock()){
					
			String query = new String("from Product p where p.productStock.id="+productStock.getId()+"");
			List<Product> p =(List<Product>) QueryFactory.create(applicationContext, query);

			for(int i = 0; i<productStock.getQuantity(); i++){
				products.add(p.get(i));
				sum+= productStock.getPrice();
			}
		}
	
	
		UserDao userDao = (UserDao)applicationContext.getBean("userDao");
		Customer customer = (Customer) userDao.retrieve(appInfo.getUser().getAccount().getUsername());
		
		Date dateOrder = MyData.getLocaleData();
		SellingDao sellingDao = (SellingDao) applicationContext.getBean("sellingDao");
		ProductDao productDao = (ProductDao) applicationContext.getBean("productDao");

		Selling selling = new Selling(customer, dateOrder, null, sum);
		sellingDao.create(selling);
		
		for(int i = 0; i<products.size(); i++){
			products.get(i).setSelling(selling);
			productDao.update(products.get(i));
		}

		selling.setProducts(products);
		sellingDao.update(selling);
		
		
		appInfo.getShoppingCart().getProductsIn().clear();
		session.setAttribute("info", appInfo);
		
		redirect.addFlashAttribute("selling",selling);
		redirect.addFlashAttribute("stockList",productsCustomList.getProductsStock());
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
