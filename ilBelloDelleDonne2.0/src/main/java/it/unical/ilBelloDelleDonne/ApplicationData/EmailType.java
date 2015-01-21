package it.unical.ilBelloDelleDonne.ApplicationData;

public abstract class EmailType {
	
	public static String getRegistrationType(){
		return new String("registration");
	}

	public static String getAdminRegistrationType() {
		return new String("admin registration");
	}
	
	public static String getConfirmSelling(){
		return new String("confirm selling");
	}

}
