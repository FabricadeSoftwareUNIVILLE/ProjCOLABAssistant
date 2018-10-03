package br.univille.projcolabassistant.viewmodel;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import br.univille.projcolabassistant.model.AccessoryColor;
import br.univille.projcolabassistant.model.AccessoryPhoto;
import br.univille.projcolabassistant.model.AccessorySize;
import br.univille.projcolabassistant.model.AssistiveAccessory;

@Component
@Scope("session")

public class ItemShoppingCart {
	private long sequence;
	private int quantity;
	private AssistiveAccessory accessory = new AssistiveAccessory();
	private AccessoryPhoto photo = new AccessoryPhoto();
	private AccessorySize size = new AccessorySize();
	
	
	public AccessoryPhoto getPhoto() {
		return photo;
	}
	public void setPhoto(AccessoryPhoto photo) {
		this.photo = photo;
	}
	public long getSequence() {
		return sequence;
	}
	public void setSequence(long sequence) {
		this.sequence = sequence;
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
	public AccessorySize getSize() {
		return size;
	}
	public void setSize(AccessorySize size) {
		this.size = size;
	}
}
