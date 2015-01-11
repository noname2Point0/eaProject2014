package it.unical.ilBelloDelleDonne.Spring.Controller;

import it.unical.ilBelloDelleDonne.ApplicationData.ApplicationInfo;
import it.unical.ilBelloDelleDonne.ApplicationData.ProductCustom;
import it.unical.ilBelloDelleDonne.ApplicationData.ProductCustomList;
import it.unical.ilBelloDelleDonne.Hibernate.Model.Product;
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
			@ModelAttribute("productsCustomList") ProductCustomList productsCustomList){
		
		ArrayList<ProductCustom> pc = new ArrayList<ProductCustom>(productsCustomList.getProductsCustom());
		List<Product> products;
		
		for(int i=0; i < pc.size(); i++){
			String query = new String("from Product p where p.description='"+pc.get(i).getDescription()+"' and p.brand='"+pc.get(i).getBrand()+"' and p.type='"+pc.get(i).getType()+"' and p.price='"+pc.get(i).getPrice()+"'");
			List<Product> p = QueryFactory.create(applicationContext, query);
			System.out.println("ecco i prodotti "+p.get(i).getDescription());
		
		}
		

		return "redirect:/home";
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
		
	}
	
	
}
