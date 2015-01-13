package it.unical.ilBelloDelleDonne.Spring.Controller;

import it.unical.ilBelloDelleDonne.ApplicationData.ProductCustom;
import it.unical.ilBelloDelleDonne.Hibernate.Dao.ProductDao;
import it.unical.ilBelloDelleDonne.Hibernate.Model.Product;
import it.unical.ilBelloDelleDonne.Hibernate.Utilities.QueryFactory;

import java.util.ArrayList;
import java.util.List;

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

	@RequestMapping(value="/showWarehouse", method=RequestMethod.GET)
	public String showWarehouse(Model model){

		System.out.println("ciao");
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

		return "showWarehouse";
	}

	@RequestMapping(value="/insertProduct",method=RequestMethod.GET)
	public String insertProduct(){
		System.out.println("ciaoooo");
		return "insertProduct";
	}

	@RequestMapping(value="/insertNewProduct",method=RequestMethod.POST)
	public String insertNewProduct(@ModelAttribute("insProduct") ProductCustom productCustom,
			RedirectAttributes redirect){

		int nProd = productCustom.getQuantity();

		ProductDao productDao = (ProductDao) applicationContext.getBean("productDao");

		for(int i = 0 ; i<nProd; i++){
			Product product = new Product();
			product.setType(productCustom.getType());
			product.setBrand(productCustom.getBrand());
			product.setDescription(productCustom.getDescription());
			product.setPrice(productCustom.getPrice());
			productDao.create(product);
		}

		
		redirect.addFlashAttribute("message","prodotti inseriti con successo");
		return "redirect:myAccount";
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext)throws BeansException {
		this.applicationContext = applicationContext;
	}
}
