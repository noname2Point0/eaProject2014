package it.unical.ilBelloDelleDonne.Hibernate.Dao;

import it.unical.ilBelloDelleDonne.Hibernate.Model.ImageWrapper;

public interface ImageWrapperDao {

	void create(ImageWrapper imageWrapper);
	ImageWrapper retrieve(int id);
	void update(ImageWrapper imageWrapper);
	void delete(ImageWrapper imageWrapper);
}
