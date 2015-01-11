package it.unical.ilBelloDelleDonne.Hibernate.Dao;

import it.unical.ilBelloDelleDonne.Hibernate.Model.Selling;

public interface SellingDao {
	void create(Selling selling);
	Selling retrieve(int idSelling);
	void update(Selling selling);
	void delete(Selling selling);

}
