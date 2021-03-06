package it.unical.ilBelloDelleDonne.Hibernate.Model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="Billing")
public class Billing {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id")
	private int id;
	
	@Column(name="dateBilling")
	private Date dateBilling;
	
	@OneToOne
	@JoinColumn(name="selling")
	private Selling selling;
	
	@OneToOne
	@JoinColumn(name="reserve")
	private Reserve reserve;
	
	public Billing(){
		
	}
	
	public Billing(Date dateBilling, Selling selling){
		this.dateBilling = dateBilling;
		this.selling = selling;
	}
	
	public Billing(Date dateBilling, Reserve reserve){
		this.dateBilling = dateBilling;
		this.reserve = reserve;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDateBilling() {
		return dateBilling;
	}

	public void setDateBilling(Date dateBilling) {
		this.dateBilling = dateBilling;
	}

	public Selling getSelling() {
		return selling;
	}

	public void setSelling(Selling selling) {
		this.selling = selling;
	}

	public Reserve getReserve() {
		return reserve;
	}

	public void setReserve(Reserve reserve) {
		this.reserve = reserve;
	}

}