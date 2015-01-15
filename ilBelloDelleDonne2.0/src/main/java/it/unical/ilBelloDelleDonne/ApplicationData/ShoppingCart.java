package it.unical.ilBelloDelleDonne.ApplicationData;

import it.unical.ilBelloDelleDonne.Hibernate.Model.ProductStock;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ShoppingCart {

	private HashMap<String, ProductStock> products = new HashMap<String, ProductStock>();
	
	public void addToCart(ProductStock p){
		if(!products.containsKey(String.valueOf(p.getId())))
			products.put(String.valueOf(p.getId()),p);
	}
	
	public void removeToCart(ProductStock p){
		if(products.containsKey(String.valueOf(p.getId())))
			products.remove(String.valueOf(p.getId()));
	}
	
	public List getProductsIn(){
		return new ArrayList<ProductStock>(products.values());
	}
	
	public boolean isEmpyt(){
		return products.size() == 0;
	}
	
	public void clearCart(){
		products.clear();
	}
	
}
