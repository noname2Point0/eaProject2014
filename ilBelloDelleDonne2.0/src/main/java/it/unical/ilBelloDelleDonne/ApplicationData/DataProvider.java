package it.unical.ilBelloDelleDonne.ApplicationData;

import it.unical.ilBelloDelleDonne.Hibernate.Dao.ServiceDao;
import it.unical.ilBelloDelleDonne.Hibernate.Dao.UserDao;
import it.unical.ilBelloDelleDonne.Hibernate.Model.Reserve;
import it.unical.ilBelloDelleDonne.Hibernate.Model.Service;
import it.unical.ilBelloDelleDonne.Hibernate.Model.User;
import it.unical.ilBelloDelleDonne.Hibernate.Utilities.MyData;
import it.unical.ilBelloDelleDonne.Hibernate.Utilities.QueryFactory;

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
		
		String query="from Service";
		List<Service> serviceList = (List<Service>) performQuery(applicationContext, query);
		
		return serviceList;
	}
	

	public static List<Reserve> getReserveList(ApplicationContext context) {
		
		String query = "from Reserve";
		
		List<Reserve> reserveList = (List<Reserve>) performQuery(context, query);
		
		return reserveList;
	
	}
	
	public static List<Reserve> getReserveListNoBilling(ApplicationContext context){
		SimpleDateFormat smp = new SimpleDateFormat("yyyy-MM-dd");
		String currentDate = smp.format(MyData.getLocaleData());
		
		String query=new String("from Reserve r where r.dateService = '"+currentDate+"' and not exists(from Billing b where b.reserve = r)");
		
		List<Reserve> reserveList = (List<Reserve>) performQuery(context,query);
		
		return reserveList;
		
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
	
	private static List performQuery(ApplicationContext context, String query){
		SessionFactory ses = (SessionFactory) context.getBean("sessionFactory");
		Session session = ses.openSession();

		Query q = session.createQuery(query);
		List l = q.list();

		session.close();
		return l;
	}

	

}
