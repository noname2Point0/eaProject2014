package it.unical.ilBelloDelleDonne.Hibernate.Dao;

import it.unical.ilBelloDelleDonne.Hibernate.Model.Product;

public interface ProductDao {
	void create(Product product);
	Product retrieve(int idProduct);
	void update(Product product);
	void delete(Product product);
}
