package it.unical.ilBelloDelleDonne.Hibernate.Dao;

import it.unical.ilBelloDelleDonne.Hibernate.Model.Product;
import it.unical.ilBelloDelleDonne.Hibernate.Model.ProductStock;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class ProductStockDaoImpl implements ProductStockDao{

private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
		@Override
		public void create(ProductStock productStock) {
			Session session = sessionFactory.openSession();
			Transaction tx = null;
			try {
				tx = session.beginTransaction();			
				session.save(productStock);
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
	public ProductStock retrieve(int id) {
		Session session = sessionFactory.openSession();
		String queryString = "from ProductStock where id = :product";
		Query query = session.createQuery(queryString);
		query.setParameter("product", id);
		ProductStock productStock = (ProductStock) query.uniqueResult();	
		session.close();	
	    return productStock;
	}

	@Override
	public void update(ProductStock productStock){
		
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.saveOrUpdate(productStock);			
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
	public void delete(ProductStock productStock){
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.delete(productStock);
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
