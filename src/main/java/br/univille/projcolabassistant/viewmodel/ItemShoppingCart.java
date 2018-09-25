package br.univille.projcolabassistant.viewmodel;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import br.univille.projcolabassistant.model.AccessoryColor;
import br.univille.projcolabassistant.model.AssistiveAccessory;

@Component
@Scope("session")

public class ItemShoppingCart {
	private long sequence;
	private int quantity;
	private AssistiveAccessory accessory = new AssistiveAccessory();
	private AccessoryColor color = new AccessoryColor();
	
	public AccessoryColor getColor() {
		return color;
	}
	public void setColor(AccessoryColor color) {
		this.color = color;
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
}
