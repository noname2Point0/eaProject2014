package it.unical.ilBelloDelleDonne.Hibernate.Dao;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import it.unical.ilBelloDelleDonne.Hibernate.Model.Service;

public class ServiceDaoImpl implements ServiceDao{

	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@Override
	public void create(Service service) {
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();			
			session.save(service);
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
	public Service retrieve(int id) {
		Session session = sessionFactory.openSession();
		String queryString = "from Service where id = :id";
		Query query = session.createQuery(queryString);
		query.setParameter("id", id);
		Service service = (Service) query.uniqueResult();	
		session.close();	
	    return service;
	}

	@Override
	public void update(Service service) {
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.saveOrUpdate(service);			
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
	public void delete(Service service) {
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.delete(service);
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
