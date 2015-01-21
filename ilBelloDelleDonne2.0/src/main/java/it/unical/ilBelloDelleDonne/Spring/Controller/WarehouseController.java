package it.unical.ilBelloDelleDonne.Spring.Controller;

import it.unical.ilBelloDelleDonne.ApplicationData.ApplicationInfo;
import it.unical.ilBelloDelleDonne.ApplicationData.DataProvider;
import it.unical.ilBelloDelleDonne.Hibernate.Dao.ProductDao;
import it.unical.ilBelloDelleDonne.Hibernate.Dao.ProductStockDao;
import it.unical.ilBelloDelleDonne.Hibernate.Dao.SellingDao;
import it.unical.ilBelloDelleDonne.Hibernate.Model.Product;
import it.unical.ilBelloDelleDonne.Hibernate.Model.ProductStock;
import it.unical.ilBelloDelleDonne.Hibernate.Model.Selling;
import it.unical.ilBelloDelleDonne.Hibernate.Utilities.MyData;
import it.unical.ilBelloDelleDonne.Hibernate.Utilities.QueryFactory;

import java.util.Base64;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class WarehouseController implements ApplicationContextAware{

	private ApplicationContext applicationContext;

	
	@RequestMapping(value="/productsGrid",method=RequestMethod.GET)
	public String getProductsGrid(Model model,HttpSession session){
	
		ApplicationInfo appInfo = (ApplicationInfo) session.getAttribute("info");
		if(appInfo.isUserLogged())
			model.addAttribute("user", appInfo.getUser());
		
		List<ProductStock> productStocks = DataProvider.getAvailableProductsList(applicationContext);
		model.addAttribute("stockList",productStocks);

		return "productsGrid";
	}
	

	@RequestMapping(value="/showWarehouse", method=RequestMethod.GET)
	public String showWarehouse(Model model){
		
		List<ProductStock> productStocks = (List<ProductStock>)QueryFactory.create(applicationContext,"from ProductStock");
		
		model.addAttribute("stockList",productStocks);

		return "showWarehouse";
	}
	
	@RequestMapping(value="/sendSelling",method=RequestMethod.GET)
	public String getSendSelling(Model model){
	
		List<Selling> sellings = QueryFactory.create(applicationContext,"from Selling s where s.dateConsignment is null");
		
		model.addAttribute("sellings",sellings);
		
		return "sendSelling";
	}

	@RequestMapping(value="/sendSelling",method=RequestMethod.POST)
	public String setSendSelling(Model model,@RequestParam("sellingId")int sellingId,RedirectAttributes redirect){
	
		SellingDao sellingDao = (SellingDao)applicationContext.getBean("sellingDao");
		Selling selling = sellingDao.retrieve(sellingId);
		selling.setDateConsignment(MyData.getLocaleData());
		sellingDao.update(selling);
		
		redirect.addFlashAttribute("message","operazione eseguita con successo. Spedizione in attesa del check out dell'amministratore");
		return "redirect:myAccount";
	}
	
	
	@RequestMapping(value="/alterProduct",method=RequestMethod.GET)
	public String alterProduct(Model model){
		
	List<ProductStock> productStocks = (List<ProductStock>)QueryFactory.create(applicationContext,"from ProductStock");
		
	model.addAttribute("stockList",productStocks);
	return "alterProduct";
	}
	
	@RequestMapping(value="/setAlterProduct",method=RequestMethod.GET)
	public String setAlterProduct(@ModelAttribute("altProduct")ProductStock productStock){
	
		System.out.println(productStock.getBrand());
	
	return "setAlterProduct";
	
	}
	@RequestMapping(value="/insertProduct",method=RequestMethod.GET)
	public String insertProduct(){

		return "insertProduct";
	}
	
	@RequestMapping(value="/product/{id}/image")
	public String  getProductImage(@PathVariable("id")int id){
		System.err.println(id);
		ProductStockDao psDao = (ProductStockDao) applicationContext.getBean("productStockDao");
		byte[] image = psDao.retrieve(id).getImage();
		String str = Base64.getEncoder().encodeToString(image);
		return str;
	}
	
	@RequestMapping(value="/insertNewProduct",method=RequestMethod.POST)
	public String insertNewProduct(@ModelAttribute("insProduct") ProductStock productCustom,
			@RequestParam("file") MultipartFile file,
			RedirectAttributes redirect){

		int nProd = productCustom.getQuantity();

		ProductDao productDao = (ProductDao) applicationContext.getBean("productDao");
		ProductStockDao productStockDao = (ProductStockDao) applicationContext.getBean("productStockDao");
		
		byte[] data = new byte[(int) file.getSize()];

		try{
			data = file.getBytes();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		productCustom.setImage(data);
		productStockDao.create(productCustom);
		
		for(int i = 0 ; i<nProd; i++){
			Product product = new Product(productCustom);
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
