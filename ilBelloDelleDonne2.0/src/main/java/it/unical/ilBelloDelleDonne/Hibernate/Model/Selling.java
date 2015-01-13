package it.unical.ilBelloDelleDonne.Hibernate.Model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name="Selling")
public class Selling {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id")
	private int id;
	
	@ManyToOne
	@JoinColumn(name="customer")
	private Customer customer;
	
	@Temporal(TemporalType.DATE)
    @Column(name = "dateOrder", nullable = false)
    private Date dateOrder;
	
	@Temporal(TemporalType.DATE)
    @Column(name = "dateConsignment")
    private Date dateConsignment;
	
	@Column(name="sellingCost")
	private double sellingCost;
	
	@OneToMany(mappedBy="selling", fetch=FetchType.EAGER)
    @OnDelete(action = OnDeleteAction.CASCADE)
	private List<Product> products;
		
	@OneToOne(mappedBy="selling")
	private Billing billing;
	
	public Selling(){
	}

	
	public Selling(Customer customer, Date dateOrder, Date dateConsignment, double sellingCost, List<Product> products){
		this.customer =  customer;
		this.dateOrder = dateOrder;
		this.dateConsignment = dateConsignment;
		this.sellingCost = sellingCost;
		this.products = products;
	}
	
	public Selling(Customer customer, Date dateOrder, Date dateConsignment, double sellingCost){
		this.customer =  customer;
		this.dateOrder = dateOrder;
		this.dateConsignment = dateConsignment;
		this.sellingCost = sellingCost;
		products = new ArrayList<Product>();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Date getDateOrder() {
		return dateOrder;
	}

	public void setDateOrder(Date dateOrder) {
		this.dateOrder = dateOrder;
	}

	public Date getDateConsignment() {
		return dateConsignment;
	}

	public void setDateConsignment(Date dateConsignment) {
		this.dateConsignment = dateConsignment;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}
	
	public double getSellingCost() {
		return sellingCost;
	}


	public void setSellingCost(double sellingCost) {
		this.sellingCost = sellingCost;
	}


	public Billing getBilling() {
		return billing;
	}


	public void setBilling(Billing billing) {
		this.billing = billing;
	}


	@Override
	public String toString() {
		return new String(String.valueOf(id)+" "+dateOrder +" "+dateConsignment);
	}

}