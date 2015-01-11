package it.unical.ilBelloDelleDonne.Hibernate.Dao;

import it.unical.ilBelloDelleDonne.Hibernate.Model.Account;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class AccountDaoImpl implements AccountDao{

    private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@Override
	public void create(Account account) {
	
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();			
			session.save(account);
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
	public Account retrieve(int idAccount) {
		Session session = sessionFactory.openSession();
		String queryString = "from Account where id = :idAccount";
		Query query = session.createQuery(queryString);
		query.setParameter("idAccount", idAccount);
		Account account = (Account) query.uniqueResult();	
		session.close();	
		return account;
	}

	@Override
	public void update(Account account) {
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.saveOrUpdate(account);			
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
	public void delete(Account account) {
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.delete(account);
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