package it.unical.ilBelloDelleDonne.Hibernate.Dao;

import it.unical.ilBelloDelleDonne.Hibernate.Model.ProductStock;

public interface ProductStockDao {

	void create(ProductStock productStock);
	ProductStock retrieve(int id);
	void update(ProductStock productStock);
	void delete(ProductStock productStock);
}
