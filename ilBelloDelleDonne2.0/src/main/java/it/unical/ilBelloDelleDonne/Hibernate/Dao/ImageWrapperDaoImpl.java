package it.unical.ilBelloDelleDonne.Hibernate.Dao;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import it.unical.ilBelloDelleDonne.Hibernate.Model.ImageWrapper;

public class ImageWrapperDaoImpl implements ImageWrapperDao{

	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@Override
	public void create(ImageWrapper imageWrapper) {
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();			
			session.save(imageWrapper);
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
	public ImageWrapper retrieve(int id) {
		Session session = sessionFactory.openSession();
		String queryString = "from ImageWrapper where id =:image";
		Query query = session.createQuery(queryString);
		query.setParameter("image", id);
		ImageWrapper imageWrapper = (ImageWrapper) query.uniqueResult();	
		session.close();	
		return imageWrapper;
	}

	@Override
	public void update(ImageWrapper imageWrapper) {
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.saveOrUpdate(imageWrapper);			
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
	public void delete(ImageWrapper imageWrapper) {
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.delete(imageWrapper);
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
