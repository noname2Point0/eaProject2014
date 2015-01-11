package it.unical.ilBelloDelleDonne.ApplicationData;

import java.util.ArrayList;
import java.util.List;

public class ProductCustomList{

	private List<ProductCustom> productsCustom;
	
	public ProductCustomList(){
		
	}
	
	public void setProductsCustom(List<ProductCustom> list) {
		
		this.productsCustom =list;
	}
	
	public List<ProductCustom> getProductsCustom(){
		return productsCustom;
	}
}
