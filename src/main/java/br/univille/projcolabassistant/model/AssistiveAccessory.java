package br.univille.projcolabassistant.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class AssistiveAccessory extends AbstractReportObject{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	@Column(length = 500)
	private String name;
	@Column(length = 1000)
	private String description;
	@Column(length = 1000)
	private String prescription;
	@Column(length = 1000)
	private String function;
	private String code;
	@ManyToOne(cascade = { CascadeType.MERGE, CascadeType.REFRESH })
	private Category category = new Category();
	@ManyToMany(cascade = { CascadeType.MERGE, CascadeType.REFRESH })
	private List<AccessorySize> sizeList = new ArrayList<AccessorySize>();
	@ManyToMany(cascade = { CascadeType.MERGE, CascadeType.REFRESH })
	private List<AccessoryColor> colorList = new ArrayList<AccessoryColor>();
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "accessoryphoto_id")
	private List<AccessoryPhoto> photoList = new ArrayList<AccessoryPhoto>();
	@OneToOne(cascade = CascadeType.ALL)
	private AccessoryPhoto principalPhoto = new AccessoryPhoto();
	
	public AssistiveAccessory() {
		this.setReportType("assistiveaccessory");
		this.setTemplatePath("report/accessory-pdf-template.html");
	}
	
	
	public AssistiveAccessory(int id, int code, String description, String prescription, String function, String name, Category category, AccessoryPhoto principalPhoto) {
		// TODO Auto-generated constructor stub
		this.id = id;
		this.code = code;
		this.description = description;
		this.prescription = prescription;
		this.function = function;
		this.name = name;
		this.category = category;
		this.principalPhoto = principalPhoto;
		this.setReportType("assistiveaccessory");
		this.setTemplatePath("report/accessory-pdf-template.html");
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

	public String getPrescription() {
		return prescription;
	}

	public void setPrescription(String prescription) {
		this.prescription = prescription;
	}

	public String getFunction() {
		return function;
	}

	public void setFunction(String function) {
		this.function = function;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public List<AccessorySize> getSizeList() {
		return sizeList;
	}

	public void setSizeList(List<AccessorySize> sizeList) {
		this.sizeList = sizeList;
	}

	public List<AccessoryColor> getColorList() {
		return colorList;
	}

	public void setColorList(List<AccessoryColor> colorList) {
		this.colorList = colorList;
	}

	public List<AccessoryPhoto> getPhotoList() {
		return photoList;
	}

	public void setPhotoList(List<AccessoryPhoto> photoList) {
		this.photoList = photoList;
	}

	public AccessoryPhoto getPrincipalPhoto() {
		return principalPhoto;
	}

	public void setPrincipalPhoto(AccessoryPhoto principalPhoto) {
		this.principalPhoto = principalPhoto;
	}
}
