package it.unical.ilBelloDelleDonne.Spring.Controller;

import it.unical.ilBelloDelleDonne.ApplicationData.ApplicationInfo;
import it.unical.ilBelloDelleDonne.Hibernate.Dao.ProductDao;
import it.unical.ilBelloDelleDonne.Hibernate.Model.Product;
import it.unical.ilBelloDelleDonne.Hibernate.Model.ProductStock;
import it.unical.ilBelloDelleDonne.Hibernate.Utilities.QueryFactory;

import java.util.ArrayList;
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
public class WarehouseController implements ApplicationContextAware{

	private ApplicationContext applicationContext;
	
	@RequestMapping(value="/products",method=RequestMethod.GET)
	public String products(Model model,HttpSession session){
		
		ApplicationInfo appInfo = (ApplicationInfo) session.getAttribute("info");
		if(appInfo.isUserLogged())
			model.addAttribute("user", appInfo.getUser());
		
		List<ProductStock> productStocks = (List<ProductStock>)QueryFactory.create(applicationContext,"from ProductStock");
		
		model.addAttribute("stockList",productStocks);

		return "products";
	}

	@RequestMapping(value="/showWarehouse", method=RequestMethod.GET)
	public String showWarehouse(Model model){
		
		List<ProductStock> productStocks = (List<ProductStock>)QueryFactory.create(applicationContext,"from ProductStock");
		
		model.addAttribute("stockList",productStocks);

		return "showWarehouse";
	}

	@RequestMapping(value="/insertProduct",method=RequestMethod.GET)
	public String insertProduct(){
		System.out.println("ciaoooo");
		return "insertProduct";
	}

	@RequestMapping(value="/insertNewProduct",method=RequestMethod.POST)
	public String insertNewProduct(@ModelAttribute("insProduct") ProductStock productCustom,
			RedirectAttributes redirect){

		int nProd = productCustom.getQuantity();

		ProductDao productDao = (ProductDao) applicationContext.getBean("productDao");
//
//		for(int i = 0 ; i<nProd; i++){
//			Product product = new Product();
//			product.setType(productCustom.getType());
//			product.setBrand(productCustom.getBrand());
//			product.setDescription(productCustom.getDescription());
//			product.setPrice(productCustom.getPrice());
//			productDao.create(product);
//		}

		
		redirect.addFlashAttribute("message","prodotti inseriti con successo");
		return "redirect:myAccount";
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext)throws BeansException {
		this.applicationContext = applicationContext;
	}
}
