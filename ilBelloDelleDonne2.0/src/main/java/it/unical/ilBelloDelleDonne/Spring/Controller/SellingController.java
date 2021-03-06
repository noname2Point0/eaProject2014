package it.unical.ilBelloDelleDonne.Spring.Controller;

import it.unical.ilBelloDelleDonne.ApplicationData.ApplicationInfo;
import it.unical.ilBelloDelleDonne.ApplicationData.DataProvider;
import it.unical.ilBelloDelleDonne.ApplicationData.ProductCustomList;
import it.unical.ilBelloDelleDonne.ApplicationData.SendEmail;
import it.unical.ilBelloDelleDonne.Hibernate.Dao.ProductDao;
import it.unical.ilBelloDelleDonne.Hibernate.Dao.ProductStockDao;
import it.unical.ilBelloDelleDonne.Hibernate.Dao.SellingDao;
import it.unical.ilBelloDelleDonne.Hibernate.Dao.UserDao;
import it.unical.ilBelloDelleDonne.Hibernate.Model.Customer;
import it.unical.ilBelloDelleDonne.Hibernate.Model.Product;
import it.unical.ilBelloDelleDonne.Hibernate.Model.ProductStock;
import it.unical.ilBelloDelleDonne.Hibernate.Model.Selling;
import it.unical.ilBelloDelleDonne.Hibernate.Model.User;
import it.unical.ilBelloDelleDonne.Hibernate.Utilities.AccountType;
import it.unical.ilBelloDelleDonne.Hibernate.Utilities.MyData;

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

		if(!appInfo.isUserLogged()){
			appInfo.setSelling(true);
			redirect.addFlashAttribute("message","devi accedere al sistema prima di poter effettuare l'acquisto");
			return "redirect:/login";
		}

		if(appInfo.getSelling())
			appInfo.setSelling(false);


		ProductCustomList productCustomList = new ProductCustomList();
		productCustomList.setProductsStock(appInfo.getShoppingCart().getProductsIn());

		model.addAttribute("productCustomList",productCustomList);
		model.addAttribute("user",appInfo.getUser());

		return "sellingProducts";

	}

	@RequestMapping(value="/confirmSelling",method=RequestMethod.GET)
	public String orderProductsPost(
			@ModelAttribute("productsCustomList") ProductCustomList productsCustomList,
			HttpSession session,
			RedirectAttributes redirect){

		ApplicationInfo appInfo = (ApplicationInfo) session.getAttribute("info");
		ArrayList<Product> products = new ArrayList<Product>();

		Double sum = 0.0;
		ProductStockDao productStockDao = (ProductStockDao) applicationContext.getBean("productStockDao");


		for(ProductStock productStock :productsCustomList.getProductsStock()){		
		
			List<Product> p = DataProvider.getProductListFromStockNotSelling(applicationContext, productStock.getId());

			if(productStock.getQuantity() <= p.size()){

				for(int i = 0; i<productStock.getQuantity(); i++){
					products.add(p.get(i));
					sum+= productStock.getPrice();
				}
				ProductStock ps = productStockDao.retrieve(productStock.getId());
				ps.setQuantity(ps.getQuantity()-productStock.getQuantity());
				productStockDao.update(ps);
			}
			else{
				String message = new String("Quantità non disponibile");
				redirect.addFlashAttribute("message", message);
				return "redirect:/sellingProducts";
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


		appInfo.getShoppingCart().clearCart();
		session.setAttribute("info", appInfo);

		redirect.addFlashAttribute("selling",selling);
		redirect.addFlashAttribute("stockList",productsCustomList.getProductsStock());

		try{
			SendEmail.sendSellingConfirmation(appInfo.getUser(), selling);
		}
		catch(Exception e){
			e.getCause();
		}

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
	public String showSelling(Model model,HttpSession session){

		ApplicationInfo appInfo = (ApplicationInfo) session.getAttribute("info");

		User user = appInfo.getUser();
		List<Selling> sellings;

		if(AccountType.isCustomer(user.getAccount().getType()))
			sellings = DataProvider.getCustomerSellingList(applicationContext,user.getAccount().getUsername());
		else
			sellings = DataProvider.getSellingList(applicationContext);

		model.addAttribute("sellings",sellings);

		return "showSelling";
	}

	@RequestMapping(value="/checkOutSelling",method=RequestMethod.GET)
	public String checkOutAppointments(Model model){

		List<Selling> sellings = DataProvider.getSellingListNoBilling(applicationContext);

		model.addAttribute("sellings",sellings);

		return "checkOutSelling";
	}


	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;

	}


}
