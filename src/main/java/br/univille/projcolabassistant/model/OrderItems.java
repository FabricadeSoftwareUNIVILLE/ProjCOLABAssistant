package br.univille.projcolabassistant.model;


import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class OrderItems {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	private int quantity;
	@ManyToOne(cascade= {CascadeType.MERGE,CascadeType.REFRESH})
	private AssistiveAccessory accessory = new AssistiveAccessory();
	@ManyToOne(cascade= {CascadeType.MERGE,CascadeType.REFRESH})
	private AccessorySize accessorySize = new AccessorySize();
	@ManyToOne(cascade= {CascadeType.MERGE,CascadeType.REFRESH})
	private AccessoryColor accessoryColor = new AccessoryColor();
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public AssistiveAccessory getAccessory() {
		return accessory;
	}
	public void setAccessory(AssistiveAccessory accessory) {
		this.accessory = accessory;
	}
	public AccessorySize getAccessorySize() {
		return accessorySize;
	}
	public void setAccessorySize(AccessorySize accessorySize) {
		this.accessorySize = accessorySize;
	}
	public AccessoryColor getAccessoryColor() {
		return accessoryColor;
	}
	public void setAccessoryColor(AccessoryColor accessoryColor) {
		this.accessoryColor = accessoryColor;
	}
	
}
