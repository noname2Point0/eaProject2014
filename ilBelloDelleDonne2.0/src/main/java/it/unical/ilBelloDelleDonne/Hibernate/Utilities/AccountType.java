package it.unical.ilBelloDelleDonne.Hibernate.Utilities;

public abstract class AccountType {
	
	public static String getAdminType(){
		return new String("admin");		
	}
	
	public static String getCustomerType(){
		return new String("customer");
	}
	
	public static String getEmployeeWarehouseType(){
		return new String("employeeWarehouse");
	}
	
	public static String getEmployeeSaloonType(){
		return new String("employeeSaloon");
	}
	
	public static boolean isAdmin(String test){
		return test.equals("admin");
	}
	
	public static boolean isCustomer(String test){
		return test.equals("customer");
	}
	
	public static boolean isEmloyeeWarehouse(String test){
		return test.equals("employeeWarehouse");
	}
	
	public static boolean isEmployeeSaloon(String test){
		return test.equals("employeeSaloon");
	}
}