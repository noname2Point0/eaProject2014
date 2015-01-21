package it.unical.ilBelloDelleDonne.Spring.Controller;

import it.unical.ilBelloDelleDonne.ApplicationData.ApplicationInfo;
import it.unical.ilBelloDelleDonne.Hibernate.Dao.CreateBlobDao;
import it.unical.ilBelloDelleDonne.Hibernate.Dao.DocumentDao;
import it.unical.ilBelloDelleDonne.Hibernate.Dao.ProductDao;
import it.unical.ilBelloDelleDonne.Hibernate.Dao.ProductStockDao;
import it.unical.ilBelloDelleDonne.Hibernate.Dao.SellingDao;
import it.unical.ilBelloDelleDonne.Hibernate.Model.Document;
import it.unical.ilBelloDelleDonne.Hibernate.Model.Product;
import it.unical.ilBelloDelleDonne.Hibernate.Model.ProductStock;
import it.unical.ilBelloDelleDonne.Hibernate.Model.Selling;
import it.unical.ilBelloDelleDonne.Hibernate.Utilities.MyData;
import it.unical.ilBelloDelleDonne.Hibernate.Utilities.QueryFactory;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.Base64;
import java.util.Base64.Encoder;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.IOUtils;
import org.hamcrest.core.Is;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sun.mail.util.BASE64EncoderStream;

@Controller
public class WarehouseController implements ApplicationContextAware{

	private ApplicationContext applicationContext;

	@RequestMapping(value="/products",method=RequestMethod.GET)
	public String products(Model model,HttpSession session, HttpServletResponse response){

		ApplicationInfo appInfo = (ApplicationInfo) session.getAttribute("info");
		if(appInfo.isUserLogged()){
			model.addAttribute("user", appInfo.getUser());
		}

		List<ProductStock> pStocks = (List<ProductStock>)QueryFactory.create(applicationContext,"from ProductStock");


//		for(ProductStock ps:pStocks){

			//		ImageWrapper image = imageDao.retrieve(pStocks.get(0).getImageWrapper().getId());
//			byte[] data = ps.getB();
/*
			try {

				response.setHeader("Location", "http://localhost:8080/application/products.jsp");
				response.setContentType("image/png");
				response.setContentLength((int)data.length);

				OutputStream out = response.getOutputStream();
				out.write(data);
				//	IOUtils.copy(image.getData(), out);
				out.flush();
				out.close();
				//	response.reset();

			} catch (IOException e) {
				e.printStackTrace();
			}
			*/

//		}


		model.addAttribute("stockList",pStocks);

		return "products";
	}

	@RequestMapping(value ="/product/{id}/image")
	@ResponseBody
	public byte[] retrieveimage(@PathVariable("id") Integer id){
		
		System.out.println("ciao");
		ProductStockDao productStockDao = (ProductStockDao) applicationContext.getBean("productStockDao");
		
	//	byte[] data = (productStockDao.retrieve(id).getB());
		byte[] d = Base64.getEncoder().encode(productStockDao.retrieve(id).getB());
	//	String data = Base64.getEncoder().encodeToString(productStockDao.retrieve(id).getB());
	//	System.out.println("ecco l'immagine "+data);
		
		return d;

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


			return "setAlterProduct";

		}
		@RequestMapping(value="/insertProduct",method=RequestMethod.GET)
		public String insertProduct(){

			return "insertProduct";
		}

		@RequestMapping(value="/insertNewProduct",method=RequestMethod.POST)
		public String insertNewProduct(@ModelAttribute("product") ProductStock productCustom,
				@RequestParam("file") MultipartFile file, RedirectAttributes redirect){


			ProductDao productDao = (ProductDao) applicationContext.getBean("productDao");
			ProductStockDao productStockDao = (ProductStockDao) applicationContext.getBean("productStockDao");

			System.out.println("Type:" + productCustom.getType());
			System.out.println("Brand:" + productCustom.getBrand());
			System.out.println("Desc:" + productCustom.getDescription());
			System.out.println("File:" + file.getName());
			System.out.println("Path: "+file.getOriginalFilename());
			System.out.println("ContentType:" + file.getContentType());


			//        Session session = sessionFactory.getCurrentSession();
			//        byte[] data = new byte[(int) file.getSize()];
			//        Blob blob = Hibernate.getLobCreator(session).createBlob(data);
			//        
			//        productCustom.setBlob(blob);     
			//        String split [] = file.getOriginalFilename().split(".");
			//        String nameImage = "";
			//        for(int i=0; i<split.length-1; i++){
			//        	 nameImage = split[i]+"";
			//        }
			//        nameImage = nameImage+".png";


			byte[] data = new byte[(int) file.getSize()];

			try{
				data = file.getBytes();
			}
			catch(Exception e){
				e.printStackTrace();
			}


			//	ImageWrapper imageWrapper = new ImageWrapper(file.getOriginalFilename(), data);
			productCustom.setB(data);
			//	imageDao.create(imageWrapper);

			productStockDao.create(productCustom);


			int nProd = productCustom.getQuantity();             


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
