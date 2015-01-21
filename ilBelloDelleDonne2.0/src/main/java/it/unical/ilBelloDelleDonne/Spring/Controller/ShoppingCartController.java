package it.unical.ilBelloDelleDonne.Spring.Controller;

import it.unical.ilBelloDelleDonne.ApplicationData.ApplicationInfo;
import it.unical.ilBelloDelleDonne.ApplicationData.ShoppingCart;
import it.unical.ilBelloDelleDonne.Hibernate.Model.ProductStock;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ShoppingCartController {

	@RequestMapping(value="/addToCart",method=RequestMethod.GET)
	public String addToShoppingCart(HttpSession session, 
			@ModelAttribute("productCustom") ProductStock productStock){
		
		ApplicationInfo appInfo = (ApplicationInfo) session.getAttribute("info");
		
		appInfo.getShoppingCart().addToCart(productStock);
		session.setAttribute("info", appInfo);
		
		return "redirect:/products";
	}
/*	
	@RequestMapping(value="/showImage",method=RequestMethod.GET)
	public String showImage(Model model,HttpSession session,
			@RequestParam("imageWrapper") ImageWrapper image){
		
			System.out.println("SONO NEL CONTROLLER AL CLICK DELL'IMMAGINE");
			System.out.println("ecco l'immagine "+image.getImageName());
		
		return "redirect:/products";
	}
	*/
	
	@RequestMapping(value="/removeToCart",method=RequestMethod.GET)
	public String removeToShoppingCart(HttpSession session, 
			@ModelAttribute("productCustom") ProductStock productStock){
		
		System.out.println(productStock.getId());
		
		ApplicationInfo appInfo = (ApplicationInfo) session.getAttribute("info");
		appInfo.getShoppingCart().removeToCart(productStock);
		session.setAttribute("info", appInfo);
		
		return "redirect:/shoppingCart";
	}
	
	@RequestMapping(value="/shoppingCart", method= RequestMethod.GET)
	public String showShoppingCart(HttpSession session, Model model){
		
		ApplicationInfo appInfo = (ApplicationInfo) session.getAttribute("info");
		if(appInfo.isUserLogged())
			model.addAttribute("user", appInfo.getUser());
		
		ShoppingCart shoppingCart = (ShoppingCart) appInfo.getShoppingCart();
		
		if(shoppingCart.isEmpyt()){
			model.addAttribute("message","non hai selezionato alcun oggetto da acquistare");
		}
	
		model.addAttribute("shoppingList",shoppingCart.getProductsIn());
		return "shoppingCart";
	}
	
}
