package it.unical.ilBelloDelleDonne.ApplicationData;

import it.unical.ilBelloDelleDonne.Hibernate.Dao.ServiceDao;
import it.unical.ilBelloDelleDonne.Hibernate.Dao.UserDao;
import it.unical.ilBelloDelleDonne.Hibernate.Model.Customer;
import it.unical.ilBelloDelleDonne.Hibernate.Model.Service;
import it.unical.ilBelloDelleDonne.Hibernate.Model.User;

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
	
}
