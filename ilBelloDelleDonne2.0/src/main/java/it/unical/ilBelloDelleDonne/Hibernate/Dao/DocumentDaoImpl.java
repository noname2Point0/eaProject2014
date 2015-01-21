package it.unical.ilBelloDelleDonne.Hibernate.Dao;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import it.unical.ilBelloDelleDonne.Hibernate.Model.Billing;
import it.unical.ilBelloDelleDonne.Hibernate.Model.Document;

public class DocumentDaoImpl implements DocumentDao{
	
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public void create(Document document) {
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();			
			session.save(document);
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
	public Document retrieve(int id) {
		Session session = sessionFactory.openSession();
		String queryString = "from Document where id = :document";
		Query query = session.createQuery(queryString);
		query.setParameter("billing", id);
		Document document = (Document) query.uniqueResult();	
		session.close();	
		return document;
	}

	@Override
	public void update(Document document) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Document document) {
		// TODO Auto-generated method stub
		
	}

}
