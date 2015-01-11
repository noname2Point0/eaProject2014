package it.unical.ilBelloDelleDonne.Hibernate.Dao;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import it.unical.ilBelloDelleDonne.Hibernate.Model.User;

public class UserDaoImpl implements UserDao{

	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@Override
	public void create(User user) {
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();			
			session.save(user);
			tx.commit();
		 }
		 catch (Exception e) {
		    if (tx!=null) 
		    		tx.rollback();
		 }
		 finally {
		     session.close();
		 }
		
	}

	@Override
	public User retrieve(String username) {
		Session session = sessionFactory.openSession();
		String queryString = "from User us where us.account.username = :user";
		Query query = session.createQuery(queryString);
		query.setParameter("user", username);
		User u = (User) query.uniqueResult();	
		session.close();	
	    return u;
	}

	@Override
	public void update(User user) {
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.saveOrUpdate(user);			
			tx.commit();
		 }
		 catch (Exception e) {
		    if (tx!=null) 
		    		tx.rollback();
		 }
		 finally {
		     session.close();
		 }
		
	}

	@Override
	public void delete(User user) {
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.delete(user);
			tx.commit();
		 }
		 catch (Exception e) {
		    if (tx!=null) 
		    		tx.rollback();
		 }
		 finally {
		     session.close();
		 }
		
	}

}
