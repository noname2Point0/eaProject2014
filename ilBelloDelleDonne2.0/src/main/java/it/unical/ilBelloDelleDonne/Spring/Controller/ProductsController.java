package it.unical.ilBelloDelleDonne.Spring.Controller;

import it.unical.ilBelloDelleDonne.ApplicationData.ApplicationInfo;
import it.unical.ilBelloDelleDonne.ApplicationData.ProductCustom;
import it.unical.ilBelloDelleDonne.Hibernate.Utilities.QueryFactory;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ProductsController implements ApplicationContextAware{

	private ApplicationContext applicationContext;

	@RequestMapping(value="/products",method=RequestMethod.GET)
	public String products(Model model,HttpSession session){
		
		ApplicationInfo appInfo = (ApplicationInfo) session.getAttribute("info");
		if(appInfo.isUserLogged())
			model.addAttribute("user", appInfo.getUser());
		
		List<Object[]> list = QueryFactory.create(applicationContext,  new String(" select prod.type, prod.brand, prod.description, prod.price, count(*)"
																																			+" from Product prod"
																																			+" group by prod.type, prod.brand, prod.description, prod.price"));

		ArrayList<ProductCustom> stockList = new ArrayList<ProductCustom>();

		for(Object[] row: list){
			ProductCustom stock = new ProductCustom();
			stock.setType(String.valueOf(row[0]));
			stock.setBrand(String.valueOf(row[1]));
			stock.setDescription(String.valueOf(row[2]));
			stock.setPrice(Double.valueOf(String.valueOf(row[3])));
			stock.setQuantity(Integer.valueOf(String.valueOf(row[4])));

			stockList.add(stock);
		}

		model.addAttribute("stockList",stockList);

		return "products";
	}

	@Override
	public void setApplicationContext( ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}

}