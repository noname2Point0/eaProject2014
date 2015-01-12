package it.unical.ilBelloDelleDonne.ApplicationData;

import it.unical.ilBelloDelleDonne.Hibernate.Model.Service;

import java.util.List;

public class ServiceList{
	
	private List<Service> services;

	public void setServices(List<Service> list){
		this.services = list;
	}
	
	public List<Service> getServices(){
		return this.services;
	}
}
