package it.unical.ilBelloDelleDonne.Hibernate.Dao;

import it.unical.ilBelloDelleDonne.Hibernate.Model.Service;

public interface ServiceDao {
	void create(Service service);
	Service retrieve(int id);
	void update(Service service);
	void delete(Service service);
}
