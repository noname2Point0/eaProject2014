package it.unical.ilBelloDelleDonne.ApplicationData;

import it.unical.ilBelloDelleDonne.Hibernate.Model.ProductStock;

import java.util.List;

public class ProductCustomList{

	private List<ProductStock> productsStock;
	
	public ProductCustomList(){
		
	}
	
	public void setProductsStock(List<ProductStock> list) {
		this.productsStock =list;
	}
	
	public List<ProductStock> getProductsStock(){
		return productsStock;
	}
}
