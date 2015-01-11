package it.unical.ilBelloDelleDonne.Hibernate.Dao;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import it.unical.ilBelloDelleDonne.Hibernate.Model.Billing;
import it.unical.ilBelloDelleDonne.Hibernate.Model.Product;

public class BillingDaoImpl implements BillingDao{

	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public void create(Billing billing) {
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();			
			session.save(billing);
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
	public Billing retrieve(int idBilling) {
		Session session = sessionFactory.openSession();
		String queryString = "from Billing where id = :idBilling";
		Query query = session.createQuery(queryString);
		query.setParameter("idBilling", idBilling);
		Billing billing = (Billing) query.uniqueResult();	
		session.close();	
		return billing;
	}

	@Override
	public void update(Billing billing) {
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.saveOrUpdate(billing);			
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
	public void delete(Billing billing) {
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.delete(billing);
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
