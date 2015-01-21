package it.unical.ilBelloDelleDonne.Test;

import javax.xml.stream.Location;

import it.unical.ilBelloDelleDonne.Hibernate.Dao.UserDao;
import it.unical.ilBelloDelleDonne.Hibernate.Model.Account;
import it.unical.ilBelloDelleDonne.Hibernate.Model.User;
import it.unical.ilBelloDelleDonne.Hibernate.Utilities.AccountType;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;


@RunWith(BlockJUnit4ClassRunner.class)
@Transactional
public class UserDaoImplTest {
	
	@Autowired
	private UserDao userDao;
	
	
	@BeforeClass
	static public void  beforeAllTheOthers()
	{
		System.out.println("This method is invoked before all the others");
	
	}
	
	@Test
	public void testCreateUser(){
		Account account = new Account("vincenzo", "ea2014", AccountType.getCustomerType());
		User u = new User("vincenzo", "algieri", "corigliano", "mazzini", "algieriv@gmail.com", account);
		System.out.println("ecco lo user "+u.getEmail());
		
		userDao.create(u);
		
		User user = userDao.retrieve("vincenzo");
		Assert.assertEquals(user.getAccount().getUsername(), "vincenzo");
	}
}
