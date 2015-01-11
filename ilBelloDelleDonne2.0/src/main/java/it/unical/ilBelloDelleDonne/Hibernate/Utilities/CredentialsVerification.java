package it.unical.ilBelloDelleDonne.Hibernate.Utilities;

import it.unical.ilBelloDelleDonne.Hibernate.Dao.UserDao;
import it.unical.ilBelloDelleDonne.Hibernate.Model.User;

import java.util.List;

import org.springframework.context.ApplicationContext;

public abstract class CredentialsVerification{
	
	public static boolean loginVerification(ApplicationContext context,User user,String username,String password){
		
		UserDao userDao = (UserDao) context.getBean("userDao");
		User us = userDao.retrieve(username);
		
		if(us!= null){
			user.copy(us);
			return true;
		}
		return false;
	}
	
	public static boolean isAnExistingUser(ApplicationContext context, String query){
		
		List<User> userList = (List<User>) QueryFactory.create(context, query);
		
		if(!userList.isEmpty()){
			User user = userList.get(0);
			if(user!=null){			
				return true;
			}
		}		
		return false;
		
	}

}