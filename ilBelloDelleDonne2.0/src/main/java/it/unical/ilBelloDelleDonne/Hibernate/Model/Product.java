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

	@ManyToOne
	@JoinColumn(name="productStock" )
	private ProductStock productStock;
	
	@ManyToOne
	@JoinColumn(name="selling")
	private Selling selling;
	
	public Product(){}
	
	public Product(ProductStock productStock){
		this.productStock = productStock;
	}
	
	public ProductStock getProductStock(){
		return this.productStock;
	}
	
	public void setProductStock(ProductStock productStock){
		this.productStock = productStock;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Selling getSelling() {
		return selling;
	}

	public void setSelling(Selling selling) {
		this.selling = selling;
	}
}