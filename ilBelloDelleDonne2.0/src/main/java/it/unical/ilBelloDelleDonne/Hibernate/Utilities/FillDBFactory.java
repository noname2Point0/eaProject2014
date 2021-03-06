package it.unical.ilBelloDelleDonne.Hibernate.Utilities;

import it.unical.ilBelloDelleDonne.Hibernate.Dao.AccountDao;
import it.unical.ilBelloDelleDonne.Hibernate.Dao.ReserveDao;
import it.unical.ilBelloDelleDonne.Hibernate.Dao.SellingDao;
import it.unical.ilBelloDelleDonne.Hibernate.Dao.ServiceDao;
import it.unical.ilBelloDelleDonne.Hibernate.Dao.UserDao;
import it.unical.ilBelloDelleDonne.Hibernate.Model.Account;
import it.unical.ilBelloDelleDonne.Hibernate.Model.Admin;
import it.unical.ilBelloDelleDonne.Hibernate.Model.Customer;
import it.unical.ilBelloDelleDonne.Hibernate.Model.Employee;
import it.unical.ilBelloDelleDonne.Hibernate.Model.Reserve;
import it.unical.ilBelloDelleDonne.Hibernate.Model.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.context.ApplicationContext;


public abstract class FillDBFactory{

	public static void create(ApplicationContext context){
	
		createService(context);
		createUser(context);
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
		
		
		Account account1 = new Account("admin", "admin", AccountType.getAdminType());
		Account account3 = new Account("giuseppe", "paswd01", AccountType.getEmployeeWarehouseType());
		Account account4 = new Account("giovanni", "paswd02", AccountType.getEmployeeSaloonType());
		Account account2 = new Account("vincenzo", "paswd00", AccountType.getCustomerType());

		accountDao.create(account1);
		accountDao.create(account2);
		accountDao.create(account3);
		accountDao.create(account4);
		Date date1=null;
		Date date2=null;
		Date date3=null;
		Date date4=null;
		Date date5 = null;
		String dateReserve = "2015-01-22";
		
		try{
			String birth1 = "1989-12-02";
			String birth2 = "1980-10-07";
			String birth3 = "1985-11-12";
			String birth4 = "1989-06-12";

			date1 = new SimpleDateFormat("yyyy-MM-dd").parse(birth1);
			date2 = new SimpleDateFormat("yyyy-MM-dd").parse(birth2);
			date3 = new SimpleDateFormat("yyyy-MM-dd").parse(birth3);
			date4 = new SimpleDateFormat("yyyy-MM-dd").parse(birth4);
			date5=new SimpleDateFormat("yyyy-MM-dd").parse(dateReserve);
		}
		catch (Exception e) {
			e.addSuppressed(e.getCause());
		}		
		
		Customer customer1 = new Customer("vincenzo", "Algieri", "cosenza", "mazzini", "3209878655", date1, "lgrvcn89t02d005r", "algieri@tiscali.it", account2);
		Employee employee1 = new Employee("nicola",  "Benedetto", "cosenza", "mazzini", "3209877055", date2, "magazzino", "benedetto@tiscali.it", account3);
		Employee employee2 = new Employee("nicola",  "Benedetto", "cosenza", "mazzini", "3209877055", date4, "saloon", "benedetto@tiscali.it", account4);
		Admin admin1 = new Admin("giuseppe", "cristiano", "cosenza", "mazzini", "3223877055", date1, "admin@tiscali.it", account1);
		
		userDao.create(customer1);
		userDao.create(employee1);
		userDao.create(employee2);
		userDao.create(admin1);
		
	}

}