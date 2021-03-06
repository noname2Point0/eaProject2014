package it.unical.ilBelloDelleDonne.Hibernate.Model;


import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;


@Entity
@Table(name="User")
@Inheritance(strategy = InheritanceType.JOINED)
public class User implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id")
	private int id;
		
	@OneToOne
	@JoinColumn(name="account")
	private Account account;
	
	@NotNull
	@Size(min = 2, max = 10)
	@Column(name="name", length=255, nullable=false)
	private String name;

	@NotNull
	@Size(min = 2, max = 10)
	@Column(name="surname", length=255, nullable=false)
	private String surname;
	
	@NotEmpty
	@NotNull
	@Email
	@Column(name="email", length=255, nullable=false)
	private String email;
	
	@Past
	@NotNull
	@DateTimeFormat(pattern="dd/mm/yyyy")
	@Column(name="birth", length=255)
	private Date birth;
	
	@NotNull
	@NotEmpty
	@Column(name="city", length=255, nullable=false)
	private String city;
	
	@Column(name="telephoneNumber", length=255)
	private String telephoneNumber;
	
	@NotNull
	@NotEmpty
	@Column(name="streetAddress", length=255, nullable=false)
	private String streetAddress;
	
	
	public User(){
	}
	
	public User(String name, String surname, String city, String streetAddress, String telephoneNumber, Date birth, String email, Account account) {
		this.account = account;
		this.name = name;
		this.surname = surname;
		this.email = email;
		this.birth = birth;
		this.city = city;
		this.streetAddress = streetAddress;
		this.telephoneNumber = telephoneNumber;
	
	}
	
	
	public User(String name, String surname, String city, String streetAddress, String email, Account account) {
		this.account = account;
		this.name = name;
		this.surname = surname;
		this.email = email;
		this.city = city;
		this.streetAddress = streetAddress;
	}
 

	public String getName() {
		return name;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getTelephoneNumber() {
		return telephoneNumber;
	}

	public void setTelephoneNumber(String telephoneNumber) {
		this.telephoneNumber = telephoneNumber;
	}

	public String getStreetAddress() {
		return streetAddress;
	}

	public void setStreetAddress(String streetAddress) {
		this.streetAddress = streetAddress;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getBirth() {
		return birth;
	}

	public void setBirth(Date birth) {
		this.birth = birth;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}
	
	public void copy(User us){
		this.account = us.account;
		this.name = us.name;
		this.surname = us.surname;
		this.email =us.email;
		this.birth = us.birth;
		this.city = us.city;
		this.streetAddress = us.streetAddress;
		this.telephoneNumber = us.telephoneNumber;
	}
	
}