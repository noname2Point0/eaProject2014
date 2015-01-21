package it.unical.ilBelloDelleDonne.ApplicationData;


import org.springframework.web.multipart.commons.CommonsMultipartFile;

public class UploadForm {
	
	private CommonsMultipartFile file = null;

	private String type;
	
	
	private String brand;
	
	
	private String description;
	
	
	private Double price;
	
	
	private int quantity;
	
	
	private int minStock;
	

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getMinStock() {
		return minStock;
	}

	public void setMinStock(int minStock) {
		this.minStock = minStock;
	}

	public CommonsMultipartFile getFile() {
		return file;
	}

	public void setFile(CommonsMultipartFile file) {
		this.file = file;
	}		
}
