package it.unical.ilBelloDelleDonne.Hibernate.Model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;


@Entity
@Table(name="Employee")
@PrimaryKeyJoinColumn(name="id")
public class Employee extends User{

	private static final long serialVersionUID = 1L;
	
	@Column(name="department")
	private String department;
	
	public Employee() {
		super();
	}
	
	public Employee(String name, String surname, String city, String streetAddress, String telephoneNumber, Date birth, String department, String email, Account account){
		super(name, surname, city, streetAddress, telephoneNumber, birth, email, account);
		this.department = department;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}
	
	

}