package it.unical.ilBelloDelleDonne.Spring.Controller;

import it.unical.ilBelloDelleDonne.ApplicationData.ApplicationInfo;
import it.unical.ilBelloDelleDonne.ApplicationData.ProductCustom;
import it.unical.ilBelloDelleDonne.ApplicationData.ShoppingCart;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ShoppingCartController {

	@RequestMapping(value="/addToCart",method=RequestMethod.GET)
	public String addToShoppingCart(HttpSession session, @ModelAttribute("productCustom") ProductCustom productCustom){
		
		ApplicationInfo appInfo = (ApplicationInfo) session.getAttribute("info");
		
		appInfo.getShoppingCart().addToCart(productCustom);
		session.setAttribute("info", appInfo);
		
		return "redirect:/products";
	}
	
	@RequestMapping(value="/removeToCart",method=RequestMethod.GET)
	public String removeToShoppingCart(HttpSession session, @ModelAttribute("productCustom") ProductCustom productCustom){
		
		ApplicationInfo appInfo = (ApplicationInfo) session.getAttribute("info");
		appInfo.getShoppingCart().removeToCart(productCustom);
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
