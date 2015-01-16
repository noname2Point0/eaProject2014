package it.unical.ilBelloDelleDonne.Hibernate.Dao;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import it.unical.ilBelloDelleDonne.Hibernate.Model.Selling;

public class SellingDaoImpl implements SellingDao{

	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@Override
	public void create(Selling selling) {
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();			
			session.save(selling);
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
	public Selling retrieve(int idSelling) {
		Session session = sessionFactory.openSession();
		String queryString = "from Selling where id =:selling";
		Query query = session.createQuery(queryString);
		query.setParameter("selling", idSelling);
		Selling selling = (Selling) query.uniqueResult();	
		session.close();	
	    return selling;
	}

	@Override
	public void update(Selling selling) {
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.saveOrUpdate(selling);			
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
	public void delete(Selling selling) {
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.delete(selling);
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
