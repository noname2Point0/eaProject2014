package it.unical.ilBelloDelleDonne.Hibernate.Dao;

import it.unical.ilBelloDelleDonne.Hibernate.Model.Account;

public interface AccountDao {

	void create(Account account);
	Account retrieve(String username);
	void update(Account account);
	void delete(Account account);
}
