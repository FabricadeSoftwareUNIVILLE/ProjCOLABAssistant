package br.univille.projcolabassistant.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class User extends AbstractReportObject {	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	@Column(length=1000)
	private String name;
	private String email;
	private String type;
	private String phone;
	@Column(length=1000)
	private String address;
	private boolean enabled=true;
	
	@ManyToOne(cascade={CascadeType.MERGE,CascadeType.REFRESH})
	private City city = new City();
	@ManyToMany(cascade= {CascadeType.MERGE, CascadeType.REFRESH})
	private List<Institution> institutionList = new ArrayList<Institution>();
	
	public User() {
		this.setReportType("users");
		this.setTemplatePath("report/user-pdf-template");
	}
	
	public User(long id, String name, String email, String type, String phone, String address, boolean enabled) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.type = type;
		this.phone = phone;
		this.address = address;
		this.enabled = enabled;
		
		this.setReportType("users");
		this.setTemplatePath("report/user-pdf-template");
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public boolean isEnabled() {
		return enabled;
	}
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	public City getCity() {
		return city;
	}
	public void setCity(City city) {
		this.city = city;
	}
	public List<Institution> getInstitutionList() {
		return institutionList;
	}
	public void setInstitutionList(List<Institution> institutionList) {
		this.institutionList = institutionList;
	}
}
