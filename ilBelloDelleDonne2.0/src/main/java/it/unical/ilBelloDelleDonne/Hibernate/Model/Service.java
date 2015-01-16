package it.unical.ilBelloDelleDonne.Hibernate.Model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name="Service")
public class Service {
	
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id")
    private int id;
	
	@Column(name="description", nullable=false)
	private String description;
	
	@Column(name="price", nullable=false)
	private Double price;
	
	@OneToMany(mappedBy="service")
    @OnDelete(action = OnDeleteAction.CASCADE)
	private List<Reserve> reserves;
	
	public Service(){}
	
	public Service(String description, Double price){
		setDescription(description);
		setPrice(price);
		reserves = new ArrayList<Reserve>();
	}
	
	public void setId(int id){
		this.id = id;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public void setPrice(Double price) {
		this.price = price;
	}
	
	public int getId() {
		return id;
	}
	
	public String getDescription() {
		return description;
	}
	
	public Double getPrice() {
		return price;
	}
	
}