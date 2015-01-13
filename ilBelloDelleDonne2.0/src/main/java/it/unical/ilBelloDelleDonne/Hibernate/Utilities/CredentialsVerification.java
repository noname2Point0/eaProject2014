package it.unical.ilBelloDelleDonne.Hibernate.Utilities;

import it.unical.ilBelloDelleDonne.Hibernate.Dao.UserDao;
import it.unical.ilBelloDelleDonne.Hibernate.Model.User;

import java.util.List;

import org.springframework.context.ApplicationContext;

public abstract class CredentialsVerification{
	
	public static boolean verify(ApplicationContext context,User user,String username,String password){
		
		UserDao userDao = (UserDao) context.getBean("userDao");
		User us = userDao.retrieve(username);
		
		
		if(us!= null){
			if(us.getAccount().getPassword().equals(password)){
					user.copy(us);
					return true;
			}
		}
		return false;
	}
	
	public static boolean isAnExistingUser(ApplicationContext context, String username){
		String query = "from User user where user.account.username='"+username+"'";
		
		List<User> userList = (List<User>) QueryFactory.create(context, query);
		
		if(!userList.isEmpty()){
			User user = userList.get(0);
			if(user!=null){			
				return true;
			}
		}		
		return false;
		
	}

	public static String generateUsername(ApplicationContext context, String a, String b) {
		String username = new String();
		
		username = new String(a+"."+b);
		
		int conc = 0;
		
		String test = new String(username);
		while(isAnExistingUser(context, test)){
			test = new String(username+String.valueOf(conc));
			conc++;
		}
		
		return test; 
	}

}