package it.unical.ilBelloDelleDonne.Hibernate.Dao;

import it.unical.ilBelloDelleDonne.Hibernate.Model.Product;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class ProductDaoImpl implements ProductDao{
	
	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@Override
	public void create(Product product) {
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();			
			session.save(product);
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
	public Product retrieve(int idProduct) {
		Session session = sessionFactory.openSession();
		String queryString = "from Product where id = :idProduct";
		Query query = session.createQuery(queryString);
		query.setParameter("product", idProduct);
		Product product = (Product) query.uniqueResult();	
		session.close();	
	    return product;
	}

	@Override
	public void update(Product product) {
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.saveOrUpdate(product);			
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
	public void delete(Product product) {
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.delete(product);
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


