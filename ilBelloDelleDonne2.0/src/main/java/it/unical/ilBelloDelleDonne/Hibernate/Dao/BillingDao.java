package it.unical.ilBelloDelleDonne.Hibernate.Dao;


import it.unical.ilBelloDelleDonne.Hibernate.Model.Billing;

public interface BillingDao {

	void create(Billing billing);
	Billing retrieve(int idBilling);
	void update(Billing billing);
	void delete(Billing billing);
}
