package it.unical.ilBelloDelleDonne.Hibernate.Utilities;

import it.unical.ilBelloDelleDonne.Hibernate.Model.Customer;
import it.unical.ilBelloDelleDonne.Hibernate.Model.User;

import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.context.ApplicationContext;

public abstract class QueryFactory {

	public static  List create(ApplicationContext context,String query){

		SessionFactory ses = (SessionFactory) context.getBean("sessionFactory");
		Session session = ses.openSession();

		Query q = session.createQuery(query);
		List l = q.list();

		session.close();
		return l;
	}

	public static User isUserRegistered(ApplicationContext context,String query, String user, String passUser, String username, String password){
		SessionFactory ses = (SessionFactory) context.getBean("sessionFactory");
		Session session = ses.openSession();

		Query q = session.createQuery(query);
		q.setParameter("user", username);
		q.setParameter("passUser", password);


		User u = (User) q.uniqueResult();
		session.close();

		return u;

	}

	public static Customer getCustomerByUser(ApplicationContext context, String query){
		SessionFactory ses = (SessionFactory) context.getBean("sessionFactory");
		Session session = ses.openSession();

		Query q = session.createQuery(query);

		Customer customer = (Customer) q.uniqueResult();
		session.close();
		
		return customer;

	}
	
	public static  List getReserveByParameter(ApplicationContext context,String query, String data){

		SessionFactory ses = (SessionFactory) context.getBean("sessionFactory");
		Session session = ses.openSession();

		Query q = session.createQuery(query);
		q.setParameter("billing", null);
		List l = q.list();

		session.close();
		return l;
	}
	
	public static List getSellingByParameter(ApplicationContext context, String query, String data){
		SessionFactory ses = (SessionFactory) context.getBean("sessionFactory");
		Session session = ses.openSession();

		Query q = session.createQuery(query);
		q.setParameter("billing", null);
		List l = q.list();

		session.close();
		return l;

	}

}