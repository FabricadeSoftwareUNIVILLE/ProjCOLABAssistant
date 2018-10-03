package br.univille.projcolabassistant.model;


import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class AccessoryPhoto {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	@Column(length=1000)
	private String description;
	@Column(length=5000)
	private String URI;
	@ManyToOne(cascade= {CascadeType.REFRESH,CascadeType.MERGE},optional=true)
	private AccessoryColor accessoryColor;

	
	public AccessoryColor getAccessoryColor() {
		return accessoryColor;
	}

	public void setAccessoryColor(AccessoryColor accessoryColor) {
		this.accessoryColor = accessoryColor;
	}

	public AccessoryPhoto() {
	}
	
	public AccessoryPhoto(int id, String description, String URI, int i) {
		this.id = id;
		this.description = description;
		this.URI = URI;
	}

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getURI() {
		return URI;
	}
	public void setURI(String uRI) {
		URI = uRI;
	}
}
