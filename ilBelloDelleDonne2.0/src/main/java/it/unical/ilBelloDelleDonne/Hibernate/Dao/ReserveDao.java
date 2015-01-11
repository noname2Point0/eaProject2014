package it.unical.ilBelloDelleDonne.Hibernate.Dao;

import it.unical.ilBelloDelleDonne.Hibernate.Model.Reserve;

public interface ReserveDao {
	void create(Reserve reserve);
	Reserve retrieve(int idReserve);
	void update(Reserve reserve);
	void delete(Reserve reserve);

}
