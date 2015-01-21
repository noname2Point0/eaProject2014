package it.unical.ilBelloDelleDonne.Hibernate.Model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="ProductStock")
public class ProductStock{

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id")
	private int id;

	@Column(name="type", nullable=false)
	private String type;
	@Column(name = "image", nullable = false, length = 10000000)
	private byte[] image;

	@Column(name="brand",nullable=false)
	private String brand;

	@Column(name="description")
	private String description;

	@Column(name="price", nullable=false)
	private Double price;

	@Column(name="quantity", nullable=false)
	private int quantity;

	@Column(name="minStock", nullable= false)
	private int minStock;

	@OneToMany(mappedBy="productStock")
	private List<Product> products;

	public ProductStock() {
	}
	
public ProductStock(String type, String brand, String description, int minStock, int quantity, Double price, byte[] image){
		this.type = type;
		this.brand = brand;
		this.description = description;
		this.price = price;
		this.minStock = minStock;
		this.quantity = quantity;
		this.image = image;
		this.products = new ArrayList<Product>();

	}

	public ProductStock(int id,String type, String brand, String description, int minStock, int quantity, Double price, byte[] image){
		this.id = id;
		this.type = type;
		this.brand = brand;
		this.description = description;
		this.price = price;
		this.minStock = minStock;
		this.quantity = quantity;
		this.image = image;
		this.products = new ArrayList<Product>();
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}
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

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
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
}
