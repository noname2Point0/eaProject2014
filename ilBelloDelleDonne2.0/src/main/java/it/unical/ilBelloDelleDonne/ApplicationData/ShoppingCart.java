package it.unical.ilBelloDelleDonne.ApplicationData;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ShoppingCart {

	private HashMap<String, ProductCustom> products = new HashMap<String, ProductCustom>();
	
	public void addToCart(ProductCustom p){

		if(!products.containsKey(p.getIdentify()))
			products.put(p.getIdentify(),p);
	}
	
	public void removeToCart(ProductCustom p){
		if(products.containsKey(p.getIdentify()))
			products.remove(p.getIdentify());
	}
	
	public List getProductsIn(){
		return new ArrayList<ProductCustom>(products.values());
	}
	
	public boolean isEmpyt(){
		return products.size() == 0;
	}
	
	public void clearCart(){
		products.clear();
	}
	
}
