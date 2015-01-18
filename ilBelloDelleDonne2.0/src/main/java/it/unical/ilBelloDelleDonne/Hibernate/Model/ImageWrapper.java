package it.unical.ilBelloDelleDonne.Hibernate.Model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="ImageWrapper")
public class ImageWrapper implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private int id;

	@Column(name = "imageName", nullable = false, length = 100)
	private String imageName;

	@Column(name = "data", nullable = false, length = 100000)
	private byte[] data;
	
	@OneToOne(mappedBy="imageWrapper")
	private ProductStock productStock;
	
	public ImageWrapper(){
		
	}
	
	public ImageWrapper(String imageName, byte[] data){
		this.imageName = imageName;
		this.data = data;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	public byte[] getData() {
		return data;
	}

	public void setData(byte[] data) {
		this.data = data;
	}

	public ProductStock getProductStock() {
		return productStock;
	}

	public void setProductStock(ProductStock productStock) {
		this.productStock = productStock;
	}
	
}
