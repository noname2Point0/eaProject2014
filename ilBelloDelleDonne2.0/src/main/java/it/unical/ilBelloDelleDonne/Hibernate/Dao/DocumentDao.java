package it.unical.ilBelloDelleDonne.Hibernate.Dao;

import it.unical.ilBelloDelleDonne.Hibernate.Model.Document;

public interface DocumentDao {
	
	void create(Document document);
	Document retrieve(int idBilling);
	void update(Document document);
	void delete(Document document);

}
