package it.unical.ilBelloDelleDonne.Hibernate.Model;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;


@Entity
@Table(name="Customer")
@PrimaryKeyJoinColumn(name="id")
public class Customer extends User {
	@NotNull
	@Column(name = "pIva_cf", length=255, nullable=false)
	private String pIva_cf;
	
	
	@OneToMany(mappedBy="customer")
    @OnDelete(action = OnDeleteAction.CASCADE)
	private List<Selling> sellings;
	
	@OneToMany(mappedBy="customer")
    @OnDelete(action = OnDeleteAction.CASCADE)
	private List<Reserve> reserves;

	public Customer() {
		super();

	}
	
	public Customer(String name, String surname, String city, String streetAddress, String telephoneNumber, Date birth, String pIva_cf, String email, Account account){
		super(name, surname, city, streetAddress, telephoneNumber, birth, email, account);
		this.pIva_cf = pIva_cf;
		sellings = new ArrayList<Selling>();
		reserves = new ArrayList<Reserve>();
	}
	
	public Customer(String name, String surname, String city, String streetAddress, String pIva_cf, String email, Account account){
		super(name, surname, city, streetAddress, email, account);

		this.pIva_cf = pIva_cf;
		sellings = new ArrayList<Selling>();
		reserves = new ArrayList<Reserve>();
	}

	public String getpIva_cf() {
		return pIva_cf;
	}

	public void setpIva_cf(String pIva_cf) {
		this.pIva_cf = pIva_cf;
	}
	public List<Selling> getSellings() {
		return sellings;
	}

	public void setSellings(List<Selling> sellings) {
		this.sellings = sellings;
	}

	public List<Reserve> getReserves() {
		return reserves;
	}

	public void setReserves(List<Reserve> reserves) {
		this.reserves = reserves;
	}
	
}