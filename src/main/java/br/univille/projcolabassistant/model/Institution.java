package br.univille.projcolabassistant.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Institution extends AbstractReportObject {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	@Column(length=1000)
	private String name;
	@Column(length=1000)
	private String description;
	@Column(length=1000)
	private String address;
	private String phone;
	private String email;
	private String technicalManager;

	
	@ManyToOne(cascade={CascadeType.MERGE,CascadeType.REFRESH})
	private City city = new City();
	
	public Institution() {
		this.setReportType("institutions");
		this.setTemplatePath("report/institution-pdf-template");
	}
	
	public Institution(long id, String name, String description, String address, String phone, String email,
			City city) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.address = address;
		this.phone = phone;
		this.email = email;
		this.city = city;
		
		this.setReportType("institutions");
		this.setTemplatePath("report/institution-pdf-template");
	}

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public City getCity() {
		return city;
	}
	public void setCity(City city) {
		this.city = city;
	}

	public String getTechnicalManager() {
		return technicalManager;
	}

	public void setTechnicalManager(String technicalManager) {
		this.technicalManager = technicalManager;
	}
	
}

