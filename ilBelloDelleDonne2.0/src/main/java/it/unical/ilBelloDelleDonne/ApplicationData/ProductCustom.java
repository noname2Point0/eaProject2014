package it.unical.ilBelloDelleDonne.ApplicationData;

public class ProductCustom {

	private String type;
	private String brand;
	private String description;
	private int quantity;
	private Double price;
	
	public ProductCustom() {
	}
	
	public ProductCustom(String type, String brand, String description, int quantity, Double price){
		setType(type);
		setBrand(brand);
		setDescription(description);
		setQuantity(quantity);
		setPrice(price);
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	public void setBrand(String brand) {
		this.brand = brand;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	public void setPrice(Double price) {
		this.price = price;
	}
	
	public String getType() {
		return type;
	}
	
	public String getBrand() {
		return brand;
	}
	
	public String getDescription() {
		return description;
	}
	
	public int getQuantity() {
		return quantity;
	}
	
	public Double getPrice() {
		return price;
	}
	
	public String getIdentify(){
		return new String(type+" "+brand+" "+description+" ");
	}
}
