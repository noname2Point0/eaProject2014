package it.unical.ilBelloDelleDonne.Hibernate.Model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;


@Entity
@Table(name="Employer")
@PrimaryKeyJoinColumn(name="id")
public class Admin extends User{

	private static final long serialVersionUID = 1L;
	
	public Admin(){
		super();
	}
	
	public Admin(String name, String surname, String city, String streetAddress, String telephoneNumber, Date birth, String email, Account account){
		super(name, surname, city, streetAddress, telephoneNumber, birth, email, account);
	}
	

}