package it.unical.ilBelloDelleDonne.Hibernate.Utilities;

import it.unical.ilBelloDelleDonne.Hibernate.Model.ImageWrapper;

import java.io.File;
import java.io.FileInputStream;

public abstract class LoadImage {

	public static ImageWrapper load(String nameImage){
		File file = new File("/home/vincenzo/git/ilBelloDelleDonne2.0/ilBelloDelleDonne2.0/src/main/webapp/resources/images/"+nameImage+"");
	//	File file = new File("./resources/images/"+nameImage+"");
		byte[] imageData = new byte[(int) file.length()];

		try {
			FileInputStream fileInputStream = new FileInputStream(file);
			fileInputStream.read(imageData);
			fileInputStream.close();
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
				
		ImageWrapper image = new ImageWrapper(nameImage, imageData);
		return image;
	}
	
	public static ImageWrapper loadByChooserFile(String pathFile, String nameImage){
		File file = new File(pathFile);
		
		byte[] imageData = new byte[(int) file.length()];
		
		try {
			FileInputStream fileInputStream = new FileInputStream(file);
			fileInputStream.read(imageData);
			fileInputStream.close();
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
				
		ImageWrapper image = new ImageWrapper(nameImage, imageData);
		return image;
	}

}
