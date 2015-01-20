package it.unical.ilBelloDelleDonne.Hibernate.Model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="Reserve")
public class Reserve {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id")
	private int id;
	
	@Temporal(TemporalType.DATE)
    @Column(name = "dateOrder", nullable = false)
    private Date dateOrder;
	
	@Future
	@NotNull
	@DateTimeFormat(pattern="dd/mm/yyyy")
	@Temporal(TemporalType.DATE)
    @Column(name = "dateService", nullable = false)
    private Date dateService;
	
	@Column(name="time")
	private String time;
	
	@ManyToOne
	@JoinColumn(name="customer")
	private Customer customer;
	
	@ManyToOne
	@JoinColumn(name="service")
	private Service service;
	
	@OneToOne(mappedBy="reserve")
	private Billing billing;
	
	public Reserve(){}
	
	public Reserve(Customer customer, Date dataOrder, Date dataService, String time, Service service){
		this.customer = customer;
		this.dateOrder = dataOrder;
		this.dateService = dataService;
		this.time = time;
		this.service = service;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDateOrder() {
		return dateOrder;
	}

	public void setDateOrder(Date dateOrder) {
		this.dateOrder = dateOrder;
	}

	public Date getDateService() {
		return dateService;
	}

	public void setDateService(Date dateService) {
		this.dateService = dateService;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public Service getService() {
		return service;
	}

	public void setService(Service service) {
		this.service = service;
	}
	
	public void setBilling(Billing billing){
		this.billing = billing;
	}
	
	public Billing getBilling(){
		return this.billing;
	}
	
}