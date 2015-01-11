package it.unical.ilBelloDelleDonne.ApplicationData;

import it.unical.ilBelloDelleDonne.Hibernate.Model.User;

public class ApplicationInfo{
	
	private User user = new User();
	private ShoppingCart shoppingCart = new ShoppingCart();
	private boolean userLogged = false;
	
	public ApplicationInfo(){}
	
	public void setUser(User us) {
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
	
}
