package it.unical.ilBelloDelleDonne.Hibernate.Utilities;

import it.unical.ilBelloDelleDonne.ApplicationData.EmailType;
import it.unical.ilBelloDelleDonne.ApplicationData.SendEmail;
import it.unical.ilBelloDelleDonne.Hibernate.Dao.AccountDao;
import it.unical.ilBelloDelleDonne.Hibernate.Dao.ImageWrapperDao;
import it.unical.ilBelloDelleDonne.Hibernate.Dao.ProductDao;
import it.unical.ilBelloDelleDonne.Hibernate.Dao.ProductStockDao;
import it.unical.ilBelloDelleDonne.Hibernate.Dao.SellingDao;
import it.unical.ilBelloDelleDonne.Hibernate.Dao.ServiceDao;
import it.unical.ilBelloDelleDonne.Hibernate.Dao.UserDao;
import it.unical.ilBelloDelleDonne.Hibernate.Model.Account;
import it.unical.ilBelloDelleDonne.Hibernate.Model.Admin;
import it.unical.ilBelloDelleDonne.Hibernate.Model.Customer;
import it.unical.ilBelloDelleDonne.Hibernate.Model.Employee;
import it.unical.ilBelloDelleDonne.Hibernate.Model.ImageWrapper;
import it.unical.ilBelloDelleDonne.Hibernate.Model.Product;
import it.unical.ilBelloDelleDonne.Hibernate.Model.ProductStock;
import it.unical.ilBelloDelleDonne.Hibernate.Model.Selling;
import it.unical.ilBelloDelleDonne.Hibernate.Model.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.springframework.context.ApplicationContext;


public abstract class FillDBFactory{

	public static void create(ApplicationContext context){
	
//		createProduct(context);
//		createService(context);
		createUser(context);
	}
	

	private static void createProduct(ApplicationContext context){
		

		ImageWrapperDao imageDao = (ImageWrapperDao) context.getBean("imageWrapperDao");
		ProductStockDao psd = (ProductStockDao) context.getBean("productStockDao");
		ProductDao productDao = (ProductDao) context.getBean("productDao");
		
		ImageWrapper imageWrapper = new ImageWrapper();

		ProductStock productStock = new ProductStock("shampoo","garnier","shampoo medio",5,100,4.5, imageWrapper);
		imageWrapper = LoadImage.load(productStock.getType()+"_"+productStock.getBrand()+".png");
		productStock.setImageWrapper(imageWrapper);
		imageDao.create(imageWrapper);

		psd.create(productStock);

		ArrayList<Product> products = new ArrayList<Product>();
		for(int i = 0; i<productStock.getQuantity(); i++){
			Product product = new Product(productStock);
			products.add(product);
			productDao.create(product);
		}
		productStock.setProducts(products);
		psd.update(productStock);


		psd = (ProductStockDao) context.getBean("productStockDao");

		ImageWrapper imageWrapper2 = new ImageWrapper();

		productStock = new ProductStock("piastra","imetech","bellissima",5,100,80.0, imageWrapper2);
		
		imageWrapper2 = LoadImage.load(productStock.getType()+"_"+productStock.getBrand()+".png");
		productStock.setImageWrapper(imageWrapper2);
		imageDao.create(imageWrapper2);
		
		productDao = (ProductDao) context.getBean("productDao");

		psd.create(productStock);

		products = new ArrayList<Product>();
		for(int i = 0; i<productStock.getQuantity(); i++){
			Product product = new Product(productStock);
			products.add(product);
			productDao.create(product);
		}
		productStock.setProducts(products);
		psd.update(productStock);



		psd = (ProductStockDao) context.getBean("productStockDao");

		ImageWrapper imageWrapper3 = new ImageWrapper();

		productStock = new ProductStock("phon","parlux","4200",5,100,60.0,imageWrapper3);
		
		imageWrapper3 = LoadImage.load(productStock.getType()+"_"+productStock.getBrand()+".png");
		productStock.setImageWrapper(imageWrapper3);
		imageDao.create(imageWrapper3);
		
		productDao = (ProductDao) context.getBean("productDao");

		psd.create(productStock);

		products = new ArrayList<Product>();
		for(int i = 0; i<productStock.getQuantity(); i++){
			Product product = new Product(productStock);
			products.add(product);
			productDao.create(product);
		}
		productStock.setProducts(products);
		psd.update(productStock);

	}

	private static void createService(ApplicationContext context){

		ServiceDao serviceDao = (ServiceDao) context.getBean("serviceDao");

		serviceDao.create(new Service(new String("piega"),3.0));
		serviceDao.create(new Service(new String("taglio e piega"),15.0));
		serviceDao.create(new Service(new String("taglio, colore e piega"),25.0));
		serviceDao.create(new Service(new String("colore e piega"),20.0));
		serviceDao.create(new Service(new String("trattamento permanente"),25.0));
		serviceDao.create(new Service(new String("meches"),10.0));
		serviceDao.create(new Service(new String("maschere facciali"),8.0));
		serviceDao.create(new Service(new String("trattamento rinvigorente"),10.0));

	}

