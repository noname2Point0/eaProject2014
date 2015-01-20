package it.unical.ilBelloDelleDonne.ApplicationData;

import it.unical.ilBelloDelleDonne.Hibernate.Model.Service;
import it.unical.ilBelloDelleDonne.Hibernate.Model.User;

public class ApplicationInfo{
	
	
	private User user = new User();
	private ShoppingCart shoppingCart = new ShoppingCart();
	private boolean userLogged = false;
	private Service service;
	private boolean selling = false;
	
	public ApplicationInfo(){}
	
	public void setUser(User us) {
		us.getAccount().setPassword("");
		userLogged = true;
		user.copy(us);
	}
	
	public User getUser() {
		return user;
	}
	
	public ShoppingCart getShoppingCart(){
		return shoppingCart;
	}
	
	public boolean isUserLogged(){
		return userLogged;
	}
	
	public void setService(Service service){
		this.service = service;
	}
	
	public Service getService(){
		return service;
	}
	
	public void setSelling(boolean value){
		this.selling = value;
	}
	
	public boolean getSelling(){
		return this.selling;
	}
	
}
