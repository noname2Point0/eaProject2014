package it.unical.ilBelloDelleDonne.Hibernate.Dao;

import java.sql.Blob;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class CreateBlobDaoImpl implements CreateBlobDao{

	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@Override
	public void create(byte[] data) {
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();		
		        
		     Blob blob = Hibernate.getLobCreator(session).createBlob(data);			
			 
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
