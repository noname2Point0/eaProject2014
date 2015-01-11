package it.unical.ilBelloDelleDonne.Hibernate.Dao;

import it.unical.ilBelloDelleDonne.Hibernate.Model.User;

public interface UserDao {

	void create(User user);
	User retrieve(String username);
	void update(User user);
	void delete(User user);
}
