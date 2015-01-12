package it.unical.ilBelloDelleDonne.ApplicationData;

import it.unical.ilBelloDelleDonne.Hibernate.Model.Product;

import java.util.List;

public class ProductList {

	private List<Product> products;
	
	public  void setProducts(List<Product> list){
		this.products = list;
	}
	
	public List getProducts(){
		return this.products;
	}
	
}
