package it.unical.ilBelloDelleDonne.Hibernate.Utilities;

import it.unical.ilBelloDelleDonne.Hibernate.Dao.UserDao;
import it.unical.ilBelloDelleDonne.Hibernate.Model.User;

import java.util.List;
import java.util.Random;

import org.springframework.context.ApplicationContext;

public abstract class CredentialsManager{
	
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
		UserDao userDao = (UserDao) context.getBean("userDao");
		User user = userDao.retrieve(username);
		
		if(user!=null){			
			return true;
		}
				
		return false;
	}

	public static String generateUsername(ApplicationContext context, String a, String b) {
		String username = new String();
		
		username = new String(a.substring(0,2)+"."+b.substring(0,2));
		
		int conc = 0;
		
		String test = new String(username);
		while(isAnExistingUser(context, test)){
			test = new String(username+String.valueOf(conc));
			conc++;
		}
		
		return test; 
	}
	
	public static String generatePassword(ApplicationContext context, String type){
		String init= new String();
		if(AccountType.isEmployeeSaloon(type)){
			init = "ES";
		}
		
		if(AccountType.isEmloyeeWarehouse(type)){
			init="EW";
		}
		
		if(AccountType.isAdmin(type)){
			init="ADM";
		}
		
		Random rand = new Random();
		String attach = String.valueOf(rand.nextInt(Integer.MAX_VALUE));
		String password = init+attach;
		
		if(password.length()>=10){
			password = password.substring(0,9);
		}
		
		return password;
	}
}