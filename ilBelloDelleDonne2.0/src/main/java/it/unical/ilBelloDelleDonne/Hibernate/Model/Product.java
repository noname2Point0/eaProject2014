package it.unical.ilBelloDelleDonne.Hibernate.Model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name="Product")
public class Product {
	
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id")
    private int id;
	
	@Column(name="type", nullable=false)
	private String type;
	
	@Column(name="brand",nullable=false)
	private String brand;
	
	@Column(name="description")
	private String description;
	
	@Column(name="price", nullable=false)
	private Double price;
	
	@ManyToOne
	@JoinColumn(name="selling")
	private Selling selling;
	
	public Product(){}
	
	public Product(String type, String brand,String description,Double price){
		setType(type);
		setBrand(brand);
		setDescription(description);
		setPrice(price);
	}
	
	public Product(String type, String brand,Double price){
		setType(type);
		setBrand(brand);
		setPrice(price);
	}

	public String getType() {
		return type;
	}
	
	public int getId() {
		return id;
	}

	public String getBrand() {
		return brand;
	}

	public Double getPrice() {
		return price;
	}
	
	public String getDescription() {
		return description;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	public void setBrand(String brand) {
		this.brand =brand;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public void setPrice(Double price) {
		this.price = price;
	}
}
