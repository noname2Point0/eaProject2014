package it.unical.ilBelloDelleDonne.ApplicationData;

import it.unical.ilBelloDelleDonne.Hibernate.Dao.ServiceDao;
import it.unical.ilBelloDelleDonne.Hibernate.Dao.UserDao;
import it.unical.ilBelloDelleDonne.Hibernate.Model.Billing;
import it.unical.ilBelloDelleDonne.Hibernate.Model.Product;
import it.unical.ilBelloDelleDonne.Hibernate.Model.ProductStock;
import it.unical.ilBelloDelleDonne.Hibernate.Model.Reserve;
import it.unical.ilBelloDelleDonne.Hibernate.Model.Selling;
import it.unical.ilBelloDelleDonne.Hibernate.Model.Service;
import it.unical.ilBelloDelleDonne.Hibernate.Model.User;
import it.unical.ilBelloDelleDonne.Hibernate.Utilities.MyData;

import java.text.SimpleDateFormat;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.context.ApplicationContext;

public abstract class DataProvider {

	public static User getUser(ApplicationContext applicationContext, String username){
		UserDao userDao = (UserDao) applicationContext.getBean("userDao");
		User user = userDao.retrieve(username);
		return user;	
	}

	public static Service getService(ApplicationContext applicationContext, int serviceId){
		ServiceDao serviceDao = (ServiceDao) applicationContext.getBean("serviceDao");
		Service service = serviceDao.retrieve(serviceId);
		return service;
	}

	public static List<User> getUsers(ApplicationContext context){
		return (List<User>) performQuery(context, "from User");
	}
	
	public static List<Service> getServiceList(ApplicationContext applicationContext) {
		return (List<Service>) performQuery(applicationContext, "from Service");
	}
	

	public static List<Reserve> getReserveList(ApplicationContext context) {
		return (List<Reserve>) performQuery(context, "from Reserve");
	}
	
	public static List<Reserve> getReserveListNoBilling(ApplicationContext context){
		
		SimpleDateFormat smp = new SimpleDateFormat("yyyy-MM-dd");
		String currentDate = smp.format(MyData.getLocaleData());
		System.out.println(currentDate);
		String query=new String("from Reserve r where r.dateService = '"+currentDate+"' and not exists(from Billing b where b.reserve = r)");
		
		List<Reserve> reserveList = (List<Reserve>) performQuery(context,query);
		System.out.println(reserveList.size());
		return reserveList;
		
	}
	
	public static List<Product> getProductListFromStockNotSelling(ApplicationContext applicationContext, int stockProductId){
		String query = "from Product p where p.productStock.id="+stockProductId+" and not exists(from Selling s where p.selling=s)";
		
		return (List<Product>) performQuery(applicationContext, query);
	}
	
	public static List<Reserve> getCustomerReserveList( ApplicationContext applicationContext, String username) {
		String query = "from Reserve r where r.customer.account.username ='"+username+"'";
		List<Reserve> reserveList = (List<Reserve>) performQuery(applicationContext, query);
		
		return reserveList;
	}

	public static List<Reserve> getEmployeeReserveList( ApplicationContext applicationContext) {
		SimpleDateFormat smp = new SimpleDateFormat("yyyy-MM-dd");
		String currentDate = smp.format(MyData.getLocaleData());
		
		String query = "from Reserve r where r.dateService = '"+currentDate+"'";
		List<Reserve> reserveList = (List<Reserve>) performQuery(applicationContext, query);
		
		return reserveList;
		
	}
	

	public static List<Selling> getCustomerSellingList(ApplicationContext applicationContext, String username) {
		return (List<Selling>)performQuery(applicationContext,"from Selling s where s.customer.account.username = '"+username+"'");
	}

	public static List<Selling> getSellingList(ApplicationContext applicationContext) {
		return (List<Selling>) performQuery(applicationContext, "from Selling");
		
	}

	public static List<ProductStock> getAvailableProductsList(ApplicationContext applicationContext) {
		return (List<ProductStock>) performQuery(applicationContext, "from ProductStock ps where ps.quantity > 0");
	}

	public static List<Billing> getBillingList(ApplicationContext applicationContext) {

		return (List<Billing>) performQuery(applicationContext, "from Billing");
	}

	public static List<Reserve> getReserveListFromDataString(ApplicationContext context, String dataService) {
		String query = "from Reserve r where r.dateService='"+dataService+"'";
		return (List<Reserve>) performQuery(context, query);
	}

	private static List performQuery(ApplicationContext context, String query){
		SessionFactory ses = (SessionFactory) context.getBean("sessionFactory");
		Session session = ses.openSession();
		
		Query q = session.createQuery(query);
		List l = q.list();
		
		session.close();
		return l;
	}

	public static List<Selling> getSellingListNoBilling(ApplicationContext applicationContext) {
		String query=new String("from Selling s where s.dateConsignment is not null and  not exists(from Billing b where b.selling = s)");
		return (List<Selling>)performQuery(applicationContext, query);
	}

	public static List<ProductStock> getProductStockList(ApplicationContext applicationContext) {
		return (List<ProductStock>)performQuery(applicationContext,"from ProductStock");
	}

	public static List<Selling> getSellingListNoSend(ApplicationContext applicationContext) {
		String query = "from Selling s where s.dateConsignment is null";
		return (List<Selling>) performQuery(applicationContext, query);
	}
	

}