	private static void createUser(ApplicationContext context){

		UserDao userDao = (UserDao) context.getBean("userDao");
		
		AccountDao accountDao = (AccountDao) context.getBean("accountDao");
		
		SellingDao sellingDao = (SellingDao) context.getBean("sellingDao");
		
		Account account1 = new Account("admin", "admin", AccountType.getAdminType());
//		Account account2 = new Account("", "paswd00", AccountType.getCustomerType());
//		Account account3 = new Account("giuseppe", "paswd01", AccountType.getEmployeeWarehouseType());
//		Account account4 = new Account("giovanni", "paswd02", AccountType.getEmployeeSaloonType());
//		Account account5 = new Account("pasquale", "pasquale", AccountType.getCustomerType());
//		Account account6 = new Account("luca", "luca", AccountType.getCustomerType());

		accountDao.create(account1);
//		accountDao.create(account2);
//		accountDao.create(account3);
//		accountDao.create(account4);
//		accountDao.create(account5);
//		accountDao.create(account6);
		
		Date date1=null;
//		Date date2=null;
//		Date date3=null;
//		Date date4=null;
		
		Date dateOrder1 = null;
		Date dateOrder2 = null;
		Date dateOrder3 = null;
		
		try{
			String birth1 = "1989-12-02";
//			String birth2 = "1980-10-07";
//			String birth3 = "1985-11-12";
//			String birth4 = "1989-06-12";
			
			date1 = new SimpleDateFormat("yyyy-MM-dd").parse(birth1);
//			date2 = new SimpleDateFormat("yyyy-MM-dd").parse(birth2);
//			date3 = new SimpleDateFormat("yyyy-MM-dd").parse(birth3);
//			date4 = new SimpleDateFormat("yyyy-MM-dd").parse(birth4);
			
			
//			String order1 = "12-12-2014";
//			String order2 = "10-10-2014";
//			String order3 = "11-11-2014";
			
//			dateOrder1 = new SimpleDateFormat("yyyy-MM-dd").parse(order1);
//			dateOrder2 = new SimpleDateFormat("yyyy-MM-dd").parse(order2);
//			dateOrder3 = new SimpleDateFormat("yyyy-MM-dd").parse(order3);
		}
		catch (Exception e) {
			e.addSuppressed(e.getCause());
		}		
//		Customer customer1 = new Customer("vincenzo", "Algieri", "cosenza", "mazzini", "3209878655", date1, "lgrvcn89t02d005r", "algieri@tiscali.it", account2);
//		Customer customer2 = new Customer("pasquale", "Grimaldi", "rende", "rossini", "3209889765", date1, "pqlgmd89g13d005r", "pasquale@gmail.com", account5);
//		Customer customer3 = new Customer("luca", "Di Lascio", "lauria", "boh", "3209859765", date3, "dlslca92g13d005r", "luca@gmail.com", account6);
//		Employee employee1 = new Employee("nicola",  "Benedetto", "cosenza", "mazzini", "3209877055", date2, "magazzino", "benedetto@tiscali.it", account3);
//		Employee employee2 = new Employee("nicola",  "Benedetto", "cosenza", "mazzini", "3209877055", date4, "saloon", "benedetto@tiscali.it", account4);
		Admin admin1 = new Admin("giuseppe", "cristiano", "cosenza", "mazzini", "3223877055", date1, "admin@tiscali.it", account1);
	
//		userDao.create(customer1);
//		userDao.create(customer2);
//		userDao.create(customer3);
//		userDao.create(employee1);
//		userDao.create(employee2);
		userDao.create(admin1);
		
		
		/*
		
		Selling selling1 = new Selling(customer2, dateOrder1 , null , 56.0);
		Selling selling2 = new Selling(customer2, dateOrder3 , null , 98.0);
		Selling selling3 = new Selling(customer2, dateOrder3 , null , 34.0);
		Selling selling4 = new Selling(customer1, dateOrder2 , null , 10.0);
		Selling selling5 = new Selling(customer1, dateOrder2 , null , 1000.0);
		Selling selling6 = new Selling(customer1, dateOrder1 , null , 578.0);
		Selling selling7 = new Selling(customer3, dateOrder3 , null , 55.0);
		Selling selling8 = new Selling(customer3, dateOrder3 , null , 12.0);
		Selling selling9 = new Selling(customer3, dateOrder2 , null , 123.0);
		
		sellingDao.create(selling1);
		sellingDao.create(selling2);
		sellingDao.create(selling3);
		sellingDao.create(selling4);
		sellingDao.create(selling5);
		sellingDao.create(selling6);
		sellingDao.create(selling7);
		sellingDao.create(selling8);
		sellingDao.create(selling9);
		*/
				
	}

}